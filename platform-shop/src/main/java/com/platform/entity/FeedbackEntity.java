package com.platform.entity;

import java.io.Serializable;


/**
 * 实体
 * 表名 nideshop_feedback
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 15:03:25
 */
public class FeedbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer msgId;
    //父节点
    private Integer parentId;
    //会员Id
    private Integer userId;
    //会员名称
    private String userName;
    //邮件
    private String userEmail;
    //标题
    private String msgTitle;
    //类型
    private Integer msgType;
    //状态
    private Integer msgStatus;
    //详细内容
    private String msgContent;
    //反馈时间
    private Integer msgTime;
    //图片
    private String messageImg;
    //订单Id
    private Integer orderId;
    //
    private Integer msgArea;

    /**
     * 设置：主键
     */
    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取：主键
     */
    public Integer getMsgId() {
        return msgId;
    }

    /**
     * 设置：父节点
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父节点
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：会员Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：会员名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取：会员名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置：邮件
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取：邮件
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置：标题
     */
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    /**
     * 获取：标题
     */
    public String getMsgTitle() {
        return msgTitle;
    }

    /**
     * 设置：类型
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取：类型
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 设置：状态
     */
    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     * 获取：状态
     */
    public Integer getMsgStatus() {
        return msgStatus;
    }

    /**
     * 设置：详细内容
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * 获取：详细内容
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 设置：反馈时间
     */
    public void setMsgTime(Integer msgTime) {
        this.msgTime = msgTime;
    }

    /**
     * 获取：反馈时间
     */
    public Integer getMsgTime() {
        return msgTime;
    }

    /**
     * 设置：图片
     */
    public void setMessageImg(String messageImg) {
        this.messageImg = messageImg;
    }

    /**
     * 获取：图片
     */
    public String getMessageImg() {
        return messageImg;
    }

    /**
     * 设置：订单Id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单Id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置：
     */
    public void setMsgArea(Integer msgArea) {
        this.msgArea = msgArea;
    }

    /**
     * 获取：
     */
    public Integer getMsgArea() {
        return msgArea;
    }
}
