package org.boot;

import org.boot.compoment.MySelect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisDomain
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 12:32:00
 */
@SpringBootApplication
@Import(MySelect.class)
public class RedisDomain {
    RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisDomain.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        String s = context.getBean("S", String.class);
    }


    // @Bean
    // public CommandLineRunner commandLineRunner() {
    //     return args -> {
    //         for (String arg : args) {
    //             System.out.println("arg = " + arg);
    //         }
    //     };
    // }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("args.getNonOptionArgs() = " + args.getNonOptionArgs());
            System.out.println("args.getOptionNames() = " + args.getOptionNames());
            for (String sourceArg : args.getSourceArgs()) {
                System.out.println("sourceArg = " + sourceArg);
            }
        };
    }
}
