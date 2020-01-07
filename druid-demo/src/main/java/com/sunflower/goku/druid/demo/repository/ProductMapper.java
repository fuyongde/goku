package com.sunflower.goku.druid.demo.repository;

import com.sunflower.goku.druid.demo.entity.Product;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuyongde
 */
public interface ProductMapper {

    /**
     * 减库存
     *
     * @param id        id
     * @param decrement 递减的数量
     * @return 修改的数量
     */
    int decr(@Param("id") Integer id, @Param("decrement") Integer decrement);

    /**
     * 根据id查询产品
     *
     * @param id id
     * @return 产品
     */
    Product getById(@Param("id") Integer id);
}
