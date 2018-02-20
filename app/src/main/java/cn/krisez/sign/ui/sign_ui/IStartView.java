package cn.krisez.sign.ui.sign_ui;

/**
 * Created by Krisez on 2018-02-18.
 */

public interface IStartView {
    String getCourseId();
    void setRandom(String random);
    void showTips();
    void showPro();
    void js(String _id);
    void cancelProgress();
    void error(String s);
    void setOjbk(String id);
}
