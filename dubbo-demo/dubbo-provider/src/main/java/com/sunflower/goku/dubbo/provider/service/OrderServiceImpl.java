package com.sunflower.goku.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.goku.dubbo.api.service.OrderService;
import com.goku.dubbo.commons.generator.SnowFlake;
import com.sunflower.goku.dubbo.provider.entity.Order;
import com.sunflower.goku.dubbo.provider.repository.OrderMapper;
import com.sunflower.goku.leaf.api.SequenceRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/1 15:52
 * @desc 订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {

  private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Resource private OrderMapper orderMapper;

  @Reference
  private SequenceRpc sequenceRpc;

  @Override
  public void createOrder(int i) {
    long id = sequenceRpc.nextLong();
    for (int j = 0; j < i; j++) {
      String orderNo = String.valueOf(id);
      Order order = new Order();
      order.setOrderNo(orderNo);
      orderMapper.insertSelective(order);
    }
    logger.info("Done! Create {} orders", i);
  }
}
