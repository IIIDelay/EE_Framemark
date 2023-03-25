package org.boot.config;

import org.boot.configuration.manger.ConfigManger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * WebConfiguration
 *
 * @author IIIDelay
 * @createTime 2023年03月10日 10:45:00
 */
@Configuration
@EnableOpenApi
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false);
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("Spring Boot集成 Swagger2接口总览")
                // 设置页面描述
                .description("这是一个简短的页面描述")
                // 设置联系方式
                .contact(new Contact("IIIDev", "http:localhost", "xx.com"))
                // 设置版本
                .version("0.1")
                // 构建
                .build();
    }

    /**
     * createRestApi : swagger 3.0 配置
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi(ConfigManger configManger) {
        return new Docket(DocumentationType.OAS_30)
                .enable(configManger.getCoustomConfig().isEnableSwagger())
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("org.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
