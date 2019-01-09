package com.sunflower.goku.webflux.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 */
@RestController
@RequestMapping(value = "/api/hello")
public class HelloRestController {

    @GetMapping
    public String example() {
        return "Hello World";
    }
}
