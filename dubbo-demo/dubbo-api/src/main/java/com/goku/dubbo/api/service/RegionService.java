package com.goku.dubbo.api.service;

import com.goku.dubbo.api.service.dto.RegionDTO;

/**
 * @author fuyongde
 */
public interface RegionService {

    /**
     * 根据id查询地区信息
     * @param id
     *
     * @return
     */
    RegionDTO region(Integer id);
}
