package cn.krisez.sign.persenter.sign_presenter;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.persenter.sign_presenter.listener.SignListener;
import cn.krisez.sign.ui.sign_ui.IDoingView;

/**
 * Created by Krisez on 2018-02-20.
 * 学生签到进行中
 */

public class DoingPresenter implements IDoingPresenter,SignListener{

    private ISignModel mModel;
    private IDoingView mView;

    public DoingPresenter(IDoingView view) {
        mView = view;
        mModel = new SignModel();
    }

    @Override
    public void sign() {
        mModel.sign(mView.getYzm(),mView.getCourseId(),this);
    }

    @Override
    public void success(List<Course> list, String _id) {
        mView.js();
    }

    @Override
    public void failed(String s) {
mView.error(s);
    }


}
