package com.enough.gateway.dao;

import com.enough.gateway.commontypes.RouteSearchParam;
import com.enough.gateway.commontypes.TenantGatewayRouteEntity;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description: 租户路由dao
 * @author: lidong
 * @create: 2019/07/16
 */
public interface TenantRouteMapper {
    /**
     * 新增路由
     *
     * @param route
     * @return
     */
    boolean addRoute(TenantGatewayRouteEntity route);

    /**
     * 查询路由
     *
     * @param param
     * @return
     */
    List <TenantGatewayRouteEntity> queryRoutes(RouteSearchParam param);

    /**
     * 删除路由信息
     *
     * @param id
     * @return
     */
    boolean deleteRoute(String id);

    /**
     * 更新路由信息
     *
     * @param route
     * @return
     */
    boolean updateRoute(TenantGatewayRouteEntity route);

    /**
     * 根据id查询路由信息
     *
     * @param id
     * @return
     */
    TenantGatewayRouteEntity queryRoute(String id);

    /**
     * 批量删除路由信息
     *
     * @param ids
     * @return
     */
    boolean batchDeleteRoute(List <String> ids);
}
