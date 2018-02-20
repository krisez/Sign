package cn.krisez.sign.persenter.sign_presenter;

import java.util.List;

import cn.krisez.sign.bean.ShowStuState;
import cn.krisez.sign.model.sign_model.ISignModel;
import cn.krisez.sign.model.sign_model.SignModel;
import cn.krisez.sign.persenter.sign_presenter.listener.ShowListener;
import cn.krisez.sign.ui.sign_ui.IShowStuView;

/**
 * Created by Krisez on 2018-02-19.
 */

public class ShowPresenter implements IShowPresenter,ShowListener {
    private ISignModel mModel;
    private IShowStuView mView;

    public ShowPresenter(IShowStuView view) {
        mView = view;
        mModel = new SignModel();
    }

    @Override
    public void getList() {
        mModel.show(mView.getSignId(),this);
    }

    @Override
    public void changeState(int position) {
        mModel.changePosState(mView.getStuId(position),position,this);
    }

    @Override
    public void success(List<ShowStuState> stuStates) {
        mView.showStuState(stuStates);
    }

    @Override
    public void change(int pos) {
        mView.changeState(pos);
        mView.cancelPro();
    }

    @Override
    public void failed(String s) {
        mView.error(s);
    }
}
