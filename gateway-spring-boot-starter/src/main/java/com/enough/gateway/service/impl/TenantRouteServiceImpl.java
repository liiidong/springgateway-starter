package com.enough.gateway.service.impl;

import com.enough.gateway.base.ReturnResult;
import com.enough.gateway.base.UUIDCreater;
import com.enough.gateway.commontypes.GatewayRouteDefinition;
import com.enough.gateway.commontypes.RouteSearchParam;
import com.enough.gateway.commontypes.TenantGatewayRouteEntity;
import com.enough.gateway.event.RoutesChangeEvent;
import com.enough.gateway.service.TenantRouteService;
import com.enough.gateway.util.RouteConvertUtil;
import com.enough.gateway.dao.TenantRouteMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/16
 */
@Service
public class TenantRouteServiceImpl implements TenantRouteService {

    @Autowired
    private TenantRouteMapper tenantRouteMapper;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 新增路由
     *
     * @param route
     * @return
     */
    @Override
    public ReturnResult <String> addRoute(GatewayRouteDefinition route) {
        ReturnResult result = new ReturnResult();
        route.setId(UUIDCreater.getInstance().getID());
        if (StringUtils.isEmpty(route.getRouteId())) {
            route.setRouteId(UUIDCreater.getInstance().getID());
        }
        route.setId(UUIDCreater.getInstance().getID());
        route.setCreateTime(new Date());
        route.setUpdateTime(new Date());
        route.setTenantId(route.getTenantId());
        TenantGatewayRouteEntity routeEntity = RouteConvertUtil.assembleTenantGatewayRouteEntity(route);
        boolean addRst = tenantRouteMapper.addRoute(routeEntity);
        if (addRst) {
            applicationContext.publishEvent(new RoutesChangeEvent(this, routeEntity, RoutesChangeEvent.OperateType.ADD));
        }
        result.setSuccessed(addRst);
        result.setMsg(addRst ? "添加成功！" : "添加失败");
        return result;
    }

    /**
     * 查询路由
     *
     * @param param
     * @return
     */
    @Override
    public ReturnResult <List <GatewayRouteDefinition>> queryRoutes(RouteSearchParam param) {
        ReturnResult <List <GatewayRouteDefinition>> result = new ReturnResult <>();
        List <GatewayRouteDefinition> gatewayRouteDefinitions = new ArrayList <>();
        List <TenantGatewayRouteEntity> list = tenantRouteMapper.queryRoutes(param);
        for (TenantGatewayRouteEntity tenantGatewayRouteEntity : list) {
            gatewayRouteDefinitions.add(RouteConvertUtil.assembleGatewayRouteDefinition(tenantGatewayRouteEntity));
        }
        result.setData(gatewayRouteDefinitions);
        result.setSuccessed(!CollectionUtils.isEmpty(list));
        result.setMsg(!CollectionUtils.isEmpty(list) ? "查询成功！" : "查无所获");
        return result;
    }

    /**
     * 删除路由信息
     *
     * @param id
     * @return
     */
    @Override
    public ReturnResult deleteRoute(String id) throws Exception {
        ReturnResult result = new ReturnResult();
        GatewayRouteDefinition gatewayRouteDefinition = queryRoute(id).getData();
        if (gatewayRouteDefinition == null) {
            throw new Exception("未能正确获取到路由信息");
        }
        boolean rst = tenantRouteMapper.deleteRoute(id);
        if (rst) {
            TenantGatewayRouteEntity tenantGatewayRouteEntity = RouteConvertUtil.assembleTenantGatewayRouteEntity(gatewayRouteDefinition);
            applicationContext.publishEvent(new RoutesChangeEvent(this, tenantGatewayRouteEntity, RoutesChangeEvent.OperateType.DELETE));
        }
        result.setSuccessed(rst);
        result.setMsg(rst ? "删除成功！" : "删除失败");
        return result;
    }

    /**
     * 更新路由信息
     *
     * @param route
     * @return
     */
    @Override
    public ReturnResult updateRoute(GatewayRouteDefinition route) {
        ReturnResult result = new ReturnResult();
        TenantGatewayRouteEntity routeEntity = RouteConvertUtil.assembleTenantGatewayRouteEntity(route);
        routeEntity.setUpdateTime(new Date());
        boolean rst = tenantRouteMapper.updateRoute(routeEntity);
        if (rst) {
            applicationContext.publishEvent(new RoutesChangeEvent(this, routeEntity, RoutesChangeEvent.OperateType.UPDATE));
        }
        result.setSuccessed(rst);
        result.setMsg(rst ? "更新成功！" : "更新失败");
        return result;
    }

    /**
     * 根据id查询路由信息
     *
     * @param id
     * @return
     */
    @Override
    public ReturnResult <GatewayRouteDefinition> queryRoute(String id) {
        ReturnResult <GatewayRouteDefinition> result = new ReturnResult();
        TenantGatewayRouteEntity route = tenantRouteMapper.queryRoute(id);
        if (route != null) {
            result.setData(RouteConvertUtil.assembleGatewayRouteDefinition(route));
        }
        result.setSuccessed(route != null);
        result.setMsg(route == null ? "未能正确获取到路由信息" : "成功获取到路由信息");
        return result;
    }

    /**
     * 批量删除路由信息
     *
     * @param ids
     * @return
     */
    @Override
    public ReturnResult <String> batchDeleteRoute(List <String> ids) throws Exception {
        ReturnResult <String> result = new ReturnResult <>();
        RouteSearchParam param = new RouteSearchParam();
        param.setIds(ids);
        ReturnResult <List <GatewayRouteDefinition>> queryRst = queryRoutes(param);
        if (!queryRst.isSuccessed() || CollectionUtils.isEmpty(queryRst.getData())) {
            throw new Exception("未能获取到路由信息");
        }
        List <GatewayRouteDefinition> tenantGatewayRouteEntities = queryRst.getData();
        boolean deleteRst = tenantRouteMapper.batchDeleteRoute(ids);
        if (deleteRst) {
            for (GatewayRouteDefinition routeDefinition : tenantGatewayRouteEntities) {
                applicationContext.publishEvent(
                        new RoutesChangeEvent(this, RouteConvertUtil.assembleTenantGatewayRouteEntity(routeDefinition), RoutesChangeEvent.OperateType.DELETE));
            }
        }
        result.setSuccessed(deleteRst);
        result.setMsg(deleteRst ? "删除成功" : "删除失败");
        return result;
    }

    @Override
    public ReturnResult <String> refreshRoutes() {
        applicationContext.publishEvent(new RoutesChangeEvent(this, null, RoutesChangeEvent.OperateType.REFRESH));
        return ReturnResult.<String>success().build();
    }
}
