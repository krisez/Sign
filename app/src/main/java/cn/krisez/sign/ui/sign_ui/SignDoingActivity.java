package cn.krisez.sign.ui.sign_ui;

import android.view.View;
import android.widget.EditText;

import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.persenter.sign_presenter.DoingPresenter;
import cn.krisez.sign.persenter.sign_presenter.IDoingPresenter;

/**
 * Created by Krisez on 2018-02-20.
 */

public class SignDoingActivity extends BaseActivity implements IDoingView {

    private IDoingPresenter mPresenter;
    private EditText mEditText;

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_doing,null);
    }

    @Override
    protected void initSon() {
        mPresenter = new DoingPresenter(this);
        mEditText = findViewById(R.id.sign_doing_yzm);
        findViewById(R.id.sign_doing_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sign();
                showProgress();
            }
        });
    }

    @Override
    protected String setbTitle() {
        return "签到";
    }

    @Override
    public String getYzm() {
        return mEditText.getText().toString();
    }

    @Override
    public String getCourseId() {
        return getIntent().getStringExtra("course_id");
    }

    @Override
    public void js() {
        finish();
        cancelPro();
    }

    @Override
    public void cancelPro() {
        missProgress();
    }

    @Override
    public void error(String s) {
        mEditText.setText("");
        showError(s);
        cancelPro();
    }
}
