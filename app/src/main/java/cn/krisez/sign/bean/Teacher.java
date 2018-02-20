package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-02-20.
 */

public class Teacher extends BmobObject{
    private String gh;//工号
    private String name;//姓名
    private String xy;//学院
    private String zy;//专业
    private String sex;

    public Teacher() {
    }

    public Teacher(String gh, String name, String xy, String zy, String sex) {
        this.gh = gh;
        this.name = name;
        this.xy = xy;
        this.zy = zy;
        this.sex = sex;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
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
