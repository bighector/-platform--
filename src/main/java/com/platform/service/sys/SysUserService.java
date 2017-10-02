package com.platform.service.sys;

import com.platform.entity.sys.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysUserService <br>
 * 描述：系统用户接口<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserService {

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
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SysUser queryObject(Long userId);

    /**
     * 查询用户列表
     */
    List<SysUser> queryList(Map<String, Object> map);

    /**
     * 查询总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户
     */
    void save(SysUser user);

    /**
     * 修改用户
     */
    void update(SysUser user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param passWord    原密码
     * @param newPassword 新密码
     */
    int updatePassword(Long userId, String passWord, String newPassword);
}
