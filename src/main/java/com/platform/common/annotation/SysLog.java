package com.platform.common.annotation;

import java.lang.annotation.*;

/**
 * 名称：SysLog <br>
 * 描述：系统日志注解<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "操作日志";
}
