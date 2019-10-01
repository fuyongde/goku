package com.sunflower.goku.alibaba.user.api;

import com.sunflower.goku.alibaba.user.api.vo.UserVO;

/**
 * @author fuyongde
 * @date 2019/10/1 11:28
 */
public interface UserRpc {

    UserVO getByUserId(Integer userId);
}
