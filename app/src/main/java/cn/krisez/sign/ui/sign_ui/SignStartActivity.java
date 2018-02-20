package cn.krisez.sign.ui.sign_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.persenter.sign_presenter.IStartPresenter;
import cn.krisez.sign.persenter.sign_presenter.StartPresenter;

/**
 * Created by Krisez on 2018-02-11.
 * 老师开始点名
 */

public class SignStartActivity extends BaseActivity implements IStartView {

    private TextView code;
    private TextView tip;
    private IStartPresenter mPresenter;
    private static String ojbk = "";

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_start, null);
    }

    @Override
    protected void initSon() {
        mPresenter = new StartPresenter(this);
        Button button = findViewById(R.id.sign_start_btn);
        code = findViewById(R.id.sign_start_code);
        tip = findViewById(R.id.sign_start_show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPro();
                mPresenter.startSign();
            }
        });

        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignStartActivity.this, SignShowActivity.class);
                intent.putExtra("id",ojbk);
                startActivity(intent);
            }
        });
    }

    @Override
    protected String setbTitle() {
        return "点名";
    }

    @Override
    public String getCourseId() {
        return getIntent().getStringExtra("course_id");
    }

    @Override
    public void setRandom(String random) {
        code.setText(random);
    }

    @Override
    public void showTips() {
        tip.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPro() {
        showProgress();
    }

    @Override
    public void js(String _id) {
        finish();
    }

    @Override
    public void cancelProgress() {
        missProgress();
    }

    @Override
    public void error(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOjbk(String id) {
        ojbk = id;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
    }
}