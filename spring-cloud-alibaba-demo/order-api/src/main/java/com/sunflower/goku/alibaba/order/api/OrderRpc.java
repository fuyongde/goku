package com.sunflower.goku.alibaba.order.api;

import com.sunflower.goku.alibaba.order.api.vo.OrderVO;

/**
 * @author fuyongde
 * @date 2019/10/1 18:38
 */
public interface OrderRpc {

    OrderVO getById(Integer id);

}
