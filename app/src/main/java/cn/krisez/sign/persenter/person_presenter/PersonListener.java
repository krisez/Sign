package cn.krisez.sign.persenter.person_presenter;

import cn.krisez.sign.bean.Students;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface PersonListener {
    void success(Students students);
    void failed(String s);
}
