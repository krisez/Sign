package cn.krisez.sign.persenter.sign_presenter.listener;

import java.util.List;

import cn.krisez.sign.bean.ShowStuState;

/**
 * Created by Krisez on 2018-02-19.
 */

public interface ShowListener {
    void success(List<ShowStuState> stuStates);
    void change(int pos);
    void failed(String s);
}
