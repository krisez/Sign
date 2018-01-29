package cn.krisez.sign.persenter.login_presenter;

import cn.krisez.sign.bean.User;
import cn.krisez.sign.model.login_model.ILoginModel;
import cn.krisez.sign.model.login_model.LoginModel;
import cn.krisez.sign.ui.login_ui.ILoginView;

/**
 * Created by Krisez on 2018-01-29.
 */

public class LoginPresenter implements ILoginPresenter,LoginListener {
    private ILoginView mView;
    private ILoginModel mModel;

    public LoginPresenter(ILoginView view) {
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void getUser() {

    }

    @Override
    public void success(User user) {

    }

    @Override
    public void failed(String s) {

    }
}
