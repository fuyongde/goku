package com.sunflower.goku.druid.demo.rest;

import com.sunflower.goku.druid.demo.DruidDemoApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author fuyongde
 * @date 2018-12-07
 * @desc 测试
 */
public class RegionRestControllerTest extends DruidDemoApplicationTests {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRegionById() throws Exception {
        super.mockMvc.perform(get("/api/regions/110000").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name").value("北京市"))
                .andDo(MockMvcResultHandlers.print());
    }
}