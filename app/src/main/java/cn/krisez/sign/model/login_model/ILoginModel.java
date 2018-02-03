package cn.krisez.sign.model.login_model;

import cn.krisez.sign.bean.Students;
import cn.krisez.sign.persenter.login_presenter.LoginListener;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface ILoginModel {
    void getUser(String xh, String mm, LoginListener listener);
    void register(String xh, String mm, Students students,LoginListener listener);
}
