package com.sunflower.goku.web.demo.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] paths = {
                "/**"
        };

        String[] excludePaths = {
                "/auth/login"
        };

        registry.addInterceptor(authInterceptor).addPathPatterns(paths).excludePathPatterns(excludePaths);
    }
}
