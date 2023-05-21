package org.excel.controller;

import org.excel.pojo.User;
import org.excel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findPage")
    public List<User>  findPage(
            @RequestParam(value = "page",defaultValue = "1") Long page,
            @RequestParam(value = "rows",defaultValue = "10") Long pageSize){
        return userService.findPage(page,pageSize);
    }
}
