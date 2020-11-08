package com.enough.tenant.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/15
 */
public class TenantServiceGateWayFilterFactory extends AbstractGatewayFilterFactory <TenantServiceGateWayFilterFactory.Config> {

    private static final String KEY = "tenantService";

    @Value("${tenantId}")
    private String tenantId;

    @Override
    public List <String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public TenantServiceGateWayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //            ShiroUser shiroUser = SecurityUtilsExt.getUser();
            //            if (shiroUser != null) {
            //                String tenantId = shiroUser.getTenantId();
            //                // 查询租户是否存在
            ////                TenantInfo currentTenant = tenantManagerService().queryTenant(tenantId);
            ////                if (currentTenant != null) {
            ////                    //根据租户查询其下的服务信息
            ////                }
            //            }
            return chain.filter(exchange);
        };
    }

    static class Config {

    }
}
