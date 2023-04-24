package org.img.service.impl;

import org.img.config.DefaultBeanInitialing;
import org.img.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext context1 = new ClassPathXmlApplicationContext("");
        Admin admin = context.getBean("admin", Admin.class);
        admin.start();
    }
}