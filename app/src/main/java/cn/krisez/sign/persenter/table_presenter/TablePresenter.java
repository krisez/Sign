package cn.krisez.sign.persenter.table_presenter;

import java.util.List;

import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.model.table_model.ITableModel;
import cn.krisez.sign.model.table_model.TableModel;
import cn.krisez.sign.ui.main_ui.ITableView;
import cn.krisez.sign.utils.SharedPreferenceUtil;

/**
 * Created by Krisez on 2018-01-27.
 */

public class TablePresenter implements ITablePresenter,TableListener {
    private ITableView mITableView;
    private ITableModel mITableModel;

    public TablePresenter(ITableView ITableView) {
        mITableView = ITableView;
        mITableModel = new TableModel();
    }

    @Override
    public void updateTable() {
        mITableModel.getTableData(SharedPreferenceUtil.getXh(),this);
    }

    @Override
    public void getTable() {
        success(mITableModel.dealTable(SharedPreferenceUtil.getTable()));
    }

    @Override
    public void success(List<KeBiao> keBiaos) {
        mITableView.showTable(keBiaos);
    }

    @Override
    public void failed(String s) {

    }
}
