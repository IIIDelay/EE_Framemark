package org.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * IIIDevBeanDefinition
 *
 * @author IIIDelay
 * @createTime 2023年04月03日 23:56:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IIIDevBeanDefinition <IN>{
    /**
     * clazz
     */
    private Class<IN> clazz;

    /**
     * scope
     */
    private String scope;

    /**
     * isLazy
     */
    private boolean isLazy;
}
