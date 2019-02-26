package com.sunflower.goku.dubbo.api.rpc;

import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;

/**
 * @author fuyongde
 */
public interface RegionRpc {

    /**
     * 根据id查询地区信息
     *
     * @param id
     * @return
     */
    RegionDTO region(Long id);

    void keepNetty(long duration);
}
