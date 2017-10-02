package com.platform.controller.sys;

import com.platform.common.Global;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.PageUtils;
import com.platform.common.utils.Query;
import com.platform.common.utils.Result;
import com.platform.controller.AbstractController;
import com.platform.entity.sys.SysRole;
import com.platform.service.sys.SysRoleMenuService;
import com.platform.service.sys.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称：SysRoleController <br>
 * 描述：角色管理<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Global.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysRole> list = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Result select() {
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (getUserId() != Global.SUPER_ADMIN) {
            map.put("createUserId", getUserId());
        }
        List<SysRole> list = sysRoleService.queryList(map);

        return Result.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public Result info(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return Result.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRole role) {

        role.setCreateUserId(getUserId());
        sysRoleService.save(role);

        return Result.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRole role) {

        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

        return Result.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

        return Result.ok();
    }
}
