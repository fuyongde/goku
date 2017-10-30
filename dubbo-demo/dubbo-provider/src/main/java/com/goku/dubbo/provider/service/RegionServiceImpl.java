package com.goku.dubbo.provider.service;

import com.goku.dubbo.api.service.RegionService;
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
    public Object region(Integer id) {
        return regionMapper.findById(id);
    }
}
