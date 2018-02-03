package cn.krisez.sign.ui.main_ui;

import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;

/**
 * Created by Krisez on 2018-02-01.
 */

public class PersonActivity extends BaseActivity{
    private View mView;

    @Override
    protected View setView() {
        mView = View.inflate(this, R.layout.activity_person,null);
        return mView;
    }

    @Override
    protected void initSon() {

    }

    @Override
    protected String setbTitle() {
        return "个人中心";
    }
}
