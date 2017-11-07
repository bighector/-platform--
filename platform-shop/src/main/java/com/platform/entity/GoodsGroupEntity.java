package com.platform.entity;

import java.io.Serializable;


/**
 * 团购实体
 * 表名 nideshop_goods_group
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-09-06 17:18:30
 */
public class GoodsGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //标题
    private String title;
    //活动条例图片
    private String itemPicUrl;
    //浏览数
    private Integer readCount;
    //商品
    private Integer goodsId;
    //排序
    private Integer sortOrder;
    //子标题
    private String subtitle;

    /**
     * 翻译用字段
     */
    //商品名称
    private String goodsName;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：团购大图
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：团购大图
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：活动条例图片
     */
    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
    }

    /**
     * 获取：活动条例图片
     */
    public String getItemPicUrl() {
        return itemPicUrl;
    }

    /**
     * 设置：
     */
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    /**
     * 获取：
     */
    public Integer getReadCount() {
        return readCount;
    }

    /**
     * 设置：
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取：
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置：
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取：
     */
    public String getSubtitle() {
        return subtitle;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
