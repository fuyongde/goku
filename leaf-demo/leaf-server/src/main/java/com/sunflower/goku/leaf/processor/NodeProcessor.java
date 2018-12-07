package com.sunflower.goku.leaf.processor;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.sunflower.goku.leaf.config.LeafConfig;
import com.sunflower.goku.leaf.generator.SnowFlake;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fuyongde
 * @date 2018/11/8
 * @desc 注册zk自动化获取节点信息
 */
@Configuration
public class NodeProcessor {

    private static final String LETOU_LEAF = "/letou/leaf";
    private static final String ZK_SPLIT = "/";
    private static final String SERVER_SPLIT = ":";
    private static final String SERVER_ID = "%s" + SERVER_SPLIT + "%s";
    /**
     * leaf的持久化节点（用于保存节点的顺序编号）
     **/
    private static final String LEAF_FOREVER = LETOU_LEAF.concat("/forever");
    /**
     * leaf的临时节点（用于保存活跃节点以及活跃心跳数）
     **/
    private static final String LEAF_TEMPORARY = LETOU_LEAF.concat("/temporary");
    /**
     * 持久化节点全路径
     **/
    private static final String FOREVER_PATH = LEAF_FOREVER + ZK_SPLIT + SERVER_ID;
    /**
     * 临时节点全路径
     **/
    private static final String TEMPORARY_PATH = LEAF_TEMPORARY + ZK_SPLIT + SERVER_ID;
    /**
     * Session失效时间
     **/
    private static final int SESSION_TIMEOUT = 5000;
    /**
     * Connection连接超时时间
     **/
    private static final int CONNECTION_TIMEOUT = 5000;
    private static Logger logger = LoggerFactory.getLogger(NodeProcessor.class);
    /**
     * 心跳间隔
     */
    private Long interval = 3000L;
    private ZkClient zkClient;

    private String fullForeverPath;
    private String fullTemporaryPath;

    @Autowired
    private Environment environment;
    @Autowired
    private LeafConfig leafConfig;

    private static String generateServerId(String ip, int dubboPort) {
        return String.format(SERVER_ID, ip, dubboPort);
    }

    private static String generateForeverPath(String ip, int dubboPort) {
        return String.format(FOREVER_PATH, ip, dubboPort);
    }

    private static String generateTemporaryPath(String ip, int dubboPort) {
        return String.format(TEMPORARY_PATH, ip, dubboPort);
    }

    private static String generateForeverPath(String serverPath) {
        return LEAF_FOREVER + ZK_SPLIT + serverPath;
    }

    @PostConstruct
    public void init() {
        String ip = NetUtils.getLocalHost();
        int dubboPort = getDubboPort();
        String zkAddress = leafConfig.getZkAddress();

        String serverId = generateServerId(ip, dubboPort);
        logger.info("ip:{}, dubboPort:{}, serverId:{}, zkAddress:{}", ip, dubboPort, serverId, zkAddress);

        //初始化zkClient
        zkClient = new ZkClient(zkAddress, SESSION_TIMEOUT, CONNECTION_TIMEOUT);
        //创建forever节点
        createForever();
        //创建temporary节点
        createTemporary(ip, dubboPort);

        //获取当前节点的path
        String serverPath = getServerPath(ip, dubboPort);
        int node = getNode(ip, dubboPort, serverPath);

        //检查是否正常
        checkHealth(node, serverPath);

        leafConfig.setNode(node);

        //启动异步线程，定时更新时间戳
        startHeartBeatThread();
    }

    @PreDestroy
    public void destory() {
        logger.info("服务即将停止");
        long now = System.currentTimeMillis();
        zkClient.writeData(fullForeverPath, now);
        logger.info("已经将最后最后的时间戳:{}更新至永久节点：{}", fullForeverPath, now);
    }

    /**
     * 守护线程，用来更新时间戳至节点
     */
    private void startHeartBeatThread() {
        Thread heartBeat = new Thread(() -> {
            while (true) {
                if (Objects.nonNull(zkClient)) {
                    long now = System.currentTimeMillis();
                    zkClient.writeData(fullForeverPath, now);
                    zkClient.writeData(fullTemporaryPath, now);

                    logger.info("更新最新的时间戳：{}", now);
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        heartBeat.setName("Snowflake-HeartBeat");
        heartBeat.setDaemon(true);
        heartBeat.start();
    }

    /**
     * 创建持久化节点（/letou/leaf/forever）
     */
    public void createForever() {
        boolean existsForever = zkClient.exists(LEAF_FOREVER);
        logger.info("是否存在forever节点：{}", existsForever);
        if (!existsForever) {
            //不存在持久化节点，则创建
            logger.info("创建forever节点：{}", LEAF_FOREVER);
            zkClient.createPersistent(LEAF_FOREVER, true);
        }
    }

    /**
     * 创建临时节点（/letou/leaf/temporary/serverId）
     *
     * @param ip        ip
     * @param dubboPort dubbo端口
     */
    public void createTemporary(String ip, int dubboPort) {
        boolean existsTemporary = zkClient.exists(LEAF_TEMPORARY);
        logger.info("是否存在temporary节点：{}", existsTemporary);
        if (!existsTemporary) {
            logger.info("创建temporary节点：{}", LEAF_TEMPORARY);
            zkClient.createPersistent(LEAF_TEMPORARY, true);
        }

        fullTemporaryPath = generateTemporaryPath(ip, dubboPort);
        long now = System.currentTimeMillis();

        if (!zkClient.exists(fullTemporaryPath)) {
            logger.info("创建临时节点：{}，写入当前时间戳：{}", fullTemporaryPath, now);
            zkClient.createEphemeral(fullTemporaryPath, now);
        }
    }

    /**
     * 获取自动分配的节点路径
     *
     * @param ip        当前ip
     * @param dubboPort dubbo端口
     * @return
     */
    public String getServerPath(String ip, int dubboPort) {
        //获取持久化节点下面的所有节点
        List<String> allForeverNodes = zkClient.getChildren(LEAF_FOREVER);
        String foreverPath = generateForeverPath(ip, dubboPort);
        long now = System.currentTimeMillis();

        if (CollectionUtils.isEmpty(allForeverNodes)) {
            //若节点列表为空，则创建
            return zkClient.createPersistentSequential(foreverPath, now).replace(LEAF_FOREVER + ZK_SPLIT, "");
        } else {
            String serverId = generateServerId(ip, dubboPort);
            List<String> allMatchNodes = allForeverNodes.stream()
                    .filter(Objects::nonNull)
                    .filter(node -> node.startsWith(serverId))
                    .collect(Collectors.toList());

            if (CollectionUtils.isEmpty(allMatchNodes)) {
                return zkClient.createPersistentSequential(foreverPath, now).replace(LEAF_FOREVER + ZK_SPLIT, "");
            } else if (allMatchNodes.size() == 1) {
                return allMatchNodes.get(0);
            } else {
                throw new RuntimeException("匹配的节点过多，请检查zookeeper。节点：" + serverId);
            }
        }
    }

    /**
     * 解析当前的节点id
     *
     * @param ip         ip
     * @param dubboPort  dubbo端口
     * @param serverPath 服务路径
     * @return
     */
    private int getNode(String ip, int dubboPort, String serverPath) {

        String serverId = generateServerId(ip, dubboPort);
        logger.info("serverId:{}, serverPath:{}", serverId, serverPath);
        String temp = serverPath.replace(serverId, "");
        return Integer.parseInt(temp);
    }

    /**
     * 节点可用性检查
     *
     * @param node       当前节点id
     * @param serverPath 当前节点路径
     */
    public void checkHealth(int node, String serverPath) {
        if (node > SnowFlake.MAX_NODE) {
            throw new RuntimeException("当前节点id超过最大节点。node：+ " + node);
        }

        fullForeverPath = generateForeverPath(serverPath);

        long lastTimestamp = zkClient.readData(fullForeverPath);
        long now = System.currentTimeMillis();

        if (now < lastTimestamp) {
            logger.error("该节点最后一次时间戳:{}", lastTimestamp);
            throw new RuntimeException("发生时钟回拨，启动失败。最后一次时间戳：" + lastTimestamp + "，当前时间戳：" + now);
        }
    }

    public int getDubboPort() {
        String port = environment.getProperty("dubbo.protocol.port");
        return Integer.valueOf(port);
    }


}
