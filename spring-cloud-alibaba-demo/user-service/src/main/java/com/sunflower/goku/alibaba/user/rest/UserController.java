package com.sunflower.goku.alibaba.user.rest;

import com.sunflower.goku.alibaba.user.api.vo.UserVO;
import com.sunflower.goku.alibaba.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 * @date 2019/10/1 14:17
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserVO getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }
}
