package com.goku.dubbo.provider.inner;

import com.alibaba.dubbo.config.annotation.Service;
import com.goku.dubbo.api.service.RegionService;
import com.goku.dubbo.api.service.dto.RegionDTO;
import com.goku.dubbo.provider.service.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fuyongde
 * @desc RegionService
 * @date 2017/12/30 18:36
 */
@Service
public class RegionServiceInner implements RegionService {

  @Autowired
  private RegionServiceImpl regionService;

  @Override
  public RegionDTO region(Integer id) {
      return regionService.region(id);
  }
}
