package cn.krisez.sign.persenter.login_presenter;

import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface ILoginPresenter {
    void login();
    void signup(Students students);
    void signT(Teacher teacher);
    void checkTeacher(String s);
}
