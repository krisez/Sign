package cn.krisez.sign.model;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.krisez.sign.bean.ClassSeats;
import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.persenter.class_presenter.ClassListener;

/**
 * Created by Krisez on 2018-01-25.
 */

public class SeatModel implements ISeatModel {
    @Override
    public void updateSeat(ClassListener listener) {

    }

    @Override
    public void reset(final ClassListener listener) {
        final List<Seats> seats = new ArrayList<>();
       BmobQuery<ClassSeats> query = new BmobQuery<>();
        query.findObjects(new FindListener<ClassSeats>() {
            @Override
            public void done(List<ClassSeats> list, BmobException e) {
                if(e==null){
                    seats.addAll(list.get(0).getSeats());
                    listener.success(seats);
                }else listener.failed();
            }
        });
    }
}

