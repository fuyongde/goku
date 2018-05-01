package com.goku.dubbo.provider.job;

import com.goku.dubbo.commons.consts.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimpleJob {

  @Scheduled(initialDelay = 1000L, fixedRate = 500000L)
  public void reportCurrentTime() {
    System.out.println("现在时间：" + DateFormatUtils.format(new Date(), DatePattern.PATTERN_1));
  }

  @Scheduled(cron="*/50000 * * * * *")
  public void sendMail() {
    System.out.println("发送邮件");
  }
}
