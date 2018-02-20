package cn.krisez.sign.ui.sign_ui;

import java.util.List;

import cn.krisez.sign.bean.ShowStuState;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface IShowStuView {
    String getSignId();
    String getStuId(int pos);
    void showStuState(List<ShowStuState> showStuStateList);
    void changeState(int pos);
    void error(String s);
    void cancelPro();
}
