package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-01-25.
 * 上课教室类型，时间，老师
 */

public class Class extends BmobObject{
    private String number;//example 3301
    private ClassType type;//get type of class,(a,b,c,d,e,f)
    private String date;//example Monday 3.4
    private String teacher;//teaching person
}
