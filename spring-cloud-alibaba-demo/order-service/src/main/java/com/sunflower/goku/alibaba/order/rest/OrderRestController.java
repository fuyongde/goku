package com.sunflower.goku.alibaba.order.rest;

import com.sunflower.goku.alibaba.order.api.vo.OrderVO;
import com.sunflower.goku.alibaba.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 * @date 2019/10/1 18:44
 */
@RestController
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}")
    public OrderVO getById(@PathVariable Integer userId) {
        return orderService.getById(userId);
    }
}
