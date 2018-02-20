package cn.krisez.sign.persenter.sign_presenter;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.persenter.sign_presenter.listener.SignListener;
import cn.krisez.sign.ui.sign_ui.IJoinView;

/**
 * Created by Krisez on 2018-02-11.
 */

public class JoinPresenter implements IJoinPresenter, SignListener {

    private IJoinView mView;
    private ISignModel mModel;

    public JoinPresenter(IJoinView view) {
        mView = view;
        mModel = new SignModel();
    }

    @Override
    public void search() {
        mModel.search(mView.getCourseId(), this);
        mView.showPro();
    }

    @Override
    public void join() {
        mModel.join(mView.getCourseId(), this);
    }

    @Override
    public void success(List<Course> list, String _id) {
        if (_id.isEmpty()) {
            mView.showList(list);
            mView.cancelProgress();
        } else
            mView.js(_id);
    }

    @Override
    public void failed(String s) {
        mView.cancelProgress();
        mView.error(s);
    }
}
