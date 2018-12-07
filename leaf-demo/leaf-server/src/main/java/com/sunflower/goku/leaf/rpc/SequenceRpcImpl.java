package com.sunflower.goku.leaf.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunflower.goku.leaf.api.SequenceRpc;
import com.sunflower.goku.leaf.config.LeafConfig;
import com.sunflower.goku.leaf.generator.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author fuyongde
 * @date 2018/10/31
 */
@Service
public class SequenceRpcImpl implements SequenceRpc {

    private static Logger logger = LoggerFactory.getLogger(SequenceRpcImpl.class);

    @Autowired
    private LeafConfig leafConfig;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public String nextUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String nextUuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public long nextLong() {
        int node = leafConfig.getNode();
        long result = snowFlake.next();
        logger.info("node : {}, sequence : {}", node, result);
        return result;
    }

}
