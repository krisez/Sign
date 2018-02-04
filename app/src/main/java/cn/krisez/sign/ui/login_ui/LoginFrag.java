package cn.krisez.sign.ui.login_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.krisez.sign.App;
import cn.krisez.sign.R;
import cn.krisez.sign.persenter.login_presenter.ILoginPresenter;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;

/**
 * Created by Krisez on 2018-02-03.
 */

public class LoginFrag extends Fragment implements ILoginView{
    private AutoCompleteTextView mUser;
    private EditText mPassword;
    private Button mSignIn;

    private ILoginPresenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_log,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new LoginPresenter(this);

        mUser = view.findViewById(R.id.login_account);
        mPassword = view.findViewById(R.id.login_password);
        mSignIn = view.findViewById(R.id.login_in);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login();
                showProgress();
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
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((LoginActivity)getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((LoginActivity)getActivity()).dismissProgress();
    }

    @Override
    public void right(){
        ((LoginActivity)getActivity()).success(getXh());
    }
}
