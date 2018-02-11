package cn.krisez.sign.ui.person_ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;
import cn.krisez.sign.App;
import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.persenter.person_presenter.IPersonPresenter;
import cn.krisez.sign.persenter.person_presenter.PersonPresenter;
import cn.krisez.sign.utils.SharedPreferenceUtil;

/**
 * Created by Krisez on 2018-02-01.
 */

public class PersonActivity extends BaseActivity implements IPersonView{
    public static final int RESULT_LOGOUT = 201;

    private TextView mName;
    private TextView mXh;
    private TextView mSex;
    private TextView mXy;
    private TextView mZy;

    private IPersonPresenter mPresenter;

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_person, null);
    }

    @Override
    protected void initSon() {
        mPresenter = new PersonPresenter(this);

        mName = findViewById(R.id.person_name);
        mXh = findViewById(R.id.person_xh);
        mSex = findViewById(R.id.person_sex);
        mXy = findViewById(R.id.person_xy);
        mZy = findViewById(R.id.person_zy);
        CardView logout = findViewById(R.id.person_logout);

        mPresenter.localStudent();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();
                SharedPreferenceUtil.saveStudent(new Students());
                SharedPreferenceUtil.setTable("","");
                showPro();
                setResult(RESULT_LOGOUT);
                dismiss();
                App.setUser(null);
                finish();
            }
        });
    }

    @Override
    protected String setbTitle() {
        return "个人中心";
    }

    @Override
    public void setInfo(Students students) {
        mName.setText(students.getName());
        mSex.setText(students.getSex());
        mZy.setText(students.getZy());
        mXy.setText(students.getXy());
        mXh.setText(students.getXh());
    }

    @Override
    public void showPro() {
        showProgress();
    }

    @Override
    public void dismiss() {
        missProgress();
    }
}
