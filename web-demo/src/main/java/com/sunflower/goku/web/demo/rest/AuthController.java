package com.sunflower.goku.web.demo.rest;

import com.google.common.collect.Maps;
import com.sunflower.goku.web.demo.annotation.Auth;
import com.sunflower.goku.web.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc TODO add description in here
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> login(String username, String password) {
        String token = authService.login(username);
        Map<String, Object> result = Maps.newHashMap();
        result.put("token", token);
        return result;
    }
}
