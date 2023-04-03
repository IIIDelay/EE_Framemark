package org.spring.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IIIDevScope
 *
 * @author IIIDelay
 * @createTime 2023年04月04日 00:06:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface IIIDevScope {
    String value() default "";
}
