package com.sunflower.goku.alibaba.order.api.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2019/10/1 18:38
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO implements Serializable {
    private Integer userId;
    private String userName;
    private Integer orderId;
    private String detail;
}
