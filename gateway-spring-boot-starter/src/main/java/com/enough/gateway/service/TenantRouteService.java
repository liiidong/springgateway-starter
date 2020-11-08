package com.enough.gateway.service;

import com.enough.gateway.base.ReturnResult;
import com.enough.gateway.commontypes.GatewayRouteDefinition;
import com.enough.gateway.commontypes.RouteSearchParam;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/16
 */
public interface TenantRouteService {
    /**
     * 新增路由
     *
     * @param route
     * @return
     */
    ReturnResult <String> addRoute(GatewayRouteDefinition route);

    /**
     * 查询路由
     *
     * @param param
     * @return
     */
    ReturnResult <List <GatewayRouteDefinition>> queryRoutes(RouteSearchParam param);

    /**
     * 删除路由信息
     *
     * @param id
     * @return
     */
    ReturnResult deleteRoute(String id) throws Exception;

    /**
     * 更新路由信息
     *
     * @param route
     * @return
     */
    ReturnResult updateRoute(GatewayRouteDefinition route);

    /**
     * 根据id查询路由信息
     *
     * @param id
     * @return
     */
    ReturnResult <GatewayRouteDefinition> queryRoute(String id);

    /**
     * 批量删除路由信息
     *
     * @param ids
     * @return
     */
    ReturnResult <String> batchDeleteRoute(List <String> ids) throws Exception;

    /**
     * 刷新路由
     *
     * @return
     */
    ReturnResult <String> refreshRoutes();

}
