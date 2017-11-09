package com.goku.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author fuyongde
 */
@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath:dubbo-consumer.xml"})
public class DubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboConsumerApplication.class, args);
  }
}
