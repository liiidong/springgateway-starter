package com.enough.gateway.commontypes;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/17
 */
public class RouteSearchParam {
    private String tenantId;
    private GatewayRouteType type;
    private boolean enable = true;
    private List <String> ids;

    public List <String> getIds() {
        return ids;
    }

    public void setIds(List <String> ids) {
        this.ids = ids;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public GatewayRouteType getType() {
        return type;
    }

    public void setType(GatewayRouteType type) {
        this.type = type;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
