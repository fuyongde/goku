package com.sunflower.goku.dubbo.consumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunflower.bulma.tools.BeanMapper;
import com.sunflower.goku.dubbo.api.rpc.RegionRpc;
import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;
import com.sunflower.goku.dubbo.consumer.rest.vo.RegionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(RegionRestController.class);

    @Reference(timeout = 20000)
    private RegionRpc regionRpc;

    @GetMapping(value = "/{id}")
    public RegionVO region(@PathVariable Long id) {
        logger.info("获取区域信息");
        RegionDTO regionDTO = regionRpc.region(id);
        logger.info("获取区域信息成功");
        RegionVO regionVO = BeanMapper.map(regionDTO, RegionVO.class);
        return regionVO;
    }
}
