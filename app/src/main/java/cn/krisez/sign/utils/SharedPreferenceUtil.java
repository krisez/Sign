package cn.krisez.sign.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.krisez.sign.App;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;
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

    public static void saveStudent(Students students) {
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("student",Context.MODE_PRIVATE).edit();
        editor.putString("_id",students.getObjectId());
        editor.putString("name",students.getName());
        editor.putString("sex",students.getSex());
        editor.putString("xh",students.getXh());
        editor.putString("xy",students.getXy());
        editor.putString("zy",students.getZy());
        editor.apply();
    }

    public static Students getStudent(){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("student",Context.MODE_PRIVATE);
        Students students = new Students(sharedPreferences.getString("xh",""),
                sharedPreferences.getString("name",""),
                sharedPreferences.getString("xy",""),
                sharedPreferences.getString("zy",""),
                sharedPreferences.getString("sex",""));
        students.setObjectId(sharedPreferences.getString("_id",""));
        return students;
    }

    public static void saveTeacher(Teacher teacher) {
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("teacher",Context.MODE_PRIVATE).edit();
        editor.putString("_id",teacher.getObjectId());
        editor.putString("name",teacher.getName());
        editor.putString("sex",teacher.getSex());
        editor.putString("xh",teacher.getGh());
        editor.putString("xy",teacher.getXy());
        editor.putString("zy",teacher.getZy());
        editor.apply();
    }

    public static Teacher getTeacher(){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("teacher",Context.MODE_PRIVATE);
        Teacher teacher = new Teacher(sharedPreferences.getString("xh",""),
                sharedPreferences.getString("name",""),
                sharedPreferences.getString("xy",""),
                sharedPreferences.getString("zy",""),
                sharedPreferences.getString("sex",""));
        teacher.setObjectId(sharedPreferences.getString("_id",""));
        return teacher;
    }
}
