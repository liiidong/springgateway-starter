package com.enough.gateway;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/22
 */
public class RedisLimitConfig {
    private boolean enbale;
    /**
     * 令牌流速
     */
    private String limiterRate;
    /**
     * 令牌桶容量
     */
    private String limiterCapacity;

    public boolean isEnbale() {
        return enbale;
    }

    public void setEnbale(boolean enbale) {
        this.enbale = enbale;
    }

    public String getLimiterRate() {
        return limiterRate;
    }

    public void setLimiterRate(String limiterRate) {
        this.limiterRate = limiterRate;
    }

    public String getLimiterCapacity() {
        return limiterCapacity;
    }

    public void setLimiterCapacity(String limiterCapacity) {
        this.limiterCapacity = limiterCapacity;
    }
}
