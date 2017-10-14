package com.platform.service.impl;

import com.platform.dao.GoodsAttributeDao;
import com.platform.dao.GoodsDao;
import com.platform.dao.ProductDao;
import com.platform.entity.GoodsAttributeEntity;
import com.platform.entity.GoodsEntity;
import com.platform.entity.ProductEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.GoodsService;
import com.platform.service.UserDefineService;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import com.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-21 21:19:49
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsAttributeDao goodsAttributeDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDefineService userDefineService;

    @Override
    public GoodsEntity queryObject(Integer id) {
        return goodsDao.queryObject(id);
    }

    @Override
    public List<GoodsEntity> queryList(Map<String, Object> map) {
        String sql = builderSql().toString();
        if (!StringUtils.isNullOrEmpty(map.get("name"))) {
            sql += " AND nideshop_goods.name LIKE %:name%";
        }
        sql = userDefineService.appOrgAndCreateUserDataPermission("nideshop_goods.create_user_dept_id", "nideshop_goods.create_user_id", sql, map);
        if (!StringUtils.isNullOrEmpty(map.get("offset")) && !StringUtils.isNullOrEmpty(map.get("limit"))) {
            sql += " limit :offset, :limit";
        }
        return userDefineService.findObjForJdbc(sql, map, GoodsEntity.class);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        String sql = "SELECT COUNT(1) FROM nideshop_goods\n" +
                "        LEFT JOIN nideshop_category\n" +
                "          ON nideshop_goods.category_id = nideshop_category.id\n" +
                "        LEFT JOIN nideshop_attribute_category ON nideshop_goods.attribute_category = nideshop_attribute_category.id\n" +
                "        LEFT JOIN nideshop_brand ON nideshop_brand.id = nideshop_goods.brand_id\n" +
                "      WHERE 1 = 1";
        if (!StringUtils.isNullOrEmpty(map.get("name"))) {
            sql += " AND nideshop_goods.name LIKE %:name%";
        }
        sql = userDefineService.appOrgAndCreateUserDataPermission("nideshop_goods.create_user_dept_id", "nideshop_goods.create_user_id", sql, map);
        return userDefineService.getCountForJdbcParam(sql, map);
    }

    @Override
    @Transactional
    public int save(GoodsEntity goods) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("name", goods.getName());
        List<GoodsEntity> list = queryList(map);
        if (null != list && list.size() != 0) {
            throw new RRException("商品名称已存在！");
        }
        Integer id = goodsDao.queryMaxId() + 1;
        goods.setId(id);

        //保存产品信息
        ProductEntity productEntity = new ProductEntity();
        productEntity.setGoodsId(id);
        productEntity.setGoodsSn(goods.getGoodsSn());
        productEntity.setGoodsNumber(goods.getGoodsNumber());
        productEntity.setRetailPrice(goods.getRetailPrice());
        productDao.save(productEntity);

        goods.setAddTime(new Date());
        goods.setPrimaryProductId(productEntity.getId());

        //保存商品详情页面显示的属性
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity item : attributeEntityList) {
                item.setGoodsId(id);
                goodsAttributeDao.save(item);
            }
        }

        goods.setCreateUserId(user.getUserId());
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsDao.save(goods);
    }

    @Override
    @Transactional
    public int update(GoodsEntity goods) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        List<GoodsAttributeEntity> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttributeEntity goodsAttributeEntity : attributeEntityList) {
                int result = goodsAttributeDao.updateByGoodsIdAttributeId(goodsAttributeEntity);
                if (result == 0) {
                    goodsAttributeDao.save(goodsAttributeEntity);
                }
            }
        }
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsDao.update(goods);
    }

    @Override
    public int delete(Integer id) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        goodsEntity.setIsDelete(1);
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }

    @Override
    @Transactional
    public int deleteBatch(Integer[] ids) {
        int result = 0;
        for (Integer id : ids) {
            result += delete(id);
        }
        return result;
    }

    @Override
    @Transactional
    public int back(Integer[] ids) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        int result = 0;
        for (Integer id : ids) {
            GoodsEntity goodsEntity = queryObject(id);
            goodsEntity.setIsDelete(0);
            goodsEntity.setIsOnSale(1);
            goodsEntity.setUpdateUserId(user.getUserId());
            goodsEntity.setUpdateTime(new Date());
            result += goodsDao.update(goodsEntity);
        }
        return result;
    }

    @Override
    public int enSale(Integer id) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (1 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于上架状态！");
        }
        goodsEntity.setIsOnSale(1);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }

    @Override
    public int unSale(Integer id) {
        SysUserEntity user = ShiroUtils.getUserEntity();
        GoodsEntity goodsEntity = queryObject(id);
        if (0 == goodsEntity.getIsOnSale()) {
            throw new RRException("此商品已处于下架状态！");
        }
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(user.getUserId());
        goodsEntity.setUpdateTime(new Date());
        return goodsDao.update(goodsEntity);
    }

    private StringBuffer builderSql() {

        StringBuffer sb = new StringBuffer();
        sb.append("select\n");
        sb.append("        nideshop_goods.id,\n");
        sb.append("        nideshop_goods.category_id,\n");
        sb.append("        nideshop_goods.goods_sn,\n");
        sb.append("        nideshop_goods.name,\n");
        sb.append("        nideshop_goods.brand_id,\n");
        sb.append("        nideshop_goods.goods_number,\n");
        sb.append("        nideshop_goods.keywords,\n");
        sb.append("        nideshop_goods.goods_brief,\n");
        sb.append("        nideshop_goods.goods_desc,\n");
        sb.append("        nideshop_goods.is_on_sale,\n");
        sb.append("        nideshop_goods.add_time,\n");
        sb.append("        nideshop_goods.update_time,\n");
        sb.append("        nideshop_goods.sort_order,\n");
        sb.append("        nideshop_goods.is_delete,\n");
        sb.append("        nideshop_goods.attribute_category,\n");
        sb.append("        nideshop_goods.counter_price,\n");
        sb.append("        nideshop_goods.extra_price,\n");
        sb.append("        nideshop_goods.is_new,\n");
        sb.append("        nideshop_goods.goods_unit,\n");
        sb.append("        nideshop_goods.primary_pic_url,\n");
        sb.append("        nideshop_goods.list_pic_url,\n");
        sb.append("        nideshop_goods.retail_price,\n");
        sb.append("        nideshop_goods.sell_volume,\n");
        sb.append("        nideshop_goods.primary_product_id,\n");
        sb.append("        nideshop_goods.unit_price,\n");
        sb.append("        nideshop_goods.promotion_desc,\n");
        sb.append("        nideshop_goods.promotion_tag,\n");
        sb.append("        nideshop_goods.app_exclusive_price,\n");
        sb.append("        nideshop_goods.is_app_exclusive,\n");
        sb.append("        nideshop_goods.is_limited,\n");
        sb.append("        nideshop_goods.is_hot,\n");
        sb.append("        nideshop_goods.market_price,\n");
        sb.append("        nideshop_goods.create_user_id,\n");
        sb.append("        nideshop_goods.create_user_dept_id,\n");
        sb.append("        nideshop_goods.update_user_id,\n");
        sb.append("        nideshop_category.name category_name,\n");
        sb.append("        nideshop_attribute_category.name attribute_category_name,\n");
        sb.append("        nideshop_brand.name brand_name\n");
        sb.append("        from nideshop_goods\n");
        sb.append("        LEFT JOIN nideshop_category\n");
        sb.append("        ON nideshop_goods.category_id = nideshop_category.id\n");
        sb.append("        LEFT JOIN nideshop_attribute_category ON nideshop_goods.attribute_category = nideshop_attribute_category.id\n");
        sb.append("        LEFT JOIN nideshop_brand ON nideshop_brand.id = nideshop_goods.brand_id\n");
        sb.append("        WHERE 1=1");
        return sb;
    }
}
