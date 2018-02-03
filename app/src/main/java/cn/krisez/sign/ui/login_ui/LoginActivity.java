package cn.krisez.sign.ui.login_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.krisez.sign.R;
import cn.krisez.sign.persenter.login_presenter.ILoginPresenter;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;

/**
 * Created by Krisez on 2018-01-27.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView{

    private AutoCompleteTextView mUser;
    private AutoCompleteTextView mPassword;
    private Button mSignUp;
    private Button mSignIn;
    private ProgressBar mProgressBar;

    private ILoginPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);

        mUser = findViewById(R.id.login_account);
        mPassword = findViewById(R.id.login_password);
        mSignIn = findViewById(R.id.login_in);
        mSignUp = findViewById(R.id.login_up);
        mProgressBar = findViewById(R.id.login_pro);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login();
                showProgress();

            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "不注册", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public String getXh() {
        return mUser.getText().toString();
    }

    @Override
    public String getMM() {
        return mPassword.getText().toString();
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent();
        intent.putExtra("xh",getXh());
        setResult(RESULT_OK,intent);
        finish();
    }
}
