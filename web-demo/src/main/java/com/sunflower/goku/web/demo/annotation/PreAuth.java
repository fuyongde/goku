package com.sunflower.goku.web.demo.annotation;

import java.lang.annotation.*;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc 授权认证
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuth {

    String[] hasAnyPermission();

}
