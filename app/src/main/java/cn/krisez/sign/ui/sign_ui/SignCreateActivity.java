package cn.krisez.sign.ui.sign_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import cn.krisez.sign.R;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.persenter.sign_presenter.CreatePresenter;
import cn.krisez.sign.persenter.sign_presenter.ICreatePresenter;
import cn.krisez.sign.utils.SharedPreferenceUtil;

/**
 * Created by Krisez on 2018-02-11.
 * 老师创建
 */

public class SignCreateActivity extends BaseActivity implements ICreateView{

    private EditText course,classcode;
    private TextView teacher;
    private ICreatePresenter mPresenter;

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_create,null);
    }

    @Override
    protected void initSon() {
        mPresenter = new CreatePresenter(this);
        course = findViewById(R.id.sign_create_course_name);
        classcode = findViewById(R.id.sign_create_class_code);
        teacher = findViewById(R.id.sign_create_teacher_name);
        teacher.setText(SharedPreferenceUtil.getTeacher().getName());
        findViewById(R.id.sign_create_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.createClass();
                showProgress();
            }
        });
    }

    @Override
    protected String setbTitle() {
        return "创建";
    }

    @Override
    public String getCourseName() {
        return course.getText().toString();
    }

    @Override
    public String getTeacher() {
        return teacher.getText().toString();
    }

    @Override
    public String getClassCode() {
        return classcode.getText().toString();
    }
    @Override
    public void cancelProgress(){
        missProgress();
    }
    @Override
    public void error(String s){
        showError(s);
    }
    @Override
    public void js(String _id){
        Intent intent = new Intent();
        Course course = new Course();
        course.setObjectId(_id);
        course.setTeacher(getTeacher());
        course.setCourseName(getCourseName());
        course.setCode(getClassCode());
        intent.putExtra("course",course);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
