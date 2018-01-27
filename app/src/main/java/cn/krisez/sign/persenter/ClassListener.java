package cn.krisez.sign.persenter;

import java.util.List;

import cn.krisez.sign.bean.Seat.Seats;

/**
 * Created by Krisez on 2018-01-25.
 */

public interface ClassListener {
    void success(List<Seats> seats);
    void failed();
}
