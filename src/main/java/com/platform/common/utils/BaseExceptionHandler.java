package com.platform.common.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * 名称：BaseExceptionHandler <br>
 * 描述：异常处理器<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public Result handleRRException(BaseException e) {
        Result r = new Result();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error();
    }


    @ExceptionHandler(ApiBaseException.class)
    public Object handleApiRRException(ApiBaseException e) {
        HashMap result = new HashMap();
        result.put("errno", e.getErrno());
        result.put("errmsg", e.getErrmsg());
        return result;
    }
}
