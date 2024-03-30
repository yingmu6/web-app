package com.orange.controller;

import com.orange.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/query")
    public String queryUser(){

        String name = "zhang";
        Integer age = 30;
        userService.saveUser(name, age);
        return "success";
    }

}
