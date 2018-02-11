package cn.krisez.sign.ui.sign_ui;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface IJoinView {
    String getCourseId();//得到课程id号
    void showList(List<Course> courseList);
    void cancelProgress();
    void error(String s);
    void showPro();
    void js(String id);
}
