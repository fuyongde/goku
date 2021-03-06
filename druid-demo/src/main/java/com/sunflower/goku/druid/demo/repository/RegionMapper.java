package com.sunflower.goku.druid.demo.repository;

import com.sunflower.goku.druid.demo.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fuyongde
 */
public interface RegionMapper {

    /**
     * 根据id查询地区信息
     *
     * @param id 地区id
     * @return Region
     */
    Region findById(@Param("id") Integer id);

    /**
     * 根据父级id查询子类信息
     *
     * @param parentId 地区id
     * @return Region
     */
    List<Region> findByParentId(@Param("parentId") Integer parentId);

    /**
     * 查询所有地区信息
     *
     * @return Region
     */
    List<Region> findAll();
}
