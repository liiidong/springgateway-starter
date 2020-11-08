package com.enough.gateway.listener;

import com.enough.gateway.base.ReturnResult;
import com.enough.gateway.commontypes.GatewayConst;
import com.enough.gateway.commontypes.GatewayRouteDefinition;
import com.enough.gateway.commontypes.GatewayRouteType;
import com.enough.gateway.commontypes.RouteSearchParam;
import com.enough.gateway.dynamicroute.DynamicRouteServiceImpl;
import com.enough.gateway.dynamicroute.RedisDynamicRouteServiceImpl;
import com.enough.gateway.service.TenantRouteService;
import com.enough.gateway.util.RouteConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/15
 */
@Slf4j
public class DynamicRouteLoadListener implements ApplicationListener <ApplicationReadyEvent> {

    @Autowired(required = false)
    private DynamicRouteServiceImpl dynamicRouteService;

    @Autowired(required = false)
    private TenantRouteService tenantRouteService;

    @Value("${tenantId:empty}")
    private String tenantId;
    @Autowired(required = false)
    private RedisTemplate <String, Object> gafRedisTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //先清除redis缓存
        List <RouteDefinition> routeDefinitions = dynamicRouteService.getRouteDefinitions();
        if (!CollectionUtils.isEmpty(routeDefinitions) && dynamicRouteService instanceof RedisDynamicRouteServiceImpl) {
            log.info("获取到网关路由数目：" + routeDefinitions.size());
            for (RouteDefinition routeDefinition : routeDefinitions) {
                if (gafRedisTemplate.opsForHash().hasKey(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, routeDefinition.getId())) {
                    long l = gafRedisTemplate.opsForHash().delete(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, routeDefinition.getId());
                    log.info("移除已缓存路由结果：" + l);
                }
            }
            //logger.info(gafRedisTemplate.delete(GatewayConst.GATEWAY_ROUTES + ":" + tenantId) ? "移除路由KV成功" : "移除路由KV失败");
        }
        // 从数据库读取
        RouteSearchParam param = new RouteSearchParam();
        param.setType(GatewayRouteType.TENANT);
        param.setTenantId(tenantId);
        ReturnResult <List <GatewayRouteDefinition>> result = tenantRouteService.queryRoutes(param);
        if (result.isSuccessed() && !CollectionUtils.isEmpty(result.getData())) {
            List <GatewayRouteDefinition> gatewayRouteDefinitions = result.getData();
            for (GatewayRouteDefinition gatewayRouteDefinition : gatewayRouteDefinitions) {
                RouteDefinition routeDefinition = RouteConvertUtil
                        .assembleRouteDefinition(RouteConvertUtil.assembleTenantGatewayRouteEntity(gatewayRouteDefinition));
                dynamicRouteService.add(routeDefinition);
            }
        }
    }
}
