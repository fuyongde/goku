package com.goku.dubbo.consumer.rest;

import com.goku.dubbo.api.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/regions")
public class RegionRestController {

    @Autowired
    private RegionService regionService;

    @GetMapping(value = "/{id}")
    public Object region(@PathVariable Integer id) {
        return regionService.region(id);
    }
}
