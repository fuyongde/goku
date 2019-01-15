package com.sunflower.goku.yuebao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author fuyongde
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sunflower.goku.yuebao.repository")
public class YuebaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuebaoApplication.class, args);
    }
}
