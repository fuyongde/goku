package com.sunflower.goku.web.demo.context;

import com.sunflower.goku.web.demo.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc TODO add description in here
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    public static ThreadLocal<List<String>> permissions = new ThreadLocal<>();

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("进行授权验证");

        String token = Optional
                .ofNullable(request.getHeader("token"))
                .orElseThrow(() -> new RuntimeException("权限不足"));

        Map<String, Object> userInfo = Optional
                .ofNullable(authService.auth(token))
                .orElseThrow(() -> new RuntimeException("认证失败"));

        List<Integer> permissionList = (List<Integer>) userInfo.get("permissions");
        permissions.set(permissionList.stream().map(String::valueOf).collect(Collectors.toList()));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("删除当前线程对象中的权限信息");
        permissions.remove();
    }


}
