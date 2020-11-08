package com.enough.demoservice2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springgateway-rootmodule
 * @description:
 * @author: liiidong
 * @create: 2020/04/19
 */
@RestController
@RequestMapping("/service2")
public class Service2tController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String getTest2() {
        return "test2";
    }

    @GetMapping("/test/hello")
    public String getHello() {
        return "test2-hello";
    }

    @GetMapping("/service1/test")
    public String getService1Test() {
        return restTemplate.getForEntity("http://DEMO-SERVICE1/service1/test", String.class).getBody();
    }
    @GetMapping("/service1/test2")
    public String getService1Test2() {
        return restTemplate.getForEntity("http://TENANT-GAF-GATEWAY/enough/service1/test", String.class).getBody();
    }
}
