package com.enough.gateway.dynamicroute;

import com.alibaba.fastjson.JSON;
import com.enough.gateway.commontypes.GatewayConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/17
 */
@ConditionalOnProperty(name = "gateway.route.redis.enable", havingValue = "true")
@Component
@Slf4j
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    @Autowired(required = false)
    private RedisTemplate <String, Object> gafRedisTemplate;
    @Value("${tenantId:empty}")
    private String tenantId;

    @Override
    public Flux <RouteDefinition> getRouteDefinitions() {
        List <RouteDefinition> routeDefinitions = new ArrayList <>();
        //先从ConcurrentHashMap本地缓存取一份；没有再到redis缓存中拿。
        String value = RouteMapCache.getCache(GatewayConst.GATEWAY_ROUTES + ":" + tenantId);
        if (value == null) {
            List <RouteDefinition> finalRouteDefinitions = routeDefinitions;
            gafRedisTemplate.opsForHash().values(GatewayConst.GATEWAY_ROUTES + ":" + tenantId).stream().forEach(routeDefinition -> {
                boolean addRst = finalRouteDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class));
            });
            RouteMapCache.initCache(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, JSON.toJSONString(routeDefinitions));
        } else {
            routeDefinitions = JSON.parseArray(value, RouteDefinition.class);
        }
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono <Void> save(Mono <RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            gafRedisTemplate.opsForHash().put(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, routeDefinition.getId(), JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono <Void> delete(Mono <String> routeId) {
        return routeId.flatMap(id -> {
            if (gafRedisTemplate.opsForHash().hasKey(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, id)) {
                gafRedisTemplate.opsForHash().delete(GatewayConst.GATEWAY_ROUTES + ":" + tenantId, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("route not found: " + routeId)));
        });
    }
}
