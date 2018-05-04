package com.goku.dubbo.consumer.context;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/4 11:25
 * @desc 频率限制器
 */
@Component
public class RateInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RateInterceptor.class);

    private static double PERMITS_PER_SECOND = 10.0D;
    RateLimiter limiter = RateLimiter.create(PERMITS_PER_SECOND);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        double acquire = limiter.acquire();
        logger.info("获取令牌成功，消耗：{}", acquire);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
