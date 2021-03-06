package com.sunflower.goku.dubbo.provider.repository;

import com.sunflower.goku.dubbo.provider.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fuyongde
 */
@Mapper
public interface RegionMapper {

    /**
     * 根据id查询地区信息
     *
     * @param id 地区id
     * @return Region
     */
    Region getById(@Param("id") Long id);

    /**
     * 根据父级id查询子类信息
     *
     * @param parentId 地区id
     * @return Region
     */
    List<Region> getByParentId(@Param("parentId") Long parentId);

    /**
     * 查询所有地区信息
     *
     * @return Region
     */
    List<Region> getAll();
}
