package cn.krisez.sign.persenter.sign_presenter;

import android.util.Log;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.ui.sign_ui.ICreateView;

/**
 * Created by Krisez on 2018-02-11.
 */

public class CreatePresenter implements ICreatePresenter,SignListener {
    private ICreateView mView;
    private ISignModel mModel;

    public CreatePresenter(ICreateView view) {
        mView = view;
        mModel = new SignModel();
    }

    @Override
    public void createClass() {
        mModel.create(mView.getCourseName(),mView.getTeacher(),mView.getClassCode(),this);
    }

    @Override
    public void success(List<Course> list, String _id) {
        mView.cancelProgress();
        mView.js(_id);
    }

    @Override
    public void failed(String s) {
        mView.cancelProgress();
        mView.error(s);
    }
}
