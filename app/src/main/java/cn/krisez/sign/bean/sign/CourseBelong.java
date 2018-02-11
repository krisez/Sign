package cn.krisez.sign.bean.sign;

import cn.bmob.v3.BmobObject;
import cn.krisez.sign.bean.User;

/**
 * Created by Krisez on 2018-02-11.
 */

public class CourseBelong extends BmobObject {
    private Course mCourse;
    private User mUser;

    public CourseBelong() {
    }

    public CourseBelong(Course course, User user) {
        mCourse = course;
        mUser = user;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
