package org.img.entity;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Admin
 *
 * @author IIIDelay
 * @createTime 2023年03月18日 10:35:00
 */
@Component
public class Admin implements InitializingBean {
    private User name;

    // public Admin() {
    //     System.out.println("无参。。。");
    // }

    public Admin(User name) {
        this.name = name;
        System.out.println("name = " + name);
        System.out.println("有参。。。");
    }

    public void start() {
        AopContext.currentProxy();
        System.out.println("start ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("未执行方法 afterPropertiesSet => ");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct... ");
    }
}
