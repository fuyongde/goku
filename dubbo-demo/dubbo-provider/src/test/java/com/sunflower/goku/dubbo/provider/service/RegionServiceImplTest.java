package com.sunflower.goku.dubbo.provider.service;

import com.sunflower.goku.dubbo.api.service.RegionService;
import com.sunflower.goku.dubbo.api.service.dto.RegionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RegionServiceImpl Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>10/31/2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceImplTest {

    @Autowired
    private RegionService regionService;

    /**
     * Method: region(Integer id)
     */
    @Test
    public void testRegion() throws Exception {
        RegionDTO regionDTO = regionService.region(110000);
        assertEquals("北京市", regionDTO.getName());
    }

} 
