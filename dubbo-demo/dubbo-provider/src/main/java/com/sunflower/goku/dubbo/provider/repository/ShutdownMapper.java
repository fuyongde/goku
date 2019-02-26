package com.sunflower.goku.dubbo.provider.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuyongde
 * @date 2019/2/26
 * @desc TODO add description in here
 */
@Mapper
public interface ShutdownMapper {

    int keepDB(@Param("duration") int duration);
}
