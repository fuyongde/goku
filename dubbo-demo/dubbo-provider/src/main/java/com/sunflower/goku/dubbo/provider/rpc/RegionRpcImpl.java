package com.sunflower.goku.dubbo.provider.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunflower.bulma.tools.BeanMapper;
import com.sunflower.goku.dubbo.api.service.RegionRpc;
import com.sunflower.goku.dubbo.api.service.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.entity.Region;
import com.sunflower.goku.dubbo.provider.repository.RegionMapper;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @desc RegionRpc
 * @date 2017/12/30 18:36
 */
@Service
public class RegionRpcImpl implements RegionRpc {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public RegionDTO region(Integer id) {
        Region region = regionMapper.findById(id);
        RegionDTO regionDTO = BeanMapper.map(region, RegionDTO.class);
        return regionDTO;
    }
}
