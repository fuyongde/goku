package com.sunflower.goku.dubbo.provider.processor;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sunflower.goku.dubbo.api.rpc.RegionRpc;
import com.sunflower.goku.dubbo.provider.repository.ShutdownMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author fuyongde
 * @date 2019/2/26
 * @desc TODO add description in here
 */
@Component
public class ApplicationProcessor {

    private Logger logger = LoggerFactory.getLogger(ApplicationProcessor.class);

    @Resource
    private ShutdownMapper shutdownMapper;

    @PostConstruct
    public void init() {
        logger.info("项目启动……");
    }

    @PreDestroy
    public void keepNetty() {
        ProtocolConfig.destroyAll();
    }

}
