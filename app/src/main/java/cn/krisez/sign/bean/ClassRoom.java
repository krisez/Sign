package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-01-25.
 * 上课教室类型，时间，老师
 */

public class ClassRoom extends BmobObject{
    private String number;//example 3301
    private ClassSeats mClassSeats;//get mClassSeats of class,(a,b,c,d,e,f)
    private String date;//example Monday 3.4
    private String teacher;//teaching person

    public ClassRoom() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ClassSeats getClassSeats() {
        return mClassSeats;
    }

    public void setClassSeats(ClassSeats classSeats) {
        this.mClassSeats = classSeats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
