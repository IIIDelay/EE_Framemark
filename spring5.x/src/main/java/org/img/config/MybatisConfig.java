package org.img.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.iiidev.wrapper.CheckedFunction;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
 * @createTime 2023年03月04日 20:38:00
 */
@MapperScan(basePackages = {"org.img.mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryBean")
public class MybatisConfig {
    /**
     * sqlSessionFactoryBean
     *
     * @param dataSource dataSource
     * @return SqlSessionFactoryBean
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTypeAliasesPackage("org.img.*.pojo");
        bean.setDataSource(dataSource);
        bean.setConfiguration(buildConfiguration());
        bean.setMapperLocations(buildResources(new PathMatchingResourcePatternResolver()::getResources,
                IOException.class, "mapper/**/*Mapper.xml"));
        bean.setPlugins();
        return bean;
    }

    private Interceptor[] getInterceptors() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        return ArrayUtils.add(new Interceptor[]{}, pageInterceptor);
    }

    private Resource[] buildResources(CheckedFunction<String, Resource[], IOException> function,
                                      Class<IOException> exClazz, String... localPath) {
        return Arrays.stream(localPath).map(CheckedFunction.exceptionWrapper(exClazz, function)::apply).flatMap(Arrays::stream).toArray(Resource[]::new);
    }

    private Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }
}
