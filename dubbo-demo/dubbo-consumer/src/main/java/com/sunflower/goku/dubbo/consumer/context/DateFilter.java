package com.sunflower.goku.dubbo.consumer.context;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fuyongde
 * @desc 参数的过滤器
 * @date 2017/11/9 15:23
 */
@WebFilter(filterName = "dateFilter", urlPatterns = "/*")
public class DateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {

        }

        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("NOW", String.valueOf(System.currentTimeMillis()));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
