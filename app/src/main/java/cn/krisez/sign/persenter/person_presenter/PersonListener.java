package cn.krisez.sign.persenter.person_presenter;

import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface PersonListener {
    void success(Students students);
    void success(Teacher teacher);
    void failed(String s);
}
