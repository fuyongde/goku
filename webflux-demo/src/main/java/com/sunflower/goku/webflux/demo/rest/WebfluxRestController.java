package com.sunflower.goku.webflux.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author fuyongde
 * @date 2019/6/11
 */
@RestController
@RequestMapping("/api/webflux")
public class WebfluxRestController {

    @GetMapping
    public Mono<String> hello(@RequestParam Long delay) {
        return Mono.just("Hello World").delayElement(Duration.ofMillis(delay));
    }
}
