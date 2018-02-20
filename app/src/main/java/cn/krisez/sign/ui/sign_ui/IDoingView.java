package cn.krisez.sign.ui.sign_ui;

/**
 * Created by Krisez on 2018-02-20.
 * 学生签到进行中
 */

public interface IDoingView {
    String getYzm();
    String getCourseId();
    void js();
    void cancelPro();
    void error(String s);
}
