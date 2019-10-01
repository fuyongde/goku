package com.sunflower.goku.alibaba.user.api.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2019/10/1 14:19
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private Integer id;
    private String name;
}
