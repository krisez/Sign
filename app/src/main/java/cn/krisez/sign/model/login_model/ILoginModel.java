package cn.krisez.sign.model.login_model;

import cn.krisez.sign.persenter.login_presenter.LoginListener;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface ILoginModel {
    void getUser(String xh, String mm, LoginListener listener);
}
