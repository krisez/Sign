package cn.krisez.sign.ui.sign_ui;

import java.util.List;
import cn.krisez.sign.bean.sign.Course;

/**
 * Created by Krisez on 2018-02-10.
 */

public interface ISignView {
    void showList(List<Course> signs);
    void showError(String s);
}
