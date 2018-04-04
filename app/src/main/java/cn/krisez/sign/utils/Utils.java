package cn.krisez.sign.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by Krisez on 2018-01-27.
 */

public class Utils {
    public static String RandomColor(int pos) {
        String color = null;
        switch (pos % 3) {
            case 0:
                color = "#31FD69";
                break;
            case 1:
                color = "#B0E2FD";
                break;
            case 2:
                color = "#FEC7E4";
                break;
        }
        return color;
    }

    public static int px2dip(Context context, Float pxVaule) {
        Float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVaule / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 对String类型的数据进行修改的新的数据
     */
    public static String newString(String Change) {
        char a[] = Change.toCharArray();
        String result = " ";
        int j = 0;
        while (a[j] != '-') {
            j++;
        }
        for (int i = ++j; i < Change.length(); i++) {
            if (a[i] == ' ')
                result += '\n';
            result += a[i];
        }
        return result;
    }

    /**
     * 得到课程的代码  类似于A0000000000
     */
    public static String lessonCode(String all_content) {
        char a[] = all_content.toCharArray();
        String result = "";
        int j = 0;
        while (a[j] != ' ') {
            j++;
        }
        for (int i = 0; i < j; i++) {
            result += a[i];
        }
        return result;
    }

    /**
     * 得到name 和 addr
     */
    public static String getLessonName(String s) {
        int i = 0;
        int name1 = 0;//  -
        int addr = 0;
        while (true) {
            if (s.charAt(i) == '-') {
                name1 = i;
            }
            if (s.charAt(i) == '地') {
                addr = i;
                break;
            }
            i++;
        }
        return s.substring(name1+1, addr-1);
    }

    /**
     * 得到name 和 addr
     */
    public static String getLessonAddr(String s) {
        int i = 0;
        int mao = 0;//   :
        while (true) {
            if (s.charAt(i) == '：') {
                mao = i;
            }
            if (mao > 0) {
                break;
            }
            i++;
        }
        String addr = s.substring(mao+1, mao+5);
        if(addr.charAt(0)<'0'||addr.charAt(0)>'9'){
            if(addr.charAt(0)=='待'){
                addr = "待定";
            }else
            addr = "实验";
        }
        return addr;
    }


    public static String getLessonTeacher(String s) {
        String[] ss = s.split("\n");
        String a = ss[3];
        return a.substring(a.lastIndexOf(' ')+1,a.length());
    }
}
