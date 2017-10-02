package com.platform.service.sys;

import com.platform.entity.sys.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysRoleService <br>
 * 描述：角色接口<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleService {

    SysRole queryObject(Long roleId);

    List<SysRole> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRole role);

    void update(SysRole role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
