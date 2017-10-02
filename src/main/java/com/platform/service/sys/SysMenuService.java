package com.platform.service.sys;

import com.platform.entity.sys.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysMenuService <br>
 * 描述：菜单管理接口<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysMenuService {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Long userId);


    /**
     * 查询菜单
     */
    SysMenu queryObject(Long menuId);

    /**
     * 查询菜单列表
     */
    List<SysMenu> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存菜单
     */
    void save(SysMenu menu);

    /**
     * 修改
     */
    void update(SysMenu menu);

    /**
     * 删除
     */
    void deleteBatch(Long[] menuIds);

    /**
     * 查询用户的权限列表
     */
    List<SysMenu> queryUserList(Long userId);
}
