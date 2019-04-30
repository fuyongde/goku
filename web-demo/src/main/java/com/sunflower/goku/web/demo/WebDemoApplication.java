package com.sunflower.goku.web.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author fuyongde
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.sunflower.goku.web.demo.repository")
public class WebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }
}
