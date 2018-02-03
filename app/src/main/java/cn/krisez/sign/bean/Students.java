package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-01-25.
 * 学生信息
 */

public class Students extends BmobObject{
    private String xh;//学号
    private String name;//姓名
    private String xy;//学院
    private String zy;//专业
    private String sex;

    public Students() {
    }

    public Students(String xh, String name, String xy, String zy, String sex) {
        this.xh = xh;
        this.name = name;
        this.xy = xy;
        this.zy = zy;
        this.sex = sex;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXy() {
        return xy;
    }

    public void setXy(String xy) {
        this.xy = xy;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
