package com.platform.entity;

import java.io.Serializable;


/**
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-15 08:03:40
 */
public class FeedbackVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer msg_id;
    //父节点
    private Integer parent_id;
    //会员Id
    private Long user_id;
    //会员名称
    private String user_name;
    //邮件
    private String user_email;
    //标题
    private String msg_title;
    //类型
    private Integer msg_type;
    //状态
    private Integer msg_status;
    //详细内容
    private String msg_content;
    //反馈时间
    private Long msg_time;
    //图片
    private String message_img;
    //订单Id
    private Integer order_id;
    //
    private Integer msg_area;

    public Integer getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Integer msg_id) {
        this.msg_id = msg_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public void setMsg_title(String msg_title) {
        this.msg_title = msg_title;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Integer msg_type) {
        this.msg_type = msg_type;
    }

    public Integer getMsg_status() {
        return msg_status;
    }

    public void setMsg_status(Integer msg_status) {
        this.msg_status = msg_status;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public Long getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(Long msg_time) {
        this.msg_time = msg_time;
    }

    public String getMessage_img() {
        return message_img;
    }

    public void setMessage_img(String message_img) {
        this.message_img = message_img;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getMsg_area() {
        return msg_area;
    }

    public void setMsg_area(Integer msg_area) {
        this.msg_area = msg_area;
    }
}
