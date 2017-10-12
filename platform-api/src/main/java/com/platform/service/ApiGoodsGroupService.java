package com.platform.service;

import com.platform.dao.ApiGoodsGroupMapper;
import com.platform.entity.GoodsGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsGroupService {
    @Autowired
    private ApiGoodsGroupMapper apiGoodsGroupMapper;


    public GoodsGroupVo queryObject(Integer id) {
        return apiGoodsGroupMapper.queryObject(id);
    }

    public List<GoodsGroupVo> queryList(Map<String, Object> map) {
        return apiGoodsGroupMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return apiGoodsGroupMapper.queryTotal(map);
    }

    public void save(GoodsGroupVo order) {
        apiGoodsGroupMapper.save(order);
    }

    public void update(GoodsGroupVo order) {
        apiGoodsGroupMapper.update(order);
    }

    public void delete(Integer id) {
        apiGoodsGroupMapper.delete(id);
    }

    public void deleteBatch(Integer[] ids) {
        apiGoodsGroupMapper.deleteBatch(ids);
    }

}
