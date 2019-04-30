package com.sunflower.goku.web.demo.rest;

import com.sunflower.goku.web.demo.entity.Region;
import com.sunflower.goku.web.demo.repository.RegionMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fuyongde
 */
@RestController
@RequestMapping(value = "/api/regions")
public class RegionRestController {

    @Resource
    private RegionMapper regionMapper;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Region getRegionById(@PathVariable("id") Integer id) {
        return regionMapper.findById(id);
    }
}
