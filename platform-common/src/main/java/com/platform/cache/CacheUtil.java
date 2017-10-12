package com.platform.cache;

import org.springframework.beans.factory.InitializingBean;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-16 10:14<br>
 * 描述: CacheUtil <br>
 */
public class CacheUtil implements InitializingBean {


    public static void init() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}