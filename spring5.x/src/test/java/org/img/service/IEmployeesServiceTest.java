package org.img.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.iiidev.utils.JsonUtil;
import org.img.config.DefaultBeanInitialing;
import org.img.entity.TbEmployees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * IEmployeesServiceTest
 *
 * @author IIIDelay
 * @createTime 2023年03月19日 21:40:00
 */
@ContextConfiguration(classes = {DefaultBeanInitialing.class})
@ExtendWith(SpringExtension.class)
public class IEmployeesServiceTest {
    @Autowired
    private IEmployeesService employeesService;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void testQueryEmps()  {
        List<TbEmployees> extracted = extracted();
        List<TbEmployees> collect =
                extracted.stream().collect(Collectors.collectingAndThen(Collectors.toMap(TbEmployees::getEmployeeId,
                Function.identity(), (k1,k2)->k2), in -> Lists.newArrayList(in.values())));

        System.out.println("===================== Result +++++++++++++++++++++");
        collect.forEach(System.out::println);
        System.out.println("extracted.size() = " + collect.size());
    }

    private List<TbEmployees> extracted() {
        List<TbEmployees> tbEmployees = employeesService.queryEmps();
        List<TbEmployees> data = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture.runAsync(() -> {
            int initVal = 0;
            while (true) {
                // TbEmployees emp = tbEmployees.get(0);
                TbEmployees emp = JsonUtil.StrToObj(JsonUtil.objToStr(tbEmployees.get(0)), TbEmployees.class);

                String employeeId = String.valueOf(RandomUtil.randomInt(155, 2100000000));
                emp.setEmployeeId(employeeId);
                emp.setFirstName(RandomUtil.randomString(8));
                emp.setLastName(RandomUtil.randomString(8));
                data.add(emp);
                atomicInteger.compareAndSet(initVal, ++initVal);

                System.out.println("        AAAA---          " + Thread.currentThread().getName());
                if (initVal >= 100000) {
                    break;
                }
            }
        }, executorService).thenRunAsync(()->{
            while (true) {
                // TbEmployees emp = tbEmployees.get(0);
                TbEmployees emp = JsonUtil.StrToObj(JsonUtil.objToStr(tbEmployees.get(0)), TbEmployees.class);

                String employeeId = String.valueOf(RandomUtil.randomInt(155, 2100000000));
                emp.setEmployeeId(employeeId);
                emp.setFirstName(RandomUtil.randomString(8));
                emp.setLastName(RandomUtil.randomString(8));
                data.add(emp);
                int count = atomicInteger.incrementAndGet();

                System.out.println("        BBBB---          " + Thread.currentThread().getName());
                if (count >= 100000) {
                    break;
                }
            }
        }).join();


        // CompletableFuture.allOf(
        //         CompletableFuture.runAsync(() -> {
        //             while (true) {
        //                 TbEmployees emp = tbEmployees.get(0);
        //                 emp.setEmployeeId(String.valueOf(RandomUtil.randomInt(155, 99998)));
        //                 emp.setFirstName(UUID.fastUUID().toString().substring(0, 6));
        //                 emp.setLastName(UUID.fastUUID().toString().substring(0, 6));
        //                 data.add(emp);
        //                 int count = atomicInteger.getAndIncrement();
        //                 System.out.println("        AAAA---          "+Thread.currentThread().getName());
        //                 if (count >= 100) {
        //                     break;
        //                 }
        //             }
        //         }),
        //         CompletableFuture.runAsync(() -> {
        //             while (true) {
        //                 TbEmployees emp = tbEmployees.get(0);
        //                 emp.setEmployeeId(String.valueOf(RandomUtil.randomInt(155, 99998)));
        //                 emp.setFirstName(UUID.fastUUID().toString().substring(0, 6));
        //                 emp.setLastName(UUID.fastUUID().toString().substring(0, 6));
        //                 data.add(emp);
        //                 int count = atomicInteger.getAndIncrement();
        //                 System.out.println("        bbbb---          "+Thread.currentThread().getName());
        //
        //                 if (count >= 100) {
        //                     break;
        //                 }
        //             }
        //         }),
        //         CompletableFuture.runAsync(() -> {
        //             while (true) {
        //                 TbEmployees emp = tbEmployees.get(0);
        //                 emp.setEmployeeId(String.valueOf(RandomUtil.randomInt(155, 99998)));
        //                 emp.setFirstName(UUID.fastUUID().toString().substring(0, 6));
        //                 emp.setLastName(UUID.fastUUID().toString().substring(0, 6));
        //                 data.add(emp);
        //                 int count = atomicInteger.getAndIncrement();
        //                 System.out.println("        ccccc---          "+Thread.currentThread().getName());
        //
        //                 if (count >= 100) {
        //                     break;
        //                 }
        //             }
        //         })
        // ).join();

        return data;
    }
}