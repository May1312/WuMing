package com.test.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yhang on 2016/10/28.
 */
public class DateUtils {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat DATE_FORMAT3 = new SimpleDateFormat("yyyy");

    public static String convert2String(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String convert2String2(Date date)
    {
        return DATE_FORMAT2.format(date);
    }
    public static Date time2Date(Long date) throws ParseException {
        String time = DATE_FORMAT2.format(date);
        return DATE_FORMAT2.parse(time);
    }

    public static String time2Year(Date date) {
        return DATE_FORMAT3.format(date);
    }

    public static Date parseDate(String str, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date fetchBeginOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date fetchEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTime();
    }
    //根据年/月获取对应的月份-天数
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    //获取当前时间 - 年份
    public static int getCurrentYear(){
        Calendar a=Calendar.getInstance();
        return a.get(Calendar.YEAR);
    }
    //获取当前时间 - 月份
    public static int getCurrentMonth(){
        Calendar a=Calendar.getInstance();
        return (a.get(Calendar.MONTH)+1);
    }
    //获取当前时间 - 天数
    public static int getCurrentDay(){
        Calendar a=Calendar.getInstance();
        return a.get(Calendar.DATE);
    }
    //获取当前时间 年/月
    public static String getCurrentYearMonth(){
        Calendar a=Calendar.getInstance();
        return a.get(Calendar.YEAR)+"-"+(a.get(Calendar.MONTH)+1)+"";
    }
}
