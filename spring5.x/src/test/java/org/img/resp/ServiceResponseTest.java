package org.img.resp;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Arrays;
import java.util.Objects;

/**
 * ServiceResponseTest
 *
 * @author IIIDelay
 * @createTime 2023年03月06日 10:19:00
 */
public class ServiceResponseTest {
    @Test
    public void testOne() {
        Resource[] exec = exec("mapper/*Mapper.xml");
        System.out.println("exec = " + exec);
    }

    private Resource[] exec(String ...ins) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources =
                Arrays.stream(ins)
                        .filter(Objects::nonNull)
                        .map(in -> Arrays.stream(Try.of(() -> resolver.getResources(in))
                                .onSuccess(resources1 -> System.out.println("success..."))
                                .onFailure(ex-> System.out.println("failed...")).getOrElse(new Resource[]{})))
                        .toArray(Resource[]::new);
        return resources;
    }
}