package cn.krisez.sign.utils;

import java.util.Calendar;

/**
 * Created by Krisez on 2018-02-20.
 */

public class Time {
    /**
     *
     * @return yyyy-MM--dd HH:mm:ss
     */
    public static String getTime() {
        String month1 ;
        String minute ;
        String second ;
        String hour  ;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int t = c.get(Calendar.SECOND);
        if(h<10){
            hour = "0"+h;
        }
        else
            hour = String.valueOf(h);
        if(month<10) {
            month1 = "0" + month;
        }
        else
            month1 = String.valueOf(month);
        if(m<10){
            //分钟推回5min
            minute  = "0" + (m-2);
        }
        else
            minute = String.valueOf(m-2);
        if(t<10){
            second = "0" + t;
        }
        else
            second = String.valueOf(t);
        return year + "-"+ month1 + "-" + day + " " + hour + ":" + minute + ":" + second;
    }
}