package com.sunflower.goku.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.goku.dubbo.commons.utils.BeanMapper;
import com.sunflower.goku.dubbo.api.service.RegionService;
import com.sunflower.goku.dubbo.api.service.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.entity.Region;
import com.sunflower.goku.dubbo.provider.repository.RegionMapper;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @desc RegionService
 * @date 2017/12/30 18:36
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;

    @Override
    public RegionDTO region(Integer id) {
        Region region = regionMapper.findById(id);
        RegionDTO regionDTO = BeanMapper.map(region, RegionDTO.class);
        return regionDTO;
    }
}
