package com.platform.dao.sys;

import com.platform.dao.BaseDao;
import com.platform.entity.sys.SysUserRole;

import java.util.List;

/**
 * 名称：SysUserRoleDao <br>
 * 描述：用户与角色对应关系<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);
}
