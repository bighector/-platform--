package com.platform.service.impl;

import com.platform.dao.SysRoleDeptDao;
import com.platform.service.UserDefineService;
import com.platform.utils.BeanUtils;
import com.platform.utils.RRException;
import com.platform.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月11日 下午9:20:48
 */
@Service("UserDefineService")
public class UserDefineServiceImpl implements UserDefineService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

    /**
     * 主要功能:根据sql查询数据，返回指定类型集合 注意事项:无
     *
     * @param sql    sql
     * @param params 参数
     * @param clazz  对象类
     * @param <T>    泛型指数据对象
     * @return 数据集合
     */
    @Override
    public <T> List<T> findObjForJdbc(String sql, Map<String, Object> params,
                                      Class<T> clazz) {
        List<Map<String, Object>> mapList = namedParameterJdbcTemplate.queryForList(sql, params);
        return convertListOfMap2Bean(mapList, clazz);
    }

    /**
     * 主要功能:查询sql列表 注意事项:无
     *
     * @param sql  sql
     * @param objs 参数对象【数组型或多个参数形式】
     * @return 数据集合
     */
    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.joyintech.platform.service.SysDataPermissionService#appOrgDataPermission(java.lang.String
     * , java.lang.String)
     */
    @Override
    public String appOrgDataPermission(String alias, String sql, Map<String, Object> map) {
        return appOrgAndCreateUserDataPermission(alias, null, sql, map);
    }

    /*
     * (non-Javadoc)
     * @see com.joyintech.platform.service.SysDataPermissionService#appOrgAndCreateUserDataPermission(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
     */
    @Override
    public String appOrgAndCreateUserDataPermission(String orgAlias, String userAlias, String sql, Map<String, Object> map) {
        if (StringUtils.isBlank(sql)) {
            throw new RRException("查询sql为空");
        }
        if (StringUtils.isBlank(orgAlias)) {
            throw new RRException("机构标识别名为空");
        }

        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        if (!sql.toUpperCase().contains("WHERE")) {
            sb.append(" WHERE 1=1 ");
        }
        Long loginId = ShiroUtils.getUserId();
        List<Long> roleOrglist = sysRoleDeptDao.queryDeptIdListByUserId(loginId);

        StringBuilder roleStr = new StringBuilder();
        if (roleOrglist != null && !roleOrglist.isEmpty()) {
            for (Long deptId : roleOrglist) {
                roleStr.append(",");
                roleStr.append("'");
                roleStr.append(deptId);
                roleStr.append("'");
            }
            String roles = roleStr.toString().substring(1, roleStr.length());
            sb.append(" and (");
            sb.append(orgAlias);
            sb.append(" in ");
            sb.append(" ( ");
            sb.append(roles);
            sb.append(" ) ");
            if (StringUtils.isNotBlank(userAlias)) {
                sb.append(" or " + userAlias + "=:loginUserId ");
            }
            sb.append(" ) ");
        } else if (StringUtils.isNotBlank(userAlias)) {
            sb.append(" and " + userAlias + "=:loginUserId");
        }
        map.put("loginUserId", loginId);
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see com.joyintech.platform.service.SysDataPermissionService#appOrgAndCreateUserDataPermission(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
     */
    @Override
    public String appOrgAndCreateUserDataPermission(String orgAlias, String userAlias, String otherOrgAlias, String otherUserAlias, String sql,
                                                    Map<String, Object> map) {
        if (StringUtils.isBlank(sql)) {
            throw new RRException("查询sql为空");
        }
        if (StringUtils.isBlank(orgAlias)) {
            throw new RRException("机构标识别名为空");
        }

        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        if (!sql.toUpperCase().contains("WHERE")) {
            sb.append(" WHERE 1=1 ");
        }
        Long loginId = ShiroUtils.getUserId();
        List<Long> roleOrglist = sysRoleDeptDao.queryDeptIdListByUserId(loginId);
        StringBuilder roleStr = new StringBuilder();
        if (roleOrglist != null && !roleOrglist.isEmpty()) {
            for (Long deptId : roleOrglist) {
                roleStr.append(",");
                roleStr.append("'");
                roleStr.append(deptId);
                roleStr.append("'");
            }
            String roles = roleStr.toString().substring(1, roleStr.length());
            sb.append(" and (");
            sb.append(orgAlias);
            sb.append(" in ");
            sb.append(" ( ");
            sb.append(roles);
            sb.append(" ) ");
            if (StringUtils.isNotBlank(userAlias)) {
                sb.append(" or " + userAlias + "=:loginUserId " + " or " + otherUserAlias + "=:loginUserId ");
            }
            sb.append(" ) ");
        } else if (StringUtils.isNotBlank(userAlias)) {
            sb.append(" and (" + userAlias + "=:loginUserId" + " or " + otherUserAlias + "=:loginUserId )");
        }
        map.put("loginUserId", loginId);
        return sb.toString();
    }

    @Override
    public void executeSql(String sql) {
        jdbcTemplate.execute(sql);
    }

    /**
     * 主要功能:集合类型Map转换成实体 注意事项:无
     *
     * @param mapList mapList数据集合
     * @param clazz   实体类
     * @param <T>     实体定义
     * @return 实体集合
     */
    private <T> List<T> convertListOfMap2Bean(List<Map<String, Object>> mapList, Class<T> clazz) {
        List<T> tList = new ArrayList<T>();
        T po = null;
        for (Map<String, Object> m : mapList) {
            try {
                po = clazz.newInstance();
                BeanUtils.map2Bean(m, po);
                tList.add(po);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tList;
    }

}
