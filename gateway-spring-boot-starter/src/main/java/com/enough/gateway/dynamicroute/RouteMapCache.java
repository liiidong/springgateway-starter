package com.enough.gateway.dynamicroute;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: gaf-service-manager-modules
 * @description: ConcurrentHashMap缓存类
 * @author: lidong
 * @create: 2019/07/17
 */
public class RouteMapCache {
    //ConcurrentHashMap对象
    private static ConcurrentHashMap <String, String> cacheMap = new ConcurrentHashMap();

    /**
     * 获取缓存的对象
     *
     * @param account
     * @return
     */
    public static String getCache(String account) {
        if (cacheMap.containsKey(account)) {
            return cacheMap.get(account);
        }

        return cacheMap.get(account);
    }

    /**
     * 初始化缓存
     *
     * @param account
     */
    public static void initCache(String account, String value) {
        cacheMap.put(account, value);
    }

    /**
     * 移除缓存信息
     *
     * @param account
     */
    public static void removeCache(String account) {
        cacheMap.remove(account);
    }
}
