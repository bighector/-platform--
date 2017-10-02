package com.platform.controller;

import com.platform.common.utils.ShiroUtils;
import com.platform.entity.sys.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 名称：AbstractController <br>
 * 描述：Controller公共组件<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return ShiroUtils.getCurUser();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

}
