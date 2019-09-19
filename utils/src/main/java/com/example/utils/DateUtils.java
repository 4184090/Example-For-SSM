package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    /**
     * 日期转字符串
     * @param date 要转换的日期
     * @param strFormat 转换的字符串格式
     * @return 转换后的日期字符串
     */
    public static String DateToString(Date date,String strFormat){
        return new SimpleDateFormat(strFormat).format(date);
    }

    /**
     * 字符串转日期
     * @param strDate 要转换的日期字符串
     * @param dateFormat 转换的日期格式
     * @return 转换后的日期
     * @throws ParseException 可能出现的异常
     */
    public static Date StringToDate(String strDate,String dateFormat) throws ParseException {
        return new SimpleDateFormat(dateFormat).parse(strDate);
    }
}
