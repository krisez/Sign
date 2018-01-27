package cn.krisez.sign.persenter;

import java.util.List;

import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.model.IMainModel;
import cn.krisez.sign.model.MainModel;
import cn.krisez.sign.ui.seat_ui.ISeatView;

/**
 * Created by Krisez on 2018-01-25.
 */

public class ClassPresenter implements ClassPresenterImp,ClassListener {
    private ISeatView iview;
    private IMainModel mIMainModel;

    public ClassPresenter(ISeatView view) {
        iview = view;
        mIMainModel = new MainModel();
    }

    @Override
    public void update() {

    }

    @Override
    public void reset() {
        iview.showProgress();
        mIMainModel.reset(this);
    }

    @Override
    public void success(List<Seats> seats) {
        iview.dismissProgress();
        iview.updateList(seats);
    }

    @Override
    public void failed() {
        iview.dismissProgress();
    }


}
