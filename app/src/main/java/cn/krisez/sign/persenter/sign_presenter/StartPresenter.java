package cn.krisez.sign.persenter.sign_presenter;

import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.persenter.sign_presenter.listener.StartListener;
import cn.krisez.sign.ui.sign_ui.IStartView;

/**
 * Created by Krisez on 2018-02-18.
 */

public class StartPresenter implements IStartPresenter,StartListener {

    private ISignModel mModel;
    private IStartView mView;

    public StartPresenter(IStartView view) {
        mView = view;
        mModel = new SignModel();


    }
    @Override
    public void startSign() {
        mModel.start(mView.getCourseId(),this);
    }

    @Override
    public void success(String yzm, String _id) {
        mView.setOjbk(_id);
        mView.setRandom(yzm);
        mView.showTips();
        mView.cancelProgress();
    }

    @Override
    public void failed(String s) {
        mView.error(s);
    }


}
