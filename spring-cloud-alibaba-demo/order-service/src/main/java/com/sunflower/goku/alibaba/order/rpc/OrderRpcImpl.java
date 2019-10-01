package com.sunflower.goku.alibaba.order.rpc;

import com.sunflower.goku.alibaba.order.api.OrderRpc;
import com.sunflower.goku.alibaba.order.api.vo.OrderVO;
import com.sunflower.goku.alibaba.order.service.OrderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @date 2019/10/1 18:45
 */
@Service
public class OrderRpcImpl implements OrderRpc {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderVO getById(Integer id) {
        return orderService.getById(id);
    }
}
