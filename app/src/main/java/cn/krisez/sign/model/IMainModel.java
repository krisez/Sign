package cn.krisez.sign.model;

import cn.krisez.sign.persenter.ClassListener;

/**
 * Created by Krisez on 2018-01-25.
 */

public interface IMainModel {
    void updateSeat(ClassListener listener);
    void reset(ClassListener listener);
}
