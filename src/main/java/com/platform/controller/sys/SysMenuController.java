package com.platform.controller.sys;

import com.platform.common.Global;
import com.platform.common.annotation.SysLog;
import com.platform.common.utils.BaseException;
import com.platform.common.utils.PageUtils;
import com.platform.common.utils.Query;
import com.platform.common.utils.Result;
import com.platform.controller.AbstractController;
import com.platform.entity.sys.SysMenu;
import com.platform.service.sys.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称：SysMenuController <br>
 * 描述：系统菜单<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    private static final String PATH = "/sys/";
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/icon")
    public String icon() {
        return PATH + "icon";
    }

    @RequestMapping("/view")
    public String view() {
        return PATH + "menu";
    }

    /**
     * 所有菜单列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysMenu> menuList = sysMenuService.queryList(query);
        int total = sysMenuService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(menuList, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 所有菜单列表
     */
    @ResponseBody
    @RequestMapping("/queryAll")
    public Result queryAll(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryList(params);

        return Result.ok().put("list", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select() {
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return Result.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @ResponseBody
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public Result perms() {
        //查询列表数据
        List<SysMenu> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() == Global.SUPER_ADMIN) {
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        } else {
            menuList = sysMenuService.queryUserList(getUserId());
        }

        return Result.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @ResponseBody
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result info(@PathVariable("menuId") Long menuId) {
        SysMenu menu = sysMenuService.queryObject(menuId);
        return Result.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @ResponseBody
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return Result.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenu menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return Result.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@RequestBody Long[] menuIds) {
        for (Long menuId : menuIds) {
            if (menuId.longValue() <= 30) {
                return Result.error("系统菜单，不能删除");
            }
        }
        sysMenuService.deleteBatch(menuIds);

        return Result.ok();
    }

    /**
     * 用户菜单列表
     */
    @ResponseBody
    @RequestMapping("/user")
    public Result user() {
        List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());

        return Result.ok().put("menuList", menuList);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new BaseException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new BaseException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Global.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new BaseException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Global.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Global.MenuType.CATALOG.getValue() ||
                menu.getType() == Global.MenuType.MENU.getValue()) {
            if (parentType != Global.MenuType.CATALOG.getValue()) {
                throw new BaseException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Global.MenuType.BUTTON.getValue()) {
            if (parentType != Global.MenuType.MENU.getValue()) {
                throw new BaseException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
