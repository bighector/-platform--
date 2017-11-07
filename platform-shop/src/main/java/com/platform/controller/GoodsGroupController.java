package com.platform.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.GoodsGroupEntity;
import com.platform.service.GoodsGroupService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * 团购Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-09-06 17:18:30
 */
@RestController
@RequestMapping("goodsgroup")
public class GoodsGroupController {
    @Autowired
    private GoodsGroupService goodsGroupService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsgroup:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GoodsGroupEntity> goodsGroupList = goodsGroupService.queryList(query);
        int total = goodsGroupService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsGroupList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsgroup:info")
    public R info(@PathVariable("id") Integer id) {
        GoodsGroupEntity goodsGroup = goodsGroupService.queryObject(id);

        return R.ok().put("goodsGroup", goodsGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsgroup:save")
    public R save(@RequestBody GoodsGroupEntity goodsGroup) {
        goodsGroupService.save(goodsGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsgroup:update")
    public R update(@RequestBody GoodsGroupEntity goodsGroup) {
        goodsGroupService.update(goodsGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsgroup:delete")
    public R delete(@RequestBody Integer[]ids) {
        goodsGroupService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<GoodsGroupEntity> list = goodsGroupService.queryList(params);

        return R.ok().put("list", list);
    }
}
