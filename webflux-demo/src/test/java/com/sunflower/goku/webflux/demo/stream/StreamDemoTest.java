package com.sunflower.goku.webflux.demo.stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fuyongde
 * @date 2019/6/11
 */
public class StreamDemoTest {

    StreamDemo streamDemo;

    @Before
    public void setUp() throws Exception {
        streamDemo = new StreamDemo();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void method1() {
        streamDemo.method1();
    }

    @Test
    public void method2() {
        streamDemo.method2();
    }

    @Test
    public void method3() {
        streamDemo.method3();
    }
}