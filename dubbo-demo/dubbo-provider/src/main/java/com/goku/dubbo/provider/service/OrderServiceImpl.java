package com.goku.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.goku.dubbo.api.service.OrderService;
import com.goku.dubbo.commons.generator.SnowFlake;
import com.goku.dubbo.commons.generator.SnowFlakeUnused;
import com.goku.dubbo.provider.config.NodeInfo;
import com.goku.dubbo.provider.entity.Order;
import com.goku.dubbo.provider.repository.OrderMapper;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

  @Autowired private NodeInfo nodeInfo;

  @Override
  public void createOrder(int i) {
    SnowFlake snowFlake = new SnowFlake(nodeInfo.getMachineId());
    for (int j = 0; j < i; j++) {
      String orderNo = String.valueOf(snowFlake.next());
      Order order = new Order();
      order.setOrderNo(orderNo);
      orderMapper.insertSelective(order);
    }
    logger.info("Done! Create {} orders", i);
  }
}
