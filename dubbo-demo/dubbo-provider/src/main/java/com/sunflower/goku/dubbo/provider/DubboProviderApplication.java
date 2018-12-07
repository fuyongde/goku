package com.sunflower.goku.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fuyongde
 */
@SpringBootApplication
@EnableScheduling
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DubboProviderApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
