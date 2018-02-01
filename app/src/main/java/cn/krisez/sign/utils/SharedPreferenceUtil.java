package cn.krisez.sign.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.krisez.sign.App;
import cn.krisez.sign.bean.User;

/**
 * Created by Krisez on 2017/10/22.
 */

public class SharedPreferenceUtil {

    public static void setTable(String xh, String kebiao){
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("kebiao", Context.MODE_PRIVATE).edit();
        editor.putString("xh",xh);
        editor.putString("table",kebiao);
        editor.apply();
    }

    public static String getTable(){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("kebiao",Context.MODE_PRIVATE);
        return sharedPreferences.getString("table","");
    }
    public static String getXh(){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("kebiao",Context.MODE_PRIVATE);
        return sharedPreferences.getString("xh","");
    }

    public static void setUser(User user) {
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("person",Context.MODE_PRIVATE).edit();
        editor.putString("username",user.getUsername());
        editor.putString("head",user.getHeadImg());
        editor.apply();
    }

    public static User getUser(){
        User u = new User();
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("person",Context.MODE_PRIVATE);
        u.setUsername(sharedPreferences.getString("username",""));
        u.setHeadImg(sharedPreferences.getString("head",""));
        return u;
    }
}
