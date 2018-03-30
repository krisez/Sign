package cn.krisez.sign.model.seat_model;

import cn.krisez.sign.persenter.class_presenter.ClassListener;

/**
 * Created by Krisez on 2018-01-25.
 */

public interface ISeatModel {
    void updateSeat(int pos,ClassListener listener);
    void reset(String teacher,String lesson,ClassListener listener);
}
