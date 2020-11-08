package com.enough.gateway.commontypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 网关路由信息，提供rest层使用
 * @author: lidong
 * @create: 2019/07/16
 */
public class GatewayRouteDefinition {
    /****************基础属性******************/
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 是否可用：true:1  false:0
     */
    private Boolean enable = true;
    /**
     * 类型：系统路由、租户路由
     */
    private GatewayRouteType type;
    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /****************路由属性******************/
    /**
     * 路由的Id
     */
    private String routeId;
    /**
     * 路由断言集合配置
     */
    private List <GatewayPredicateDefinition> predicates = new ArrayList <>();
    /**
     * 路由过滤器集合配置
     */
    private List <GatewayFilterDefinition> filters = new ArrayList <>();
    /**
     * 路由规则转发的目标uri
     */
    private String uri;
    /**
     * 路由执行的顺序
     */
    private int order = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public GatewayRouteType getType() {
        return type;
    }

    public void setType(GatewayRouteType type) {
        this.type = type;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List <GatewayPredicateDefinition> getPredicates() {
        return predicates;
    }

    public void setPredicates(List <GatewayPredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List <GatewayFilterDefinition> getFilters() {
        return filters;
    }

    public void setFilters(List <GatewayFilterDefinition> filters) {
        this.filters = filters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
