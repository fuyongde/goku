package com.sunflower.goku.dubbo.provider.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/1 15:33
 * @desc 订单
 */
public class Order extends BaseEntity {
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
