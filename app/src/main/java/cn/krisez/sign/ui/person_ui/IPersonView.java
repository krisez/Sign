package cn.krisez.sign.ui.person_ui;

import cn.krisez.sign.bean.Students;

/**
 * Created by Krisez on 2018-02-03.
 */

public interface IPersonView {
    void setInfo(Students students);
    void showPro();
    void dismiss();
}
