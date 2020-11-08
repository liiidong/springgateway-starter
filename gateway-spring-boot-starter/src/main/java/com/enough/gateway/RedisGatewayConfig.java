package com.enough.gateway;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/22
 */
public class RedisGatewayConfig {
    private boolean enable;
    private String hostName = "127.0.0.1";
    private int port = 6379;
    private String password;
    private int maxIdle = 8;
    private int minIdle = 0;
    private int maxActive = 8;
    private int databaseIndex = 0;
    private long maxWaitMillis = -1;
    private int timeout = 3000;
    private boolean ssl = false;
    /**
     * 限流策略配置
     */
    private RedisLimitConfig limit;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getDatabaseIndex() {
        return databaseIndex;
    }

    public void setDatabaseIndex(int databaseIndex) {
        this.databaseIndex = databaseIndex;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public RedisLimitConfig getLimit() {
        return limit;
    }

    public void setLimit(RedisLimitConfig limit) {
        this.limit = limit;
    }
}
