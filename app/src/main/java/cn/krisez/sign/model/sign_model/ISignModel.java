package cn.krisez.sign.model.sign_model;

import cn.krisez.sign.persenter.sign_presenter.SignListener;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface ISignModel {
    void getCourse(SignListener listener);
    void create(String courseName, String teacher, String classCode, SignListener listener);
    void join(String _id,SignListener listener);
    void start(SignListener listener);//老师开始
    void end(SignListener listener);//老师结束
    void sign(SignListener listener);//同学签到

}
