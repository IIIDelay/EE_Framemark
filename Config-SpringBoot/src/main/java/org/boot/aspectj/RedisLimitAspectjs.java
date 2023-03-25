package org.boot.aspectj;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.iiidev.common.annotation.RedisLimit;
import org.iiidev.common.exception.ServiceRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * RedisLimitAspectjs
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 23:33:00
 */
@Component
@Aspect
@Slf4j
public class RedisLimitAspectjs {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@within(org.iiidev.common.annotation.RedisLimit)")
    private void check() {
    }

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }

    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //拿到RedisLimit注解，如果存在则说明需要限流
        RedisLimit redisLimitAnno = method.getAnnotation(RedisLimit.class);

        Optional.ofNullable(redisLimitAnno).ifPresent(redisLimit -> {
            //获取redis的key
            String key = redisLimit.key();
            String className = method.getDeclaringClass().getName();

            String limitKey = key + className + method.getName();

            log.info(limitKey);

            if (StringUtils.isEmpty(key)) {
                throw ServiceRuntimeException.of("key cannot be null");
            }

            long limit = redisLimit.permitsPerSecond();

            long expire = redisLimit.expire();

            Long count = redisTemplate.execute(redisScript, Lists.newArrayList(key), String.valueOf(limit),
                    String.valueOf(expire));

            log.info("Access try count is {} for key= {}", count, key);
            Optional.ofNullable(count).filter(val -> val != 0).orElseThrow(() -> {
                log.debug("获取key失败，key为{}", key);
                throw ServiceRuntimeException.of(redisLimit.msg());
            });
        });
    }
}
