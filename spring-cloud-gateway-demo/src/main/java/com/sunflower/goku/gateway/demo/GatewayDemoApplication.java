package com.sunflower.goku.gateway.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fuyongde
 */
@SpringBootApplication
public class GatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayDemoApplication.class, args);
    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_route", r -> r.path("/api/hello").uri("http://localhost:8081/api/hello"))
//                .route("path_route", r -> r.path("/a").uri("https://www.baidu.com/"))
//                .build();
//    }
}
