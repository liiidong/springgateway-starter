package com.enough.gateway.dynamicroute;

import com.enough.gateway.base.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description: 动态路由服务
 * @author: lidong
 * @create: 2019/07/15
 */
@Slf4j
public class RedisDynamicRouteServiceImpl extends DynamicRouteServiceImpl {
    private RedisRouteDefinitionRepository routeDefinitionWriter;

    public RedisDynamicRouteServiceImpl(RedisRouteDefinitionRepository routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    @Override
    public List <RouteDefinition> getRouteDefinitions() {
        return routeDefinitionWriter.getRouteDefinitions().collectList().block();
    }


    /**
     * 增加路由
     *
     * @param definition
     * @return
     */
    @Override
    public ReturnResult <String> add(RouteDefinition definition) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.notifyChanged();
            result.setSuccessed(true);
            result.setMsg("路由添加成功");
        } catch (Exception e) {
            log.error("添加失败" + e.getMessage(), e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 更新路由:先删除在重新添加
     *
     * @param definition
     * @return
     */
    @Override
    public ReturnResult <String> update(RouteDefinition definition) {
        ReturnResult <String> result = new ReturnResult <>();
        try {
            ReturnResult deleteRst = delete(definition.getId());
            if (!deleteRst.isSuccessed()) {
                throw new Exception(deleteRst.getMsg());
            }
        } catch (Exception e) {
            result.setMsg("更新失败，找不到路由信息routeId: " + definition.getId());
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.notifyChanged();
            result.setSuccessed(true);
            result.setMsg("路由更新成功");
        } catch (Exception e) {
            result.setMsg("路由更新失败");
        }
        return result;
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    @Override
    public ReturnResult <Mono <ResponseEntity <Object>>> delete(String id) {
        ReturnResult <Mono <ResponseEntity <Object>>> result = new ReturnResult <>();
        try {
            Mono <ResponseEntity <Object>> mono = this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
                result.setMsg("删除成功");
                notifyChanged();
                return Mono.just(ResponseEntity.ok().build());
            })).onErrorResume((t) -> {
                result.setMsg("删除失败");
                return t instanceof NotFoundException;
            }, (t) -> {
                return Mono.just(ResponseEntity.notFound().build());
            });
            result.setData(mono);
        } catch (Exception e) {
            log.error("删除异常：" + e.getMessage(), e);
            result.setMsg("删除异常：" + e.getMessage());
        }
        return result;
    }

    @Override
    public void refreshRoutes() {
        this.notifyChanged();
    }
}
