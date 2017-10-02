package com.platform.dao;

import java.util.List;
import java.util.Map;

/**
 * 名称：BaseDao <br>
 * 描述：基础Dao(还需在XML文件里，有对应的SQL语句)<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface BaseDao<T> {

    int save(T t);

    int save(Map<String, Object> map);

    int saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
