package cn.krisez.sign.persenter.class_presenter;

import java.util.List;

import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.model.seat_model.ISeatModel;
import cn.krisez.sign.model.seat_model.SeatModel;
import cn.krisez.sign.ui.seat_ui.ISeatView;

/**
 * Created by Krisez on 2018-01-25.
 */

public class ClassPresenter implements ClassPresenterImp,ClassListener {
    private ISeatView iview;
    private ISeatModel mIMainModel;

    public ClassPresenter(ISeatView view) {
        iview = view;
        mIMainModel = new SeatModel();
    }

    @Override
    public void update(int position) {
        mIMainModel.updateSeat(position,this);
    }

    @Override
    public void reset() {
        iview.showProgress();
        mIMainModel.reset(iview.getTeacher(),iview.getClassRoom(),this);
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
