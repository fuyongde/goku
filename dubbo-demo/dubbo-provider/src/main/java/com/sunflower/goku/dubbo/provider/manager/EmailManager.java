package com.sunflower.goku.dubbo.provider.manager;

import com.google.common.base.Preconditions;
import com.sunflower.bulma.tools.EmailChecker;
import com.sunflower.goku.dubbo.api.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * @author fuyongde
 * @desc 邮件服务
 * @date 2017/11/9 18:44
 */
@Component
public class EmailManager {

    private static Logger logger = LoggerFactory.getLogger(EmailManager.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Async
    public void sendMail(String to, String subject, String text) {
        Preconditions.checkArgument(StringUtils.isBlank(to), "Receiver is Null");
        Preconditions.checkArgument(StringUtils.isBlank(subject), "Subject is Null");
        Preconditions.checkArgument(StringUtils.isBlank(text), "Content is Null");
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
            if (logger.isInfoEnabled()) {
                logger.error("发送邮件失败。接收方：{}，主题：{}，内容：{}", to, subject, text);
            }
        }
    }

    @Async
    public void sendMailWithFile(String[] toArray, String[] ccArray, String subject, String text, File[] files) {
        Preconditions.checkArgument(Objects.nonNull(toArray), "receiver is empty");
        Preconditions.checkArgument(Objects.nonNull(subject), "Subject is Null");
        Preconditions.checkArgument(Objects.nonNull(text), "Content is Null");
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            for (String to : toArray) {
                helper.addTo(to);
            }
            if (ccArray != null && ccArray.length > 0) {
                for (String cc : ccArray) {
                    helper.addCc(cc);
                }
            }
            if (files != null && files.length > 0) {
                for (File file : files) {
                    FileDataSource fileDataSource = new FileDataSource(file);
                    helper.addAttachment(file.getName(), fileDataSource);
                }
            }
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.error("发送邮件失败。接收方：{}，主题：{}，内容：{}", toArray, subject, text);
            }
            e.printStackTrace();
        }
    }
}
