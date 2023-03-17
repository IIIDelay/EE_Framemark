package org.img.common.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.iiidev.utils.AttrTransferUtil;
import org.img.common.DSTypeContainer;
import org.img.common.annotation.DSType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * MultiDataSourceAspect
 *
 * @author IIIDelay
 * @createTime 2023年03月17日 19:29:00
 */
@Aspect
@Component
public class MultiDataSourceAspect {
    @Around("@within(org.img.common.annotation.DSType)")
    public Object injectDbType(ProceedingJoinPoint joinPoint) {
        try {
            // 优先取方法的ds类型，在取类上的
            DSType dsType = Optional.ofNullable(getAnnoInfoForMethod(joinPoint, DSType.class))
                    .orElse(getAnnoInfoForType(joinPoint, DSType.class));
            DSTypeContainer.set(AttrTransferUtil.safeGetter(dsType, DSType::value));
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            DSTypeContainer.clear();
        }
    }

    private <OUT extends Annotation> OUT getAnnoInfoForType(ProceedingJoinPoint joinPoint, Class<OUT> clazz) {
        return (OUT) joinPoint.getSignature().getDeclaringType().getAnnotation(clazz);
    }

    private <OUT extends Annotation> OUT getAnnoInfoForMethod(ProceedingJoinPoint joinPoint, Class<OUT> clazz) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(clazz);
    }
}
