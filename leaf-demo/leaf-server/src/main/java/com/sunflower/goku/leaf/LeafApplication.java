package com.sunflower.goku.leaf;

import com.sunflower.goku.leaf.config.LeafConfig;
import com.sunflower.goku.leaf.generator.SnowFlake;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.concurrent.CountDownLatch;

/**
 * @author fuyongde
 * @date 2018/10/31
 */
@SpringBootApplication
public class LeafApplication {

    public static void main(String[] args) throws InterruptedException {
        if (SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
        }

        ApplicationContext ctx = new SpringApplicationBuilder()
                .listeners(new ApplicationPidFileWriter())
                .sources(LeafApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }

    @Autowired
    private LeafConfig leafConfig;

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(leafConfig.getNode());
    }

    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }
}
