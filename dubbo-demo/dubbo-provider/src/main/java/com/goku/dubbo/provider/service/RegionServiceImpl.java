package com.goku.dubbo.provider.service;

import com.goku.dubbo.api.service.RegionService;
import com.goku.dubbo.api.service.dto.RegionDTO;
import com.goku.dubbo.commons.utils.BeanMapper;
import com.goku.dubbo.provider.entity.Region;
import com.goku.dubbo.provider.repository.RegionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fuyongde
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
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
