package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-01-25.
 * 第一次安排座位调用此类（开学），或者查看教室类型
 */

public class ClassType extends BmobObject{
    private String no; //seats number
    private String type;//type of seats(zw or gd)
    private Students belong;//belong somebody
}
