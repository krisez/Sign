package cn.krisez.sign.model.sign_model;

import cn.krisez.sign.persenter.sign_presenter.ShowPresenter;
import cn.krisez.sign.persenter.sign_presenter.listener.ShowListener;
import cn.krisez.sign.persenter.sign_presenter.listener.SignListener;
import cn.krisez.sign.persenter.sign_presenter.listener.StartListener;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface ISignModel {
    void getCourse(SignListener listener);
    void create(String courseName, String teacher, String classCode, SignListener listener);
    void search(String _id,SignListener listener);
    void join(String _id,SignListener listener);
    void start(String _id,StartListener listener);//老师开始
    void show(String _id,ShowListener listener);//展示学生列表
    void changePosState(String stuId, int pos,ShowListener listener);
    void end(SignListener listener);//老师结束
    void sign(String yzm,String course,SignListener listener);//同学签到
}
