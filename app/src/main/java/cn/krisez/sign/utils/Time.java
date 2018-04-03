package cn.krisez.sign.utils;

import java.util.Calendar;

/**
 * Created by Krisez on 2018-02-20.
 */

public class Time {
    /**
     * @return yyyy-MM--dd HH:mm:ss
     */
    public static String getTime() {
        String month1;
        String minute;
        String second;
        String hour;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int t = c.get(Calendar.SECOND);
        if (h < 10) {
            hour = "0" + h;
        } else
            hour = String.valueOf(h);
        if (month < 10) {
            month1 = "0" + month;
        } else
            month1 = String.valueOf(month);
        if (m < 10) {
            //分钟推回5min
            minute = "0" + (m - 2);
        } else
            minute = String.valueOf(m - 2);
        if (t < 10) {
            second = "0" + t;
        } else
            second = String.valueOf(t);
        return year + "-" + month1 + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    /**
     * @return yyyy-MM--dd HH:mm:ss
     */
    public static String getSeatTime() {
        String month1;
        String minute;
        String second;
        String hour;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int t = c.get(Calendar.SECOND);
        if (h < 10) {
            hour = "0" + (h - 3);
        } else
            hour = String.valueOf(h - 3);
        if (month < 10) {
            month1 = "0" + month;
        } else
            month1 = String.valueOf(month);
        if (m < 10) {
            //分钟推回5min
            minute = "0" + m;
        } else
            minute = String.valueOf(m);
        if (t < 10) {
            second = "0" + t;
        } else
            second = String.valueOf(t);
        return year + "-" + month1 + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    /**
     * Monday 3.4
     *
     * @return example Monday 3.4  -->>   2  10
     */
    public static String getDate() {
        Calendar c = Calendar.getInstance();
        int w = c.get(Calendar.DAY_OF_WEEK);
        int h = c.get(Calendar.HOUR_OF_DAY);
        return w + " " + h;
    }

    //2018.3.5   相差时间的计算
    public static long getWeeks(){
        int monthHave[] = {31,0,31,30,31,30,31,31,30,31,30,31};
        int allYearDay = 0;
        int initYear = 2018;
        int initMonth = 3;
        int initDay = 5;
        long theTime = 0;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if((year % 4 == 0 && year % 100 !=0) || year % 400 == 0){
            allYearDay = 366;
            monthHave[1] = 29;
        }
        else{
            allYearDay = 365;
            monthHave[1] = 28;
        }

        if(year == initYear){
            if(month == initMonth){
                if(day > initDay){
                    theTime = day-initDay;
                }
            }else {
                switch (month){
                    case 10:theTime = 30 - initDay + day;
                        break;
                    case 11:theTime = 30 - initDay + 31 + day;
                        break;
                    case 12:theTime = 30 - initDay + day + 31 + 30;
                        break;
                }
            }
        }
        else {
            theTime = 30 - initDay + 31 + 30 + 31;
            for(int i = 1;i < month;i++){
                theTime += monthHave[i];
            }
            theTime += day;
        }
        System.out.println(allYearDay);
        return theTime;
    }
}
