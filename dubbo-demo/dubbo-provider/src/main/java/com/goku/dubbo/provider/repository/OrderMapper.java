package com.goku.dubbo.provider.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.goku.dubbo.provider.entity.Order;

@Mapper
public interface OrderMapper {
    int insert(@Param("pojo") Order pojo);

    int insertSelective(@Param("pojo") Order pojo);

    int insertList(@Param("pojos") List<Order> pojo);

    int update(@Param("pojo") Order pojo);
}
