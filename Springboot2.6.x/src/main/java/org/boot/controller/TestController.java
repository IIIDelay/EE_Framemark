package org.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author IIIDelay
 * @createTime 2023年03月10日 10:46:00
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("one")
    public String onexx(String name) {
        return "名字是: " + name;
    }
}
