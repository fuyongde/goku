package com.sunflower.goku.alibaba.user.service;

import com.sunflower.goku.alibaba.user.api.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author fuyongde
 * @date 2019/10/1 14:18
 */
@Service
public class UserService {

    public UserVO getById(Integer userId) {
        return UserVO.builder().id(userId).name("fuyongde").build();
    }
}
