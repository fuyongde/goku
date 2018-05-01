package com.goku.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fuyongde
 */
@SpringBootApplication
@EnableScheduling
public class DubboProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboProviderApplication.class,args);
  }
}
