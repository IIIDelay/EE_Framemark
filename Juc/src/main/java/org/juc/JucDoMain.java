package org.juc;

import org.boot.configuration.TaskConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * JucDoMain
 *
 * @author IIIDelay
 * @createTime 2023年04月12日 15:45:00
 */
@SpringBootApplication(scanBasePackages = {"org.boot.*"})
@Import(TaskConfig.class)
public class JucDoMain {
    public static void main(String[] args) {
        SpringApplication.run(JucDoMain.class, args);
    }
}
