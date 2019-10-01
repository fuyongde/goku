package com.sunflower.goku.alibaba.order.service;

import com.sunflower.goku.alibaba.order.api.vo.OrderVO;
import com.sunflower.goku.alibaba.user.api.UserRpc;
import com.sunflower.goku.alibaba.user.api.vo.UserVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author fuyongde
 * @date 2019/10/1 18:41
 */
@Service
public class OrderService {

    @Reference
    private UserRpc userRpc;

    public OrderVO getById(Integer id) {
        UserVO userVO = userRpc.getByUserId(1);
        return OrderVO.builder().orderId(id).detail("订单详情").userId(1).userName(userVO.getName()).build();
    }

}
