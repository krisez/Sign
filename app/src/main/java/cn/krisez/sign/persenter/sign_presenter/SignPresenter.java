package cn.krisez.sign.persenter.sign_presenter;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.persenter.sign_presenter.listener.SignListener;
import cn.krisez.sign.ui.sign_ui.ISignView;

/**
 * Created by Krisez on 2018-02-11.
 */

public class SignPresenter implements ISignPresenter,SignListener {

    private ISignView mView;
    private ISignModel mModel;

    public SignPresenter(ISignView view) {
        mView = view;
        mModel = new SignModel();
    }

    @Override
    public void getCourses() {
        mModel.getCourse(this);
    }

    @Override
    public void viewStuList() {

    }

    @Override
    public void signClass() {

    }

    @Override
    public void success(List<Course> list,String _id) {
        mView.showList(list);
    }

    @Override
    public void failed(String s) {
        mView.showError(s);
    }
}
