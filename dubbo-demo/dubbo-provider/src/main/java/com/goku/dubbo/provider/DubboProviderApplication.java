package com.goku.dubbo.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * @author fuyongde
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo-provider.xml"})
public class DubboProviderApplication {

  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = new SpringApplicationBuilder()
        .sources(DubboProviderApplication.class)
        .web(false)
        .run(args);

    CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
    closeLatch.await();
  }

  @Bean
  public CountDownLatch countDownLatch() {
    return new CountDownLatch(1);
  }
}
