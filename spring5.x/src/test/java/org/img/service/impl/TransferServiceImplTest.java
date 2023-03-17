package org.img.service.impl;

import org.img.config.DefaultBeanInitialing;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TransferServiceImplTest
 *
 * @author IIIDelay
 * @createTime 2023年03月17日 12:35:00
 */
public class TransferServiceImplTest {

    @Test
    public void testTransfer() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DefaultBeanInitialing.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}