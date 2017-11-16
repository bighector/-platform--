package com.platform.utils;

/**
 * 常量
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    public static final String USE_DATA = "MYSQL";//ORACLE、MYSQL

    /**
     * 流程会签集合名称
     */
    public static final String ACT_MUIT_LIST_NAME="users";
    /**
     * 流程会签变量名称
     */
    public static final String ACT_MUIT_VAR_NAME="user";
    /**
     * 分页条数
     */
    public static final int pageSize=10;

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

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 是否类型
     */
    public enum YESNO{
        /**
         * 是
         */
        YES("0"),
        /**
         * 否
         */
        NO("1");
        private String value;

        private YESNO(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    }
    /**
     * 审批节点行为
     */
    public enum ActAction {
        /**
         * 审批
         */
        APPROVE("1"),
        /**
         * 会签
         */
        MULIT("2");

        private String value;

        private ActAction(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 业务树类型
     */
    public enum ActBusType {
        /**
         * 根节点
         */
        ROOT("1"),
        /**
         * 分组
         */
        GROUP("2"),
        /**
         * 业务类
         */
        BUS("3"),
        /**
         * 回调
         */
        BACK("4");

        private String value;

        private ActBusType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 流程节点类型
     */
    public enum NodeType {
        /**
         * 开始节点
         */
        START("1"),
        /**
         * 审批节点
         */
        EXAMINE("2"),

        /**
         * 分支
         */
        BRUNCH("3"),
        /**
         * 连线
         */
        LINE("4"),
        /**
         * 结束
         */
        END("5");


        private String value;

        private NodeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 审批者类型
     */
    public enum ExamineType {
        /**
         * 用户
         */
        USER("1"),
        /**
         * 角色
         */
        ROLE("2"),

        /**
         * 组织
         */
        ORGAN("3");

        private String value;

        private ExamineType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 流程任务审批结果
     */
    public enum ActTaskResult {
        /**
         * 同意
         */
        AGREE("1"),
        /**
         * 反对
         */
        NO_AGREE("2"),
        /**
         * 弃权
         */
        ABSTAINED("3"),
        /**
         * 驳回
         */
        TURN_DOWN("4"),
        /**
         * 转办
         */
        TURN_DO("5");

        private String value;

        private ActTaskResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 流程状态
     */
    public enum ActStauts {
        /**
         * 草稿
         */
        DRAFT("1"),
        /**
         * 审批中
         */
        APPROVAL("2"),
        /**
         * 结束
         */
        END("3");

        private String value;

        private ActStauts(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 整个流程的审批结果
     */
    public enum ActResult {
        /**
         * 同意
         */
        AGREE("1"),
        /**
         * 不同意
         */
        NO_AGREE("2"),
        /**
         * 审批中
         */
        DISAGREE("3");

        private String value;

        private ActResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 通知类型
     */
    public enum noticeType {
        /**
         *普通通知
         */
        UL_NOTICE("1"),
        /**
         * 流程通知
         */
        ACT_NOTICE("2");

        private String value;

        private noticeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
