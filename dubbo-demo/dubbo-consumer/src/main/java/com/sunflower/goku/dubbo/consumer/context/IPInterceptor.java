package com.sunflower.goku.dubbo.consumer.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.common.collect.Sets;
import com.sunflower.goku.dubbo.consumer.handler.ErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author fuyongde
 * @desc IP拦截器
 * @date 2017/12/2 18:46
 */
@Component
public class IPInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(IPInterceptor.class);

    private static ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取当前的ip
        String currentIP = ContextHolder.getIpAddress(request);
        Set<String> securityIPSet = Sets.newHashSet(
                "127.0.0.1"
        );
        boolean isAllowed = true;

        if (!isAllowed) {

            logger.error("非法IP：{}", currentIP);

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
            ErrorResult errorResult = new ErrorResult(HttpServletResponse.SC_FORBIDDEN, "非法IP", request.getContextPath());
            response.getOutputStream().write(mapper.writeValueAsBytes(errorResult));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
