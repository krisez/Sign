package cn.krisez.sign.ui.person_ui;

import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;

/**
 * Created by Krisez on 2018-02-03.
 */

public interface IPersonView {
    void setInfo(Students students);
    void showPro();
    void dismiss();
    void setInfo(Teacher teacher);
}
