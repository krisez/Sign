package cn.krisez.sign.persenter.login_presenter;

import cn.krisez.sign.bean.User;

/**
 * Created by Krisez on 2018-01-29.
 */

public interface LoginListener {
    void success();
    void failed(String s);
}
