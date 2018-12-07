package com.sunflower.goku.fastjson.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author fuyongde
 */
@SpringBootApplication
public class FastjsonDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(FastjsonDemoApplication.class, args);
  }

  @Bean
  public HttpMessageConverters fastJsonHttpMessageConverters() {
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    HttpMessageConverter<?> converter = fastConverter;
    return new HttpMessageConverters(converter);
  }

}
