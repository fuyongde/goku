package com.sunflower.goku.dubbo.consumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunflower.bulma.tools.BeanMapper;
import com.sunflower.goku.dubbo.api.service.RegionRpc;
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
    private RegionRpc regionRpc;

    @GetMapping(value = "/{id}")
    public RegionVO region(@PathVariable Integer id) {
        RegionDTO regionDTO = regionRpc.region(id);
        RegionVO regionVO = BeanMapper.map(regionDTO, RegionVO.class);
        return regionVO;
    }
}
