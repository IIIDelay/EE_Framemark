package org.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.plus.**.dao")
@SpringBootApplication
public class MybatisPlusDomain {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDomain.class, args);
    }
}
