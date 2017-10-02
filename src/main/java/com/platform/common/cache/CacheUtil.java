package com.platform.common.cache;

import org.springframework.beans.factory.InitializingBean;

/**
 * 名称：CacheUtil <br>
 * 描述：缓存工具类<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class CacheUtil implements InitializingBean {


    public static void init() {
        System.out.println("-----------------加载缓存CacheUtil-----------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}