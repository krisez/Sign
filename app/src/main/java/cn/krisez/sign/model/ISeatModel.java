package cn.krisez.sign.model;

import cn.krisez.sign.persenter.class_presenter.ClassListener;

/**
 * Created by Krisez on 2018-01-25.
 */

public interface ISeatModel {
    void updateSeat(ClassListener listener);
    void reset(ClassListener listener);
}
