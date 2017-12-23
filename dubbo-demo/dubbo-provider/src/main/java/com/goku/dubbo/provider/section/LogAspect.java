package com.goku.dubbo.provider.section;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * 日志系统
 */
@Aspect
@Component
public class LogAspect {

  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  private final ThreadLocal<Long> startTime = new ThreadLocal<>();

  @Pointcut(value = "execution(public * com.goku.dubbo.provider.service..*.*(..))")
  public void serviceLog() {
  }

  @Before("serviceLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    startTime.set(System.currentTimeMillis());

    Object[] params = joinPoint.getArgs();

    // 记录下请求内容
    logger.info("=============================start====================================");
    logger.info("METHOD: {}", joinPoint.getSignature());
    if (params!=null && params.length > 0) {
      logger.info("params : {}", params);
    }

  }

  @AfterReturning(returning = "result", pointcut = "serviceLog()")
  public void doAfterReturning(Object result) throws Throwable {
    // 处理完请求，返回内容
    logger.info("RESPONSE : {}", result);
    logger.info("SPEND TIME : {}ms", (System.currentTimeMillis() - startTime.get()));
    logger.info("=============================end======================================");
    startTime.remove();
  }
}
