package com.platform.dao.sys;

import com.platform.dao.BaseDao;
import com.platform.entity.sys.SysMenu;

import java.util.List;

/**
 * 名称：SysMenuDao <br>
 * 描述：菜单管理<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysMenuDao extends BaseDao<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenu> queryUserList(Long userId);
}
