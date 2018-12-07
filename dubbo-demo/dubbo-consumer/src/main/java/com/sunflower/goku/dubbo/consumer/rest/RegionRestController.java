package com.sunflower.goku.dubbo.consumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.goku.dubbo.commons.utils.BeanMapper;
import com.sunflower.goku.dubbo.api.service.RegionService;
import com.sunflower.goku.dubbo.api.service.dto.RegionDTO;
import com.sunflower.goku.dubbo.consumer.rest.vo.RegionVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuyongde
 */
@RestController
@RequestMapping(value = "/api/regions")
public class RegionRestController {

    @Reference
    private RegionService regionService;

    @GetMapping(value = "/{id}")
    public RegionVO region(@PathVariable Integer id) {
        RegionDTO regionDTO = regionService.region(id);
        RegionVO regionVO = BeanMapper.map(regionDTO, RegionVO.class);
        return regionVO;
    }
}
