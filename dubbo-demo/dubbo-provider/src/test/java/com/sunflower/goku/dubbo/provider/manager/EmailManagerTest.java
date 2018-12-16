package com.sunflower.goku.dubbo.provider.manager;

import com.sunflower.goku.dubbo.provider.DubboProviderApplicationTests;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * EmailManager Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/09/2017</pre>
 */
public class EmailManagerTest extends DubboProviderApplicationTests {

    @Autowired
    private EmailManager emailManager;

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    /**
     * Method: sendMail(String from, String to, String subject, String text)
     */
    @Test
    public void testSendMail() {
        String to = "fuyongde@dafy.com";
        String subject = "Goku变身";
        String text = "自在极意功";
        emailManager.sendMail(to, subject, text);
    }

    /**
     * Method: sendMail(String from, String to, String subject, String text)
     */
    @Test
    public void testSendMailWithFiles() throws Exception {
        String[] toArray = new String[]{"fuyongde@zhuifintech.com"};
        String[] ccArray = new String[]{"fuyongde@foxmail.com"};
        String subject = "Goku变身";
        String text = "自在极意功";
        File file = FileUtils.getFile("C:\\Users\\fuyongde\\Desktop", "滴滴出行行程报销单滴滴出行行程报销单.pdf");
        File chinestFile = FileUtils.getFile("C:\\Users\\fuyongde\\Desktop", "滴滴出行行程报销单滴滴出行行程报销单.xlsx");
        emailManager.sendMailWithFile(toArray, ccArray, subject, text, new File[]{file, chinestFile});
    }

} 
