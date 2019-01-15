package com.sunflower.goku.zhifubao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fuyongde
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.sunflower.goku.zhifubao.repository")
public class ZhifubaoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZhifubaoApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
