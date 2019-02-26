package com.sunflower.goku.dubbo.provider.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunflower.goku.dubbo.api.rpc.RegionRpc;
import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @desc RegionRpc
 * @date 2017/12/30 18:36
 */
@Service
public class RegionRpcImpl implements RegionRpc {

    private static Logger logger = LoggerFactory.getLogger(RegionRpcImpl.class);

    @Autowired
    private RegionService regionService;

    @Override
    public RegionDTO region(Long id) {
        return regionService.getById(id);
    }

    @Override
    public void keepNetty(long duration) {
        try {
            logger.info("keep Netty ...");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
