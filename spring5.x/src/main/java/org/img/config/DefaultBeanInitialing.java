package org.img.config;

import org.img.attribute.DataSourceAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * DefaultBeanInitialing
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 20:39:00
 */
@Import({MybatisConfig.class})
@ComponentScan("org.img")
public class DefaultBeanInitialing {
    @Autowired
    private DataSourceAttr dataSourceAttr;

    @Bean
    public DataSource dataSourceForMysql() {
        return dataSourceAttr.mysql();
    }

    @Bean
    @Primary
    public DataSource dataSourceForPG() {
        return dataSourceAttr.pg();
    }
}
