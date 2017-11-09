package com.goku.dubbo.commons.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * EmailChecker Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/09/2017</pre>
 */
public class EmailCheckerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: isEmail(String email)
     */
    @Test
    public void testIsEmail() throws Exception {
        String email = "fuyongde@foxmail.com, fuyongde@163.com";
        boolean isEmail = EmailChecker.isEmail(email);
        assertTrue(isEmail);
    }


} 
