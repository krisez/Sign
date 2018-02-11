package cn.krisez.sign.ui.sign_ui;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface ICreateView {
    String getCourseName();
    String getTeacher();
    String getClassCode();
    void js(String _id);
    void cancelProgress();
    void error(String s);
}
