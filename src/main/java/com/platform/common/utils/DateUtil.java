package com.platform.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 名称：DateUtil <br>
 * 描述：日期处理工具类<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class DateUtil {
    // 中文日期格式
    public static final String DEFAULT_DATE_FORMAT_CN = "yyyy年MM月dd日";

    // 标准的日期格式
    public static final String DEFAULT_DATE_FORMAT_EN = "yyyy-MM-dd";

    // 到毫秒日期格式
    public static final String DEFAULT_DATETIME_FORMAT = "yyyyMMddhhmmssms";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // 一天是多少毫秒
    public static final long DAY = 24L * 60L * 60L * 1000L;

    /**
     * 根据格式化字符串格式化日期
     *
     * @param date 格式化前日期
     * @return 格式化后日期字符串（yyyy-MM-dd）
     */
    public static String format(Date date) {
        String s = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_EN);
            s = sdf.format(date);
        }
        return s;
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 根据格式化字符串格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 说明：获取年
     * 备注：
     *
     * @param date 格式：yyyy-MM-dd
     * @return 年份
     * @throws ParseException 处理异常
     */
    public static int getYear(String date)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int ke = cal.get(Calendar.YEAR);
        return ke;
    }
}
