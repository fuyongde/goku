package com.goku.dubbo.commons.generator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SnowFlake Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 2, 2018</pre>
 */
public class SnowFlakeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: next()
     */
    @Test
    public void testNext() throws Exception {
        SnowFlake snowFlake = new SnowFlake(1);
        for (int i = 0; i < 10000; i++) {
            System.out.println(snowFlake.next());
        }
    }

} 
