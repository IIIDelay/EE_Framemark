package org.img.config;

import org.img.common.DSTypeContainer;
import org.img.common.attribute.DataSourceAttr;
import org.img.common.constant.DatabaseConstant;
import org.img.common.proxy.RoutingMultiDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * DefaultBeanInitialing
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 20:39:00
 */
@Import({MybatisConfig.class})
@ComponentScan("org.img")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class DefaultBeanInitialing {
    /**
     * DynamicDataSource 动态数据源注解方式
     *
     * @param dataSourceAttr dataSourceAttr
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSource DynamicDataSource(DataSourceAttr dataSourceAttr) {
        RoutingMultiDataSource rmd = new RoutingMultiDataSource();
        Map<Object, Object> dsMap = new HashMap<Object, Object>() {{
            put(DatabaseConstant.DATA_TYPE_MYSQL, dataSourceAttr.mysql());
            put(DatabaseConstant.DATA_TYPE_PG, dataSourceAttr.pg());
        }};
        rmd.setTargetDataSources(dsMap);
        rmd.setDefaultTargetDataSource(Optional.ofNullable(dsMap.get(DSTypeContainer.get())).orElse(dataSourceAttr.mysql()));
        return rmd;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
