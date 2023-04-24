package org.img.common.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * AdminAop
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 14:09:00
 */
@Aspect
@Component
public class AdminAop {
    @Pointcut("execution(* org.img.entity.Admin.start(..))")
    public void point() {

    }

    @Before("point()")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        System.out.println("before exec "+ signature.getMethod().getName());
    }

}
