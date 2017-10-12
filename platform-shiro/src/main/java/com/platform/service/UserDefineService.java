package com.platform.service;

import java.util.List;
import java.util.Map;

/**
 * 自定义
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月11日 下午9:20:48
 */
public interface UserDefineService {

    /**
     * 主要功能:根据sql查询数据，返回指定类型集合 注意事项:无
     *
     * @param sql    sql
     * @param params 参数
     * @param clazz  对象类
     * @param <T>    泛型指数据对象
     * @return 数据集合
     */
    <T> List<T> findObjForJdbc(String sql, Map<String, Object> params, Class<T> clazz);

    /**
     * 主要功能:查询sql列表 注意事项:无
     *
     * @param sql  sql
     * @param objs 参数对象【数组型或多个参数形式】
     * @return 数据集合
     */
    List<Map<String, Object>> findForJdbc(String sql, Object... objs);

    /**
     * 追加机构数据权限
     *
     * @param orgAlias sql中数据orgId的别名
     * @param sql      查询sql
     * @param map      sql参数
     * @return 查询sql
     */
    String appOrgDataPermission(String orgAlias, String sql, Map<String, Object> map);

    /**
     * 追加机构数据权限(并如果没有任何机构权限时，登录人能看见自己创建的数据)
     *
     * @param orgAlias       sql中数据orgId的别名
     * @param userAlias      sql中数据创建用户（通常传入createUserId）的别名
     * @param otherOrgAlias  sql中数据orgId的别名  辅办机构   暂时不使用  yaojie  20170919
     * @param otherUserAlias sql中数据创建用户（通常传入createUserId）的别名  辅办客户经理
     * @param sql            查询sql
     * @param map            sql参数
     * @return 查询sql
     */
    String appOrgAndCreateUserDataPermission(String orgAlias, String userAlias, String otherOrgAlias, String otherUserAlias, String sql,
                                             Map<String, Object> map);

    /**
     * 追加机构数据权限(并如果没有任何机构权限时，登录人能看见自己创建的数据)
     *
     * @param orgAlias  sql中数据orgId的别名
     * @param userAlias sql中数据创建用户（通常传入createUserId）的别名
     * @param sql       查询sql
     * @param map       sql参数
     * @return 查询sql
     */
    String appOrgAndCreateUserDataPermission(String orgAlias, String userAlias, String sql, Map<String, Object> map);

    /**
     * 执行单条sql
     *
     * @param sql
     */
    void executeSql(String sql);
}
