package com.sunflower.goku.dubbo.provider.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sunflower.bulma.tools.BeanMapper;
import com.sunflower.goku.dubbo.api.exception.ServiceException;
import com.sunflower.goku.dubbo.api.rpc.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.entity.Region;
import com.sunflower.goku.dubbo.provider.repository.RegionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author fuyongde
 * @date 12/16/2018 2:38 PM
 * @desc 地区服务
 */
@Service
public class RegionService implements InitializingBean {

    private static final Cache<Long, Region> ALL_REGION_CACHE = CacheBuilder.newBuilder().build();
    private static final Cache<Long, Region> REGION_CACHE = CacheBuilder.newBuilder().maximumSize(1000).build();
    private static final Cache<Long, List<Region>> REGION_LIST_CACHE = CacheBuilder.newBuilder().maximumSize(1000).build();
    private static Logger logger = LoggerFactory.getLogger(RegionService.class);
    @Resource
    private RegionMapper regionMapper;

    public RegionDTO getById(Long id) {
        try {
            Region region = REGION_CACHE.get(id, () -> regionMapper.getById(id));
            RegionDTO regionDTO = BeanMapper.map(region, RegionDTO.class);
            if (!region.getLeaf()) {
                List<Region> regions = getByParent(id);
                List<RegionDTO> regionDTOS = BeanMapper.mapList(regions, RegionDTO.class);
                regionDTO.setChildren(regionDTOS);
            }
            return regionDTO;
        } catch (ExecutionException e) {
            throw new ServiceException(10000, "根据id获取数据失败" + id);
        }
    }

    public List<Region> getByParent(Long parentId) {
        List<Region> regions = null;
        try {
            regions = REGION_LIST_CACHE.get(parentId, () -> regionMapper.getByParentId(parentId));
            return regions;
        } catch (ExecutionException e) {
            throw new ServiceException(10000, "根据父级获取数据失败" + parentId);
        }
    }

    @Override
    public void afterPropertiesSet() {
        Iterable<Region> allRegions = regionMapper.getAll();
        logger.info("=======================Load all Region to cache start==========================");
        allRegions.forEach(region -> ALL_REGION_CACHE.put(region.getId(), region));
        logger.info("=======================Load {} Region to cache end============================", ALL_REGION_CACHE.size());
    }
}
