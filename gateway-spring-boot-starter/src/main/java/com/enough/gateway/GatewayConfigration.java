package com.enough.gateway;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.enough.gateway.dynamicroute.DefaultDynamicRouteServiceImpl;
import com.enough.gateway.dynamicroute.RedisDynamicRouteServiceImpl;
import com.enough.gateway.dynamicroute.RedisRouteDefinitionRepository;
import com.enough.gateway.listener.DynamicRouteLoadListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: gaf-service-manager-modules
 * @description:
 * @author: lidong
 * @create: 2019/07/22
 */
@Configuration
@ConditionalOnProperty(name = "gateway.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({GatewayConfig.class})
@ComponentScan("com.zxt.gateway.*")
public class GatewayConfigration {
    @Autowired
    private GatewayConfig gatewayConfig;
    @Autowired
    private RouteDefinitionRepository routeDefinitionWriter;
    @Autowired(required = false)
    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    @Bean
    @ConditionalOnProperty(name = "gateway.route.redis.enable", havingValue = "true")
    public RedisTemplate <String, Object> gafRedisTemplate() {
        RedisTemplate <String, Object> redisTemplate = new RedisTemplate <>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(
                connectionFactory(gatewayConfig.getRedis().getHostName(), gatewayConfig.getRedis().getPort(), gatewayConfig.getRedis().getPassword(),
                        gatewayConfig.getRedis().getMaxIdle(), gatewayConfig.getRedis().getMinIdle(), gatewayConfig.getRedis().getMaxActive(),
                        gatewayConfig.getRedis().getDatabaseIndex(), gatewayConfig.getRedis().getMaxWaitMillis(), gatewayConfig.getRedis().getTimeout(),
                        gatewayConfig.getRedis().isSsl()));
        return redisTemplate;
    }

    @Bean
    @ConditionalOnProperty(name = "gateway.route.redis.enable", havingValue = "true")
    public RedisDynamicRouteServiceImpl redisDynamicRouteService() {
        return new RedisDynamicRouteServiceImpl(redisRouteDefinitionRepository);
    }

    @Bean
    @ConditionalOnExpression("${gateway.route.enable}")
    public DynamicRouteLoadListener dynamicRouteLoadListener() {
        return new DynamicRouteLoadListener();
    }

    /**
     * 开启网关配置同时不开启redis，采用默认
     *
     * @return
     */
    @Bean
    @ConditionalOnExpression("${gateway.route.enable} and !${gateway.route.redis.enable}")
    public DefaultDynamicRouteServiceImpl defaultDynamicRouteService() {
        return new DefaultDynamicRouteServiceImpl(routeDefinitionWriter);
    }

    public RedisConnectionFactory connectionFactory(String hostName, int port, String password, int maxIdle, int minIdle, int maxActive, int databaseIndex,
            long maxWaitMillis, int timeout, boolean ssl) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);
        jedis.setPassword(StringUtils.isNotEmpty(password) ? password : "");
        jedis.setDatabase(databaseIndex);
        jedis.setTimeout(timeout);
        jedis.setUseSsl(ssl);
        jedis.setPoolConfig(this.poolCofig(maxIdle, minIdle, maxActive, maxWaitMillis));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int minIdle, int maxActive, long maxWaitMillis) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMinIdle(minIdle);
        poolCofig.setMaxTotal(maxActive);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(false);
        return poolCofig;
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }

}
