package com.enough.tenant.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.zxt.*")
@EnableDiscoveryClient
@EnableAsync
//需要扫描到gateway-starter的mapper
@MapperScan(basePackages = "com.zxt.**.dao")
public class ServiceTenantGatewayApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ServiceTenantGatewayApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
