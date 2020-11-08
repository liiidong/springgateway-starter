package com.enough.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: gaf-service-manager-modules
 * @description: 网关配置信息
 * @author: lidong
 * @create: 2019/07/22
 */
@Configuration
@ConfigurationProperties(prefix = "gateway")
public class GatewayConfig {
    private boolean enable;
    private boolean lowerCaseServiceId = true;
    /**
     * Redis配置
     */
    private RedisGatewayConfig redis;

    public boolean isLowerCaseServiceId() {
        return lowerCaseServiceId;
    }

    public void setLowerCaseServiceId(boolean lowerCaseServiceId) {
        this.lowerCaseServiceId = lowerCaseServiceId;
    }

    public RedisGatewayConfig getRedis() {
        return redis;
    }

    public void setRedis(RedisGatewayConfig redis) {
        this.redis = redis;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
