package com.enough.gateway.event;

import com.enough.gateway.commontypes.TenantGatewayRouteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/19
 */
@Slf4j
public class RoutesChangeEvent extends ApplicationEvent {
    private TenantGatewayRouteEntity route;
    private OperateType operateType;

    public TenantGatewayRouteEntity getRoute() {
        return route;
    }

    public void setRoute(TenantGatewayRouteEntity route) {
        this.route = route;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public RoutesChangeEvent(Object source, TenantGatewayRouteEntity route, OperateType type) {
        super(source);
        log.info("路由信息更改事件");
        this.setRoute(route);
        this.setOperateType(type);
    }

    public static enum OperateType {
        ADD, UPDATE, DELETE, REFRESH
    }
}
