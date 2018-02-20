package cn.krisez.sign.persenter.sign_presenter.listener;

import java.util.List;

import cn.krisez.sign.bean.sign.Course;

/**
 * Created by Krisez on 2018-02-11.
 */

public interface SignListener {
    void success(List<Course> list,String _id);
    void failed(String s);
}
