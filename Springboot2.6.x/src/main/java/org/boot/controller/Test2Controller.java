package org.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test2Controller
 *
 * @author IIIDelay
 * @createTime 2023年03月10日 11:23:00
 */
@RequestMapping("test2")
@RestController
public class Test2Controller {
    @GetMapping("two")
    public String two(String age) {
        return "two"+age;
    }
}
