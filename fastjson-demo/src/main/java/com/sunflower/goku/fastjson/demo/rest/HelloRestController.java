package com.sunflower.goku.fastjson.demo.rest;

import com.alibaba.fastjson.annotation.JSONField;
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
  Hello hello() {
    return new Hello(1, "Hello");
  }
}

class Hello {
  @JSONField(serialize = false)
  Integer id;
  String name;

  public Hello(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
