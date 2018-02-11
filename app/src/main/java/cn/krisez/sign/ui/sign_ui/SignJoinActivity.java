package cn.krisez.sign.ui.sign_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;

/**
 * Created by Krisez on 2018-02-11.
 * 学生加入相应课程
 */

public class SignJoinActivity extends BaseActivity {


    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_join,null);
    }

    @Override
    protected void initSon() {

    }

    @Override
    protected String setbTitle() {
        return null;
    }
}
