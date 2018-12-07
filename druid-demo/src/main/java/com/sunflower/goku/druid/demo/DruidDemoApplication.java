package com.sunflower.goku.druid.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author fuyongde
 */
@ServletComponentScan
@SpringBootApplication
public class DruidDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DruidDemoApplication.class, args);
  }
}
