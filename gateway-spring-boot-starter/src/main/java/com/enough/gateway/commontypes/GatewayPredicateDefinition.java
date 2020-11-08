package com.enough.gateway.commontypes;

/**
 * @program: gaf-service-manager-modules
 * @description: 断言实体
 * @author: lidong
 * @create: 2019/07/16
 */
public class GatewayPredicateDefinition {
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

    public GatewayPredicateDefinition() {
    }

    public GatewayPredicateDefinition(String name, String args) {
        this.name = name;
        this.args = args;
    }
}
