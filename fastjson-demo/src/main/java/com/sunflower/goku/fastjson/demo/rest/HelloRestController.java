package com.sunflower.goku.fastjson.demo.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 */
@RestController
@RequestMapping(value = "/api/hello")
@Slf4j
public class HelloRestController {

    @GetMapping
    public Hello hello() {
        log.info("hello");
        return new Hello(1, "Hello");
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Hello {
    @JSONField(serialize = false)
    Integer id;
    String name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
