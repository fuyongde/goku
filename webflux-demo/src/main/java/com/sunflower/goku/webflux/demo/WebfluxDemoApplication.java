package com.sunflower.goku.webflux.demo;

import com.sunflower.goku.webflux.demo.handler.EchoHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author fuyongde
 */
@SpringBootApplication
public class WebfluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EchoHandler echoHandler) {
        return route(GET("/webflux/echo"), echoHandler::echo);
    }
}
