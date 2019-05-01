package com.sunflower.goku.web.demo.context;

import com.sunflower.goku.web.demo.annotation.PreAuth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author fuyongde
 */
@Aspect
@Component
public class AuthAop {

    private static final Logger logger = LoggerFactory.getLogger(AuthAop.class);

    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.sunflower.goku.web.demo.rest..*.*(..)) && args(..)")
    public void executeService() {
    }

    @Around("executeService()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            PreAuth preAuth = method.getAnnotation(PreAuth.class);
            if (Objects.nonNull(preAuth)) {
                String[] permissionArray = preAuth.hasAnyPermission();
                boolean hasPermission = false;
                if (permissionArray.length > 0) {
                    List<String> currentPermissions = AuthInterceptor.permissions.get();
                    if (!CollectionUtils.isEmpty(currentPermissions)) {
                        List<String> needPermissionList = Arrays.asList(permissionArray);
                        // 两个集合有交集，则说明有该方法的权限
                        hasPermission = currentPermissions.retainAll(needPermissionList);
                    }
                }
                if (!hasPermission) {
                    throw new RuntimeException("权限不足");
                }
            }
        }

        Object result = joinPoint.proceed();
        return result;
    }


}
