package org.boot.configuration;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.iiidev.wrapper.CheckedFunction;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

/**
 * MybatisConfig
 *
 * @author IIIDelay
 * @createTime 2023年03月16日 11:26:00
 */
public class MybatisConfig {
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        // 注意: Mybatis-plus把SqlSessionFactoryBean给替换成了MybatisSqlSessionFactoryBean.
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("org.ymail.pojo");
        factoryBean.setConfiguration(buildConfiguration());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = buildMapperLocations(resolver::getResources, IOException.class, "mapper/**/*Mapper.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean;
    }

    private <IN, EX extends Throwable> Resource[] buildMapperLocations(CheckedFunction<IN, Resource[], EX> function,
                                                                            Class<EX> clazz, IN... localtions) {
        if (ArrayUtils.isEmpty(localtions)) {
            return new Resource[]{};
        }
        return Arrays.stream(localtions)
                .flatMap(local -> Arrays.stream(CheckedFunction.exceptionWrapper(clazz, function).apply(local)))
                .toArray(Resource[]::new);
    }

    private MybatisConfiguration buildConfiguration() {
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(StdOutImpl.class);
        return configuration;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        configurer.setBasePackage("org.ymail.mapper");
        return configurer;
    }
}
