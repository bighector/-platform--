package com.platform.dao.sys;

import com.platform.dao.BaseDao;
import com.platform.entity.sys.SysRoleMenu;

import java.util.List;

/**
 * 名称：SysRoleMenuDao <br>
 * 描述：角色与菜单对应关系<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
