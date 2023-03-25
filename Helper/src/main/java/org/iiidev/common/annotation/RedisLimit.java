package org.iiidev.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RedisLimit
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 23:30:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RedisLimit {
    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     */
    long permitsPerSecond() default 2;

    /**
     * 过期时间也可以理解为单位时间，单位秒，默认60
     */
    long expire() default 60;

    /**
     * 得不到令牌的提示语
     */
    String msg() default "系统繁忙,请稍后再试.";
}
