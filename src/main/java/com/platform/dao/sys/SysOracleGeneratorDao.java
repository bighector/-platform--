package com.platform.dao.sys;

import com.platform.entity.ResultMap;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysOracleGeneratorDao <br>
 * 描述：oracle代码生成器<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysOracleGeneratorDao {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<ResultMap> queryColumns(String tableName);
}
