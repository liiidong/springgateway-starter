package com.enough.gateway.dao.impl;

/**
 * @program: springgateway-rootmodule
 * @description:
 * @author: liiidong
 * @create: 2020/04/18
 */
public class TenantRouteDaoImpl{}
//@Component
//public class TenantRouteDaoImpl implements TenantRouteDao {
//    @Autowired
//    private SqlSession sqlSession;
//
//    /**
//     * 新增路由
//     *
//     * @param route
//     * @return
//     */
//    @Override
//    public boolean addRoute(TenantGatewayRouteEntity route) {
//        return sqlSession.insert("addRoute", route) > 0;
//    }
//
//    /**
//     * 查询路由
//     *
//     * @param param
//     * @return
//     */
//    @Override
//    public List <TenantGatewayRouteEntity> queryRoutes(RouteSearchParam param) {
//        return sqlSession.selectList("queryRoutes", param);
//    }
//
//    /**
//     * 删除路由信息
//     *
//     * @param id
//     * @return
//     */
//    @Override
//    public boolean deleteRoute(String id) {
//        return sqlSession.delete("deleteRoute", id) > 0;
//    }
//
//    /**
//     * 更新路由信息
//     *
//     * @param route
//     * @return
//     */
//    @Override
//    public boolean updateRoute(TenantGatewayRouteEntity route) {
//        return sqlSession.update("updateRoute", route) > 0;
//    }
//
//    /**
//     * 根据id查询路由信息
//     *
//     * @param id
//     * @return
//     */
//    @Override
//    public TenantGatewayRouteEntity queryRoute(String id) {
//        return sqlSession.selectOne("queryRoute", id);
//    }
//
//    /**
//     * 批量删除路由信息
//     *
//     * @param ids
//     * @return
//     */
//    @Override
//    public boolean batchDeleteRoute(List <String> ids) {
//        return sqlSession.delete("batchDeleteRoute", ids) > 0;
//    }
//}
