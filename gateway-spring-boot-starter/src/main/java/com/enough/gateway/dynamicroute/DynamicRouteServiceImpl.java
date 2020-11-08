package com.enough.gateway.dynamicroute;

import com.enough.gateway.base.ReturnResult;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: gaf-service-manager-modules
 * @description: 动态路由服务
 * @author: lidong
 * @create: 2019/07/15
 */
public abstract class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    public ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;

    }

    public abstract List <RouteDefinition> getRouteDefinitions();

    /**
     * 增加路由
     *
     * @param definition
     * @return
     */
    public abstract ReturnResult <String> add(RouteDefinition definition);

    /**
     * 更新路由:先删除在重新添加
     *
     * @param definition
     * @return
     */
    public abstract ReturnResult <String> update(RouteDefinition definition);

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public abstract ReturnResult <Mono <ResponseEntity <Object>>> delete(String id);

    /**
     * 刷新路由
     */
    public abstract void refreshRoutes();

    // 修改路由后将绑定到刷新事件
    public void notifyChanged() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
