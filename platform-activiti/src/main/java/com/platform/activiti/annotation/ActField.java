package com.platform.activiti.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在实体类中对字段进行注解，用于流程表单权限和分支条件设置
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年11月16日 下午10:43:36
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ActField {
    /**
     * 显示名字
     *
     * @return
     */
    String name();

    /**
     * 是否用于分支条件判断
     *
     * @return
     */
    boolean isJudg() default false;
}
