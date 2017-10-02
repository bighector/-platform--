package com.platform.service.sys;

import java.util.List;

/**
 * 名称：SysRoleMenuService <br>
 * 描述：角色与菜单对应关系接口<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleMenuService {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
