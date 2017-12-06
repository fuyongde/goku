package com.goku.dubbo.consumer.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author fuyongde
 * @desc 拦截器配置类
 * @date 2017/11/14 19:58
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {

  @Autowired
  private IPInterceptor ipInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] IPPathPatterns = {
        "/**"
    };

    registry.addInterceptor(ipInterceptor)
        .addPathPatterns(IPPathPatterns);
    super.addInterceptors(registry);
  }

}
