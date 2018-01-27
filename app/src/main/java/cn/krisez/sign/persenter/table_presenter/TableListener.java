package cn.krisez.sign.persenter.table_presenter;

import java.util.List;

import cn.krisez.sign.bean.KeBiao;

/**
 * Created by Krisez on 2018-01-27.
 */

public interface TableListener {
    void success(List<KeBiao> keBiaos);
    void failed(String s);
}
