package com.goku.dubbo.provider.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * EmailManager Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/09/2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailManagerTest {

    @Autowired
    private EmailManager emailManager;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sendMail(String from, String to, String subject, String text)
     */
    @Test
    public void testSendMail() throws Exception {
        String to = "fuyongde@dafy.com";
        String subject = "Goku变身";
        String text = "自在极意功";
        emailManager.sendMail(to, subject, text);
    }


} 
