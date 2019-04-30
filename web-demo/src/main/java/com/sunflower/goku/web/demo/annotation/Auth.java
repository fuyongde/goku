package com.sunflower.goku.web.demo.annotation;

import java.lang.annotation.*;

/**
 * @author fuyongde
 * @date 2019/4/30
 * @desc TODO add description in here
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    String[] hasAnyPermission();

}
