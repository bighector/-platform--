package com.platform.entity.sys;

import java.io.Serializable;

/**
 * 名称：SysUserRole <br>
 * 描述：用户与角色对应关系<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 设置：
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     *
     * @return Long
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     *
     * @return Long
     */
    public Long getRoleId() {
        return roleId;
    }

}
