package cn.krisez.sign.persenter.login_presenter;

import cn.krisez.sign.bean.Students;
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
    public void login() {
        mModel.getUser(mView.getXh(),mView.getMM(),this);
    }

    @Override
    public void signup(Students students) {
        mModel.register(mView.getXh(),mView.getMM(),students,this);
    }

    @Override
    public void success() {
        mView.dismissProgress();
        mView.right();
    }

    @Override
    public void failed(String s) {
        mView.dismissProgress();
        mView.showError(s);
    }
}
