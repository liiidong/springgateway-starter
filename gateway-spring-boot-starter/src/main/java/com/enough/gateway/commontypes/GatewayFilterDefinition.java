package com.enough.gateway.commontypes;

/**
 * @program: gaf-service-manager-modules
 * @description: 过滤器实体
 * @author: lidong
 * @create: 2019/07/16
 */
public class GatewayFilterDefinition {
    private String name;
    private String args;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public GatewayFilterDefinition() {
    }

    public GatewayFilterDefinition(String name, String args) {
        this.name = name;
        this.args = args;
    }
}
