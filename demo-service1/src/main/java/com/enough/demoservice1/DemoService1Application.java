package com.enough.demoservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoService1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoService1Application.class, args);
    }

}
