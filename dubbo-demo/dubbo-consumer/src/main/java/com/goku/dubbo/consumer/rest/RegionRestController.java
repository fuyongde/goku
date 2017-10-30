package com.goku.dubbo.consumer.rest;

import com.goku.dubbo.api.service.RegionService;
import com.goku.dubbo.api.service.dto.RegionDTO;
import com.goku.dubbo.commons.utils.BeanMapper;
import com.goku.dubbo.consumer.rest.vo.RegionVO;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private RegionService regionService;

  @GetMapping(value = "/{id}")
  public RegionVO region(@PathVariable Integer id) {
    RegionDTO regionDTO = regionService.region(id);
    RegionVO regionVO = BeanMapper.map(regionDTO, RegionVO.class);
    return regionVO;
  }
}
