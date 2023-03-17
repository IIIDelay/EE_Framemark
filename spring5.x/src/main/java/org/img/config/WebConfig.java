package org.img.config;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 23:43:00
 */
@EnableWebMvc // 替代: <mvc:annotation-driven/>, 否则不能进行json与pojo之间互转
public class WebConfig implements WebMvcConfigurer {

}
