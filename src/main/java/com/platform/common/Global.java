package com.platform.common;

/**
 * 名称：Global <br>
 * 描述：全局属性<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class Global {
    /**
     * 排序方式名称 asc:正序 | desc:倒序
     */
    public static final String SORT_ORDER = "sortOrder";
    /**
     * 当前登录用户
     */
    public static final String CURRENT_USER = "curUser";
    /**
     * 默认密码
     */
    public static final String DEFAULT_PASS_WORD = "888888";

    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    public static final String USE_DATA = "MYSQL";//ORACLE、MYSQL

    /**
     * 菜单类型
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author lipengjun
     * @email 939961241@qq.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
