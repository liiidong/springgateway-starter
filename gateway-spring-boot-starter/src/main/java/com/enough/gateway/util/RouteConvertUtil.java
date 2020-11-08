package com.enough.gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.enough.gateway.commontypes.GatewayFilterDefinition;
import com.enough.gateway.commontypes.GatewayPredicateDefinition;
import com.enough.gateway.commontypes.GatewayRouteDefinition;
import com.enough.gateway.commontypes.TenantGatewayRouteEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/18
 */
public class RouteConvertUtil {
    /**
     * 将前端入参转换为自定义的租户路由实体模型
     *
     * @param gatewayRouteInfoDefinition
     * @return
     */
    public static TenantGatewayRouteEntity assembleTenantGatewayRouteEntity(GatewayRouteDefinition gatewayRouteInfoDefinition) {
        if (null == gatewayRouteInfoDefinition) {
            return null;
        }
        //这里没解析id和时间属性，在resource层里做
        TenantGatewayRouteEntity tenantGatewayRouteEntity = new TenantGatewayRouteEntity();
        tenantGatewayRouteEntity.setId(gatewayRouteInfoDefinition.getId());
        tenantGatewayRouteEntity.setCreateTime(gatewayRouteInfoDefinition.getCreateTime());
        tenantGatewayRouteEntity.setUpdateTime(gatewayRouteInfoDefinition.getUpdateTime());
        tenantGatewayRouteEntity.setRouteId(gatewayRouteInfoDefinition.getRouteId());
        tenantGatewayRouteEntity.setRouteUri(gatewayRouteInfoDefinition.getUri());
        tenantGatewayRouteEntity.setRouteOrder(gatewayRouteInfoDefinition.getOrder());
        tenantGatewayRouteEntity.setEnable(gatewayRouteInfoDefinition.getEnable());
        tenantGatewayRouteEntity.setPredicates(JSON.toJSONString(gatewayRouteInfoDefinition.getPredicates()));
        tenantGatewayRouteEntity.setFilters(JSON.toJSONString(gatewayRouteInfoDefinition.getFilters()));
        tenantGatewayRouteEntity.setType(gatewayRouteInfoDefinition.getType());
        tenantGatewayRouteEntity.setTenantId(gatewayRouteInfoDefinition.getTenantId());
        return tenantGatewayRouteEntity;
    }

    public static void main(String[] args) {
        List <GatewayPredicateDefinition> predicates = new ArrayList <>();
        predicates.add(new GatewayPredicateDefinition("Path", "/test/**"));
        predicates.add(new GatewayPredicateDefinition("Path2", "/test/**"));
        System.out.println(JSON.toJSONString(predicates));
    }

    private static List <String> parsePredicateStr(List <GatewayPredicateDefinition> predicates) {
        List <String> predicateStr = new ArrayList <>();
        for (GatewayPredicateDefinition predicate : predicates) {
            predicateStr.add(predicate.getName() + "=" + predicate.getArgs());
        }
        return predicateStr;
    }

    private static List <String> parseFilterStr(List <GatewayFilterDefinition> filters) {
        List <String> filterStr = new ArrayList <>();
        for (GatewayFilterDefinition filter : filters) {
            filterStr.add(filter.getName() + "=" + filter.getArgs());
        }
        return filterStr;
    }

    /**
     * 将数据库中信息转换为自定义的网关路由信息，便于展示
     *
     * @param tenantGatewayRouteEntity
     * @return
     */
    public static GatewayRouteDefinition assembleGatewayRouteDefinition(TenantGatewayRouteEntity tenantGatewayRouteEntity) {
        if (null == tenantGatewayRouteEntity) {
            return null;
        }
        GatewayRouteDefinition gatewayRouteDefinition = new GatewayRouteDefinition();
        gatewayRouteDefinition.setId(tenantGatewayRouteEntity.getId());
        gatewayRouteDefinition.setEnable(tenantGatewayRouteEntity.getEnable());
        gatewayRouteDefinition.setType(tenantGatewayRouteEntity.getType());
        gatewayRouteDefinition.setTenantId(tenantGatewayRouteEntity.getTenantId());
        gatewayRouteDefinition.setRouteId(tenantGatewayRouteEntity.getRouteId());
        gatewayRouteDefinition.setCreateTime(tenantGatewayRouteEntity.getCreateTime());
        gatewayRouteDefinition.setUpdateTime(tenantGatewayRouteEntity.getUpdateTime());
        gatewayRouteDefinition.setPredicates(JSONArray.parseArray(tenantGatewayRouteEntity.getPredicates(), GatewayPredicateDefinition.class));
        gatewayRouteDefinition.setFilters(JSONArray.parseArray(tenantGatewayRouteEntity.getFilters(), GatewayFilterDefinition.class));
        gatewayRouteDefinition.setUri(tenantGatewayRouteEntity.getRouteUri());
        gatewayRouteDefinition.setOrder(tenantGatewayRouteEntity.getRouteOrder());
        return gatewayRouteDefinition;
    }

    /**
     * 获取过滤器集合
     *
     * @return
     */
    public static List <FilterDefinition> getFilterDefinition(TenantGatewayRouteEntity tenantGatewayRouteEntity) {
        List <FilterDefinition> filterDefinitions = new ArrayList <>();
        if (!StringUtils.isEmpty(tenantGatewayRouteEntity.getPredicates())) {
            List <GatewayFilterDefinition> gatewayFilters = JSON.parseArray(tenantGatewayRouteEntity.getFilters(), GatewayFilterDefinition.class);
            for (GatewayFilterDefinition gatewayFilter : gatewayFilters) {
                filterDefinitions.add(new FilterDefinition(gatewayFilter.getName() + "=" + gatewayFilter.getArgs()));
            }
        }
        return filterDefinitions;
    }

    /**
     * 获取断言集合
     *
     * @return
     */
    public static List <PredicateDefinition> getPredicateDefinition(TenantGatewayRouteEntity tenantGatewayRouteEntity) {
        List <PredicateDefinition> predicateDefinitions = new ArrayList <>();
        if (!StringUtils.isEmpty(tenantGatewayRouteEntity.getFilters())) {
            List <GatewayPredicateDefinition> gatewayPredicates = JSON.parseArray(tenantGatewayRouteEntity.getPredicates(), GatewayPredicateDefinition.class);
            for (GatewayPredicateDefinition gatewayPredicate : gatewayPredicates) {
                predicateDefinitions.add(new PredicateDefinition(gatewayPredicate.getName() + "=" + gatewayPredicate.getArgs()));
            }
        }
        return predicateDefinitions;
    }

    /**
     * 将数据库中信息 解析为Springcloud Gateway路由对象
     * <p>
     *
     * @param tenantGatewayRouteEntity
     * @return
     */
    public static RouteDefinition assembleRouteDefinition(TenantGatewayRouteEntity tenantGatewayRouteEntity) {
        if (tenantGatewayRouteEntity == null) {
            return null;
        }
        RouteDefinition definition = new RouteDefinition();
        definition.setId(tenantGatewayRouteEntity.getRouteId());
        definition.setOrder(tenantGatewayRouteEntity.getRouteOrder());

        //设置断言
        List <PredicateDefinition> pdList = getPredicateDefinition(tenantGatewayRouteEntity);
        definition.setPredicates(pdList);

        //设置过滤器
        List <FilterDefinition> filters = getFilterDefinition(tenantGatewayRouteEntity);
        definition.setFilters(filters);
        URI uri = null;
        if (tenantGatewayRouteEntity.getRouteUri().startsWith("http")) {
            uri = UriComponentsBuilder.fromHttpUrl(tenantGatewayRouteEntity.getRouteUri()).build().toUri();
        } else {
            uri = URI.create(tenantGatewayRouteEntity.getRouteUri());
        }
        definition.setUri(uri);
        return definition;
    }
}
