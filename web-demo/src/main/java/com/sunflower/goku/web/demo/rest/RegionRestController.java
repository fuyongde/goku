package com.sunflower.goku.web.demo.rest;

import com.google.common.collect.Maps;
import com.sunflower.goku.web.demo.annotation.PreAuth;
import com.sunflower.goku.web.demo.entity.Region;
import com.sunflower.goku.web.demo.repository.RegionMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    @DeleteMapping(value = "/{id}")
    @PreAuth(hasAnyPermission = {"0"})
    public Map<String, Object> deleteRegionById(@PathVariable("id") Integer id) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("success", true);
        return result;
    }
}
