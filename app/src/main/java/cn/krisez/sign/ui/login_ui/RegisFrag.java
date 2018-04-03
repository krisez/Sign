package cn.krisez.sign.ui.login_ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.krisez.sign.App;
import cn.krisez.sign.R;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.Teacher;
import cn.krisez.sign.persenter.login_presenter.ILoginPresenter;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;

/**
 * Created by Krisez on 2018-02-03.
 */

public class RegisFrag extends Fragment implements ILoginView {
    private AutoCompleteTextView mUser;
    private EditText mPassword;
    private EditText mName;
    private EditText mXy;
    private EditText mZy;
    private RadioGroup mRadioGroup;
    private CheckBox mCheckBox;


    private ILoginPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_reg, container, false);
        return v;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new LoginPresenter(this);

        mUser = view.findViewById(R.id.register_user);
        mPassword = view.findViewById(R.id.register_password);
        mName = view.findViewById(R.id.register_name);
        mXy = view.findViewById(R.id.register_xy);
        mZy = view.findViewById(R.id.register_zy);
        mRadioGroup = view.findViewById(R.id.register_sex);
        mCheckBox = view.findViewById(R.id.register_rule_teacher);
        Button signUp = view.findViewById(R.id.login_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getXh().isEmpty() || getMM().isEmpty() || mName.getText().toString().isEmpty()
                        || mXy.getText().toString().isEmpty() || mZy.getText().toString().isEmpty()
                        ) {
                    Toast.makeText(getActivity(), "请填写完整！", Toast.LENGTH_SHORT).show();
                } else {
                    if (getXh().length() != 10 && !mCheckBox.isChecked()) {
                        Toast.makeText(getActivity(), "学号貌似有点问题~", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(getXh().length() != 6 && mCheckBox.isChecked()){
                        Toast.makeText(getActivity(), "工号貌似有点问题~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RadioButton button = view.findViewById(mRadioGroup.getCheckedRadioButtonId());
                    if (!mCheckBox.isChecked()) {
                        Students students = new Students();
                        students.setName(mName.getText().toString());
                        students.setXh(getXh());
                        students.setSex(button.getText().toString());
                        students.setXy(mXy.getText().toString());
                        students.setZy(mZy.getText().toString());
                        mPresenter.signup(students);
                        showProgress();
                    } else {
                        Teacher teacher = new Teacher();
                        teacher.setName(mName.getText().toString());
                        teacher.setGh(getXh());
                        teacher.setSex(button.getText().toString());
                        teacher.setXy(mXy.getText().toString());
                        teacher.setZy(mZy.getText().toString());
                        mPresenter.signT(teacher);
                        showProgress();
                    }
                }
            }
        });
        if (!mCheckBox.isChecked())
            mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final EditText editText = new EditText(getActivity());
                    new AlertDialog.Builder(getActivity())
                            .setTitle("邀请码")
                            .setView(editText)
                            .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    showProgress();
                                    mPresenter.checkTeacher(editText.getText().toString());
                                }
                            })
                            .show()
                            .setCancelable(false);
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
        if (mCheckBox.isChecked()) {
            mCheckBox.setChecked(false);
        }
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((LoginActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((LoginActivity) getActivity()).dismissProgress();
    }

    @Override
    public void right() {
        ((LoginActivity) getActivity()).success(getXh());
    }
}
