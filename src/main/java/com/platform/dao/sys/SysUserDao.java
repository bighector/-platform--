package com.platform.dao.sys;

import com.platform.dao.BaseDao;
import com.platform.entity.sys.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysUserDao <br>
 * 描述：系统用户<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserDao extends BaseDao<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String userName);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);
}
