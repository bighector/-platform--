package com.platform.controller.sys;

import com.platform.common.annotation.SysLog;
import com.platform.common.utils.Result;
import com.platform.common.utils.ShiroUtils;
import com.platform.entity.sys.SysMenu;
import com.platform.entity.sys.SysUser;
import com.platform.service.sys.SysMenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 名称：SysLoginController <br>
 * 描述：登录相关<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@Controller
public class SysLoginController {

    @Autowired
    SysMenuService sysMenuService;

    /**
     * 登录
     */
    @SysLog("登录")
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Result login(SysUser user) throws IOException {

        String userName = user.getUserName();
        String passWord = user.getPassWord();

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            passWord = new Sha256Hash(passWord).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.error(e.getMessage());
        } catch (LockedAccountException e) {
            return Result.error(e.getMessage());
        } catch (AuthenticationException e) {
            return Result.error("账户验证失败");
        }

        return Result.ok();
    }

    /**
     * 退出
     */
    @ResponseBody
    @RequestMapping(value = "/sys/logout")
    public Result logout() {
        ShiroUtils.logout();
        return Result.ok();
    }

    /**
     * 跳转到登录页
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        SysUser curUser = ShiroUtils.getCurUser();
        List<SysMenu> menuList = sysMenuService.getUserMenuList(curUser.getUserId());
        model.put("curUser", curUser);
        model.put("menuList", menuList);
        return "index";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }
}
