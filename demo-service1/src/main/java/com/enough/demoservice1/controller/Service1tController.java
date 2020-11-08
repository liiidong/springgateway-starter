package com.enough.demoservice1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springgateway-rootmodule
 * @description:
 * @author: liiidong
 * @create: 2020/04/19
 */
@RestController
@RequestMapping("/service1")
public class Service1tController {

    @GetMapping("/test")
    public String getTest1() {
        return "test1";
    }

    @GetMapping("/test/hello")
    public String getHello() {
        return "test1-hello";
    }
}
