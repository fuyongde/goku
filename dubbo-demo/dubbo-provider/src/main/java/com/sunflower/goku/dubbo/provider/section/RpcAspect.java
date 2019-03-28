package com.sunflower.goku.dubbo.provider.section;

import com.alibaba.fastjson.JSON;
import com.sunflower.goku.dubbo.api.CommonResponse;
import org.apache.dubbo.rpc.RpcResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author fuyonde
 * 日志系统
 */
@Aspect
@Component
public class RpcAspect {

    private static final Logger logger = LoggerFactory.getLogger(RpcAspect.class);

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(public * com.sunflower.goku.dubbo.provider.rpc..*.*(..))")
    public void rpc() {
    }

    @Around("rpc()")
    public CommonResponse process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        Object[] params = proceedingJoinPoint.getArgs();
        // 记录下请求内容
        logger.info("=============================start====================================");
        logger.info("METHOD: {}", proceedingJoinPoint.getSignature());
        if (params != null && params.length > 0) {
            logger.info("PARAMS : {}", JSON.toJSONString(params));
        }

        CommonResponse commonResponse;
        try {
            commonResponse = (CommonResponse) proceedingJoinPoint.proceed();
        } catch (Exception e) {
            commonResponse = new CommonResponse(e.getMessage(), e);
        }
        logger.info("RESULT : {}", JSON.toJSONString(commonResponse));
        logger.info("SPEND TIME : {}ms", (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
        return commonResponse;
    }
}
