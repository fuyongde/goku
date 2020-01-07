package com.sunflower.goku.druid.demo.rest;

import com.sunflower.goku.druid.demo.concurrent.MyLock;
import com.sunflower.goku.druid.demo.entity.Product;
import com.sunflower.goku.druid.demo.repository.ProductMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fuyongde
 * @date 2020/1/7 21:56
 */
@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {

    @Resource
    private ProductMapper productMapper;

    private MyLock lock = new MyLock();

    @GetMapping("/{id}")
    public String decr(@PathVariable Integer id, @RequestParam(defaultValue = "1") Integer decrement) {
        lock.lock();
        Product product = productMapper.getById(id);
        if (product.getStock() <= 0) {
            throw new RuntimeException("没有库存了");
        }
        productMapper.decr(id, decrement);
        lock.unlock();
        return "Success";
    }

}
