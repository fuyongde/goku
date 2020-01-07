package com.sunflower.goku.druid.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2020/1/7 21:47
 */
@Data
public class Product implements Serializable {

    private int id;
    private int stock;

}
