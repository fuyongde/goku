package com.goku.dubbo.commons.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * BeanMapper Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>10/31/2017</pre>
 */
public class UnirestTest {

    public static final String URL_BAIDU = "http://www.baidu.com";

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        HttpResponse response = Unirest.get(URL_BAIDU).asBinary();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testPost() throws Exception {
        HttpResponse response = Unirest.post(URL_BAIDU)
                .field("name", "fuyongde")
                .asBinary();
        assertEquals(302, response.getStatus());
    }

    @Test
    public void testBug() throws Exception {
        HttpResponse response = Unirest
                .get("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/11.html")
                .asBinary();
        String result = IOUtils.toString(response.getRawBody(), "GB2312");
        System.out.println(result);
    }
} 
