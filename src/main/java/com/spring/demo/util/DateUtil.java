package com.spring.demo.util;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

public class DateUtil {

    public static String parseDateByTimeZone(Date date, String patten, String timeZone) {
        if (StringUtils.isEmpty(patten) || StringUtils.isEmpty(timeZone)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(patten);
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
        return df.format(date);
    }

    public static int daysBetween(final Date date1, final Date date2) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        final long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        final long time2 = cal.getTimeInMillis();
        final long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int hoursBetween(final Date start, final Date end) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        final long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        final long time2 = cal.getTimeInMillis();
        final long between_hours = (time2 - time1) / (1000 * 3600);

        return Integer.parseInt(String.valueOf(between_hours));
    }

    public static int minutesBetween(final Date start, final Date end) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        final long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        final long time2 = cal.getTimeInMillis();
        final long between_minutes = (time2 - time1) / (1000 * 60) % 60;

        return Integer.parseInt(String.valueOf(between_minutes));
    }

    public static int secondsBetween(final Date start, final Date end) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        final long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        final long time2 = cal.getTimeInMillis();
        final long between_seconds = (time2 - time1) / 1000 % 60;

        return Integer.parseInt(String.valueOf(between_seconds));
    }

    public static Date getNewDate(final int days) {
        final Calendar cal = Calendar.getInstance();
        final Date date = new Date();
        cal.setTime(date);
        final long time1 = cal.getTimeInMillis();
        final long time_days = days * 1000 * 3600 * 24;
        final long time = time1 + time_days;
        return new Date(time);
    }

    /*
     * 计算某一个日期后几小时的日期
     *
     * @Param date1
     *
     * @Param hours
     *
     * @return
     */
    public static Date addHoursToDate(final Date date, final int hours) {

        return new DateTime(date).plusHours(hours).toDate();
    }

    public static Date addDaysToDate(final Date date, final int days) {

        return new DateTime(date).plusDays(days).toDate();
    }

    public static Date addMonthsToDate(final Date date, final int months) {

        return new DateTime(date).plusMonths(months).toDate();
    }

    public static Date addMinuteToDate(final Date date, final int minutes) {

        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    public static Date minusHoursToDate(final Date date, final int hours) {

        return new DateTime(date).minusHours(hours).toDate();
    }

    public static Date minusMinuteToDate(final Date date, final int minutes) {

        return new DateTime(date).minusMinutes(minutes).toDate();
    }

    /**
     * @return
     * @description: 按日期格式返回月份第一天.
     * @author: liuxmi
     * @Date:Sep 9, 2009
     * @return:String
     */
    public static Date getFirstDayOfMonth(final Date date) {
        if (date == null) {
            return date;
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        final Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), now.get(Calendar.DATE));
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * @return
     * @description: 按日期格式返回月份最后天.
     * @author: liuxmi
     * @Date:Sep 9, 2009
     * @return:String
     */
    public static Date getFirstDayOfNextMonth() {
        final Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getStartDate(final Date date) {
        if (date == null) {
            return new Date();
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        final Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getEndDate(final Date date) {
        if (date == null) {
            return new Date();
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        final Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), now.get(Calendar.DATE) + 1, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getLastMonthOfFirstDay(final Date date) {
        if (date == null) {
            return new Date();
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        if (now.get(Calendar.MONTH) > 1) {
            Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH) - 1, 1, 0, 0, 0);
            return calendar.getTime();
        } else {
            Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR) - 1,
                    Calendar.DECEMBER, 1, 0, 0, 0);
            return calendar.getTime();
        }
    }

    public static Date getThisMonthOfFirstDay(final Date date) {
        if (date == null) {
            return new Date();
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), 1, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getLastMonthOfLastDay(final Date date) {
        if (date == null) {
            return new Date();
        }
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        Calendar calendar = new GregorianCalendar(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), 1, 0, 0, 0);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    @Test
    public void test1() {
        Date lastMonthOfFirstDay = getLastMonthOfLastDay(DateUtil.parseStr2Date("2018-03-28"));
        System.out.println(lastMonthOfFirstDay);
    }

    public static Date parseStr2Date(final String dateStr) {
        return parseStr2Date(dateStr, null);
    }

    public static Date parseStr2Date(final String dateStr, String pattern) {
        return parseStr2Date(dateStr, pattern, TimeZone.getDefault());

    }

    public static Date parseStr2Date(final String dateStr, String pattern, TimeZone timeZone) {
        if (dateStr == null) {
            return null;
        }
        if (pattern == null || pattern.equals("")) {
            pattern = "yyyy-MM-dd";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        return sdf.parse(dateStr, new ParsePosition(0));

    }

    public static Date parseStr2Date(final String dateStr, String pattern,
                                     Locale type) {
        if (dateStr == null) {
            return null;
        }
        if (pattern == null || pattern.equals("")) {
            pattern = "yyyy-MM-dd";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern, type);

        return sdf.parse(dateStr, new ParsePosition(0));

    }

    public static String dateFormat(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }

    public static String dateFormatGMT(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return dateFormat.format(date);
    }

    public static String dateFormatGMTLenglish(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "d MMM, yyyy h:m:s aa", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return dateFormat.format(date);
    }

    public static Date stringToDate(final String date) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date);
    }

    public static String dateFormat(final Date date, final String format) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String dateFormatWithTimezone(final Date date, final String format, TimeZone timezone) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timezone);
        return dateFormat.format(date);
    }

    public static String dateFormatYMD(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(date);
    }

    public static String dateFormatYMDchinese(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return dateFormat.format(date);
    }

    public static String showSpendTime(Long startTime, final String name) {
        final String result = "%s用时：" + (new Date().getTime() - startTime)
                + "s";
        startTime = new Date().getTime();
        return String.format(result, name);
    }

    public static String afterOrBeforeNDay(String currentDate, int n) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayBefore;
    }

    public static String afterOrBeforeNMonth(String currentDate, int n) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + n);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayBefore;
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); //
        // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    public static Date getMonthEndByStart(Date startDate) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        cl.add(Calendar.MONTH, 1);
        cl.add(Calendar.DAY_OF_MONTH, -1);
        return cl.getTime();
    }

    public static Date getNextMonthStartByStart(Date startDate) {
        return getFirstDayFromDateByMonthCalc(startDate, 1);
    }

    public static Date getNextDayStartByStart(Date startDate) {
        return getFirstDayFromDateByDayCalc(startDate, 1);
    }

    public static Date getNextYearStartByStart(Date startDate) {
        return getFirstDayFromDateByYearCalc(startDate, 1);
    }


    /**
     * 根据某一天计算相差月份的1号
     *
     * @param startDate
     * @return firstDay
     */

    public static Date getFirstDayFromDateByDayCalc(Date startDate, int calc) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        cl.add(Calendar.DAY_OF_YEAR, calc);
        return cl.getTime();
    }


    public static Date getFirstDayFromDateByWeekCalc(Date startDate, int calc) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        cl.add(Calendar.WEEK_OF_YEAR, calc);
        return cl.getTime();
    }

    public static Date getFirstDayFromDateByMonthCalc(Date startDate,
                                                      int calc) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        cl.add(Calendar.MONTH, calc);
        return cl.getTime();
    }


    public static Date getFirstDayFromDateByYearCalc(Date startDate, int calc) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(startDate);
        cl.add(Calendar.YEAR, calc);
        return cl.getTime();
    }

    public static Date getNextWeekStartByStart(Date startDate) {
        return getFirstDayFromDateByWeekCalc(startDate, 1);
    }


    public static String getWeek(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        if (month == 0 && day < 7 && week > 1) {
            year = year - 1;
        }
        return year + "年" + String.format("%02d", week) + "周";
    }

    public static String getWeekNew(String str) {
        WeekFields wfs = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate inputDate = LocalDate.parse(str);
        // 所在周开始时间
        int num = inputDate.get(wfs.weekOfWeekBasedYear());
        LocalDate beginDayOfWeek = inputDate.with(DayOfWeek.MONDAY);
        // 所在周结束时间
        LocalDate endDayOfWeek = inputDate.with(DayOfWeek.SUNDAY);
        return num + "周(" + beginDayOfWeek.getMonthValue() + "." + beginDayOfWeek.getDayOfMonth() + "-" + endDayOfWeek.getMonthValue() + "." + endDayOfWeek.getDayOfMonth() + ")";
    }
    

    public static String getMonth(String str) {
        Date date = parseStr2Date(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return year + "-" + String.format("%02d", month);
    }

    public static String getYear(String str) {

        Date date = parseStr2Date(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year + "";
    }

    public static String getSeason(String str) {
        Date date = parseStr2Date(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                return "第 1 季度";
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                return "第 2 季度";
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                return "第 3 季度";
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                return "第 4 季度";
            default:
                break;
        }
        return "空";
    }


    public static int longCalcDaysFloor(long time) {
        return new Double(Math.floor(Double.parseDouble("" + time) / Double.parseDouble("" + (60 * 1000 * 60 * 24))))
                .intValue();
    }

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            return d;
        }
        return d;
    }

    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    public static String getWeekStr(int year,int week){
        WeekFields wfs = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate inputDate;
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            inputDate = LocalDate.ofYearDay(1996, week * 7);
        } else {
            inputDate = LocalDate.ofYearDay(2001, week * 7);
        }
        int num = inputDate.get(wfs.weekOfWeekBasedYear());
        LocalDate beginDayOfWeek = inputDate.with(DayOfWeek.MONDAY);
        // 所在周结束时间
        LocalDate endDayOfWeek;
        int monthValue, dayOfMonth;
        if (week == 52) {
            monthValue = 12;
            dayOfMonth = 31;
        } else {
            endDayOfWeek = inputDate.with(DayOfWeek.SUNDAY);
            monthValue = endDayOfWeek.getMonthValue();
            dayOfMonth = endDayOfWeek.getDayOfMonth();
        }
        return year + "年" + num + "周(" + beginDayOfWeek.getMonthValue() + "." + beginDayOfWeek.getDayOfMonth() + "-" + monthValue + "." + dayOfMonth + ")";
    }
}
