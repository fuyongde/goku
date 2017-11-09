package com.goku.dubbo.provider.manager;

import com.goku.dubbo.api.exception.ServiceException;
import com.goku.dubbo.commons.utils.EmailChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author fuyongde
 * @desc 邮件服务
 * @date 2017/11/9 18:44
 */
@Component
public class EmailManager {

    private static Logger logger = LoggerFactory.getLogger(EmailManager.class);

    @Autowired
    private MailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Async
    public void sendMail(String to, String subject, String text) {
        Assert.notNull(to, "Receiver is Null");
        Assert.notNull(subject, "Subject is Null");
        Assert.notNull(text, "Content is Null");

        boolean isEmail = EmailChecker.isEmail(to);
        if (!isEmail) {
            throw new ServiceException(120001);
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            if(logger.isInfoEnabled()) {
                logger.error("发送邮件失败。接收方：{}，主题：{}，内容：{}", to, subject, text);
            }
        }
    }
}
