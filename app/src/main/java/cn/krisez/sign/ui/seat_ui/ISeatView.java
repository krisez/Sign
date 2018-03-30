package cn.krisez.sign.ui.seat_ui;

import java.util.List;

import cn.krisez.sign.bean.Seat.Seats;

/**
 * Created by Krisez on 2018-01-25.
 */

public interface ISeatView {
    void showProgress();
    void dismissProgress();
    void updateList(List<Seats> seats);
    String getTeacher();
    String getClassRoom();
}
