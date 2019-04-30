package com.sunflower.goku.web.demo.service;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc TODO add description in here
 */
@Service
public class AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private static Cache<String, String> tokenCache = CacheBuilder.newBuilder().build();

    public String login(String username) {
        List<Integer> permissions = Lists.newArrayList(1);
        if (username.equals("admin")) {
            permissions.add(0);
        }

        Map<String, Object> userInfo = Maps.newHashMap();
        userInfo.put("username", username);
        userInfo.put("permissions", permissions);
        userInfo.put("timeout", new Date(System.currentTimeMillis() + 5 * 60 * 1000L));
        String tokenValue = JSON.toJSONString(userInfo);
        String token = UUID.randomUUID().toString();
        tokenCache.put(token, tokenValue);
        return token;
    }

    public Map<String, Object> auth(String token) {
        String tokenValue;
        try {
            tokenValue = tokenCache.get(token, () -> null);
        } catch (ExecutionException e) {
            logger.error("---错误：{}", e);
            return null;
        }
        return JSON.parseObject(tokenValue, Map.class);
    }

}
