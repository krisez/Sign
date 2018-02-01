package cn.krisez.sign.model.table_model;

import java.util.List;

import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.persenter.table_presenter.TableListener;

/**
 * Created by Krisez on 2018-01-27.
 */

public interface ITableModel {
    void getTableData(String xh, TableListener listener);
    List<KeBiao> dealTable(String tableData);
}
