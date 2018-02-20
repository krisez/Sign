package cn.krisez.sign;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;
import cn.krisez.sign.bean.sign.Course;

/**
 * Created by Krisez on 2018-02-18.
 * 课程，验证码，学生状况以及是否可签到
 */

public class SignStart extends BmobObject {
    private Course mCourse;
    private String yzm;
    private BmobRelation stuState;
    private boolean sign;

    public SignStart() {
    }

    public SignStart(Course course, String yzm, BmobRelation stuState, boolean sign) {
        mCourse = course;
        this.yzm = yzm;
        this.stuState = stuState;
        this.sign = sign;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    public BmobRelation getStuState() {
        return stuState;
    }

    public void setStuState(BmobRelation stuState) {
        this.stuState = stuState;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
