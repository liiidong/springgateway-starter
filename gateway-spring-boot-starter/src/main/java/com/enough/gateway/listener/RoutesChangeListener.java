package com.enough.gateway.listener;

import com.enough.gateway.base.ReturnResult;
import com.enough.gateway.commontypes.TenantGatewayRouteEntity;
import com.enough.gateway.dynamicroute.DynamicRouteServiceImpl;
import com.enough.gateway.event.RoutesChangeEvent;
import com.enough.gateway.util.RouteConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: gaf-service-manager-modules
 * @description: 路由信息改变监听
 * @author: lidong
 * @create: 2019/07/19
 */
@Component
@Slf4j
public class RoutesChangeListener implements ApplicationListener <RoutesChangeEvent> {
    @Autowired(required = false)
    private DynamicRouteServiceImpl dynamicRouteService;

    @Async //异步监听，
    @Override
    public void onApplicationEvent(RoutesChangeEvent routesChangeEvent) {
        TenantGatewayRouteEntity tenantGatewayRouteEntity = routesChangeEvent.getRoute();
        RoutesChangeEvent.OperateType operateType = routesChangeEvent.getOperateType();
        RouteDefinition routeDefinition = RouteConvertUtil.assembleRouteDefinition(tenantGatewayRouteEntity);
        ReturnResult result = new ReturnResult();
        try {
            switch (operateType) {
            case ADD:
                result = dynamicRouteService.add(routeDefinition);
                break;
            case DELETE:
                result = dynamicRouteService.delete(routeDefinition.getId());
                break;
            case UPDATE:
                result = dynamicRouteService.update(routeDefinition);
                break;
            default:
                log.info("开始刷新路由信息!");
                dynamicRouteService.refreshRoutes();
            }
            log.info(result.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
