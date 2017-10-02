package com.platform.dao.sys;

import com.platform.dao.BaseDao;
import com.platform.entity.sys.SysRole;

import java.util.List;

/**
 * 名称：SysRoleDao <br>
 * 描述：角色管理<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
