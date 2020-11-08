package com.enough.gateway.commontypes;

import java.util.Date;

/**
 * @program: gaf-service-manager-modules
 * @description: 租户路由实体模型，与数据库交互时使用
 * @author: lidong
 * @create: 2019/07/16
 */
public class TenantGatewayRouteEntity {
    private String id;

    private String routeId;

    private String routeUri;

    private Integer routeOrder = 1;
    /**
     * 是否可用：true:1  false:0
     */
    private Boolean enable = true;

    private Date createTime;

    private Date updateTime;
    /**
     * 断言信息：格式为JSONStr
     */
    private String predicates;
    /**
     * 过滤器信息：格式为JSONStr
     */
    private String filters;

    /**
     * 类型：系统路由、租户路由
     */
    private GatewayRouteType type;

    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getRouteUri() {
        return routeUri;
    }

    public void setRouteUri(String routeUri) {
        this.routeUri = routeUri == null ? null : routeUri.trim();
    }

    public Integer getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(Integer routeOrder) {
        this.routeOrder = routeOrder;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    public String getPredicates() {
        return predicates;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates == null ? null : predicates.trim();
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters == null ? null : filters.trim();
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


}
