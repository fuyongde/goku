package com.goku.dubbo.provider.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
