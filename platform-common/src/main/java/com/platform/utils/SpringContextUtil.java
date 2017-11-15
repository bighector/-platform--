package com.platform.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 名称：SpringContextUtil <br>
 * 描述：spring 上下文<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext 上下文
     * @throws BeansException 异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * @return ApplicationContext返回给其它模块使用
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 主要功能: 获取bean
     * 注意事项:无
     *
     * @param arg0 参数
     * @return 返回Bean实例
     */
    public static Object getBean(String arg0) {
        return APPLICATION_CONTEXT.getBean(arg0);
    }

    /**
     * 主要功能:获取bean并转换对象
     * 注意事项:无
     *
     * @param beanType bean类型
     * @param <T>      泛型
     * @return 返回Bean实例
     */
    public static <T> T getBean(Class<T> beanType) {
        return APPLICATION_CONTEXT.getBean(beanType);
    }
}