package com.sunflower.goku.web.demo.context;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc Http请求日志过滤器
 */
@WebFilter(urlPatterns = "/*")
public class LogFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // doNothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            Map<String, Object> request = Maps.newHashMap();
            request.put("uri", httpServletRequest.getRequestURI());
            request.put("contextPath", httpServletRequest.getContextPath());
            request.put("httpMethod", httpServletRequest.getMethod());
            request.put("servletPath", httpServletRequest.getServletPath());
            logger.info("request:{}", JSON.toJSONString(request));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // doNothing
    }
}
