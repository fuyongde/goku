package com.sunflower.goku.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunflower.goku.dubbo.api.service.RegionRpc;
import com.sunflower.goku.dubbo.api.service.dto.RegionDTO;
import com.sunflower.goku.dubbo.provider.DubboProviderApplicationTests;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * RegionRpcImpl Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>10/31/2017</pre>
 */
public class RegionRpcImplTest extends DubboProviderApplicationTests {

    @Reference
    private RegionRpc regionRpc;

    /**
     * Method: region(Integer id)
     */
    @Test
    public void testRegion() throws Exception {
        RegionDTO regionDTO = regionRpc.region(110000);
        assertEquals("北京市", regionDTO.getName());
    }

} 
