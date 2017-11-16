package com.platform.activiti.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在实体类中对字段进行注解，业务表名
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年11月16日 下午10:43:36
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActTable {
    /**
     * 业务表名
     *
     * @return
     */
    String tableName();

    /**
     * 数据库主键
     *
     * @return
     */
    String pkName();
}
