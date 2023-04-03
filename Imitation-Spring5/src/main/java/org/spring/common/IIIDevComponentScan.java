package org.spring.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IIIDevComponentScan
 *
 * @author IIIDelay
 * @createTime 2023年04月03日 23:42:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IIIDevComponentScan {
    String[] value() default {};
}
