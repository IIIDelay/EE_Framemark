package org.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfiguration
 *
 * @author IIIDelay
 * @createTime 2023年03月10日 10:45:00
 */
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("Spring Boot集成 Swagger2接口总览")
                // 设置页面描述
                .description("这是一个简短的页面描述")
                // 设置联系方式
                .contact(new Contact("IIIDev", "http:localhost","xx.com"))
                // 设置版本
                .version("0.1")
                // 构建
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("org.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
