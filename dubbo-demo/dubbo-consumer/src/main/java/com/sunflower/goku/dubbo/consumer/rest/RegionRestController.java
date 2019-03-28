package com.sunflower.goku.dubbo.consumer.rest;

import com.sunflower.bulma.tools.BeanMapper;
import com.sunflower.goku.dubbo.api.CommonResponse;
import com.sunflower.goku.dubbo.api.exception.ServiceException;
import com.sunflower.goku.dubbo.api.rpc.RegionRpc;
import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;
import com.sunflower.goku.dubbo.consumer.rest.vo.RegionVO;
import org.apache.dubbo.config.annotation.Reference;
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

    @Reference(timeout = 300000)
    private RegionRpc regionRpc;

    @GetMapping(value = "/{id}")
    public CommonResponse<RegionVO> region(@PathVariable Long id) {
        CommonResponse<RegionDTO> commonResponse = regionRpc.region(id);
        if (commonResponse.isSuccess()) {
            RegionVO regionVO = BeanMapper.map(commonResponse.getValue(), RegionVO.class);
            return new CommonResponse<>(regionVO);
        } else {
            throw new ServiceException(commonResponse.getThrowable());
        }
    }
}
