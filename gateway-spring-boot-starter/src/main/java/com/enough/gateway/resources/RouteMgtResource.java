package com.enough.gateway.resources;

import com.alibaba.fastjson.JSON;
import com.enough.gateway.base.ReturnResult;
import com.enough.gateway.commontypes.GatewayRouteDefinition;
import com.enough.gateway.commontypes.RouteSearchParam;
import com.enough.gateway.service.TenantRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/18
 */
@RestController
@RequestMapping("/routes")
@Slf4j
public class RouteMgtResource {

    @Autowired
    private TenantRouteService tenantRouteService;

    @Value("${tenantId:empty}")
    String tenantId;

    @GetMapping
    public ReturnResult <List <GatewayRouteDefinition>> queryTenantGatewayRoutes(
            @RequestParam(name = "routeSearchParam", required = false) String routeSearchParam) {
        ReturnResult <List <GatewayRouteDefinition>> result = new ReturnResult <>();
        try {
            RouteSearchParam param = buildRouteSearchParam(routeSearchParam);
            ReturnResult <List <GatewayRouteDefinition>> result1 = tenantRouteService.queryRoutes(param);
            result.setData(result1.getData());
            result.setSuccessed(result1.isSuccessed());
            result.setMsg(result1.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ReturnResult <GatewayRouteDefinition> queryTenantGatewayRoute(@PathVariable("id") String id) {
        ReturnResult <GatewayRouteDefinition> result = new ReturnResult <>();
        try {
            result = tenantRouteService.queryRoute(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ReturnResult <String> addTenantRoute(@RequestBody GatewayRouteDefinition route) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            if (CollectionUtils.isEmpty(route.getPredicates())) {
                throw new Exception("路由Predicates不能为空");
            }
            if (CollectionUtils.isEmpty(route.getFilters())) {
                throw new Exception("路由Filters不能为空");
            }
            if (StringUtils.isEmpty(route.getTenantId())) {
                route.setTenantId(tenantId);
            }
            result = tenantRouteService.addRoute(route);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @DeleteMapping(produces = "application/json")
    public ReturnResult <String> batchDeleteTenantRoute(@RequestBody String idsJsonStr) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            if (StringUtils.isEmpty(idsJsonStr)) {
                throw new IllegalArgumentException("请传入正确的id集合");
            }
            List <String> ids = JSON.parseArray(idsJsonStr, String.class);
            result = tenantRouteService.batchDeleteRoute(ids);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ReturnResult <String> deleteTenantRoute(@PathVariable("id") String id) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            result = tenantRouteService.deleteRoute(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PutMapping(produces = "application/json")
    public ReturnResult <String> updateTenantRoute(@RequestBody GatewayRouteDefinition route) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            result = tenantRouteService.updateRoute(route);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "refresh",produces = "application/json")
    public ReturnResult <String> refreshRoutes() {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            result = tenantRouteService.refreshRoutes();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    private RouteSearchParam buildRouteSearchParam(String routeSearchParam) throws Exception {
        RouteSearchParam searchParam = new RouteSearchParam();
        if (StringUtils.isNotEmpty(routeSearchParam)) {
            try {
                RouteSearchParam temp = (RouteSearchParam) JSON.parseObject(routeSearchParam, RouteSearchParam.class);
                searchParam = (temp != null) ? temp : searchParam;
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        return searchParam;
    }

}
