package com.sunflower.goku.alibaba.user.rpc;

import com.sunflower.goku.alibaba.user.api.UserRpc;
import com.sunflower.goku.alibaba.user.api.vo.UserVO;
import com.sunflower.goku.alibaba.user.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @date 2019/10/1 11:30
 */
@Service
public class UserRpcImpl implements UserRpc {

    @Autowired
    private UserService userService;

    @Override
    public UserVO getByUserId(Integer userId) {
        return userService.getById(userId);
    }
}
