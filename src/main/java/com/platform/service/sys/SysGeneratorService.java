package com.platform.service.sys;

import java.util.List;
import java.util.Map;

/**
 * 名称：SysGeneratorService <br>
 * 描述：代码生成器<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public interface SysGeneratorService {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    /**
     * 生成代码
     */
    byte[] generatorCode(String[] tableNames);
}
