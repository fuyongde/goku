package com.sunflower.goku.dubbo.provider.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunflower.goku.dubbo.api.rpc.RegionRpc;
import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @desc RegionRpc
 * @date 2017/12/30 18:36
 */
@Service
public class RegionRpcImpl implements RegionRpc {

    @Autowired
    private RegionService regionService;

    @Override
    public RegionDTO region(Long id) {
        return regionService.getById(id);
    }
}
