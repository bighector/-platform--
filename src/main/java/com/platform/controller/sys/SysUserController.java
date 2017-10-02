package com.platform.controller.sys;

import com.platform.common.Global;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.PageUtils;
import com.platform.common.utils.Query;
import com.platform.common.utils.Result;
import com.platform.common.utils.ShiroUtils;
import com.platform.controller.AbstractController;
import com.platform.entity.sys.SysUser;
import com.platform.service.sys.SysUserRoleService;
import com.platform.service.sys.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysUserController <br>
 * 描述：系统用户<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != Global.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysUser> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public Result info() {
        return Result.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/passWord")
    public Result passWord(String passWord, String newPassword) {

        //sha256加密
        passWord = new Sha256Hash(passWord).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword).toHex();

        //更新密码
        int count = sysUserService.updatePassword(getUserId(), passWord, newPassword);
        if (count == 0) {
            return Result.error("原密码不正确");
        }

        //退出
        ShiroUtils.logout();

        return Result.ok();
    }

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.queryObject(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return Result.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody SysUser user) {

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return Result.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody SysUser user) {

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return Result.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return Result.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return Result.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return Result.ok();
    }
}
