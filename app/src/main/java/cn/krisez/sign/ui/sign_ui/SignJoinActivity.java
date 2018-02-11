package cn.krisez.sign.ui.sign_ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.adapter.OnItemClickListener;
import cn.krisez.sign.adapter.SignAdapter;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.persenter.sign_presenter.IJoinPresenter;
import cn.krisez.sign.persenter.sign_presenter.JoinPresenter;
import cn.krisez.sign.widget.DividerDecoration;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

/**
 * Created by Krisez on 2018-02-11.
 * 学生加入相应课程
 */

public class SignJoinActivity extends BaseActivity implements IJoinView {

    private EditText search;
    private RecyclerView mRecyclerView;
    private SignAdapter mAdapter;
    private List<Course> mCourses = new ArrayList<>();
    private IJoinPresenter mPresenter;


    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_join, null);
    }

    @Override
    protected void initSon() {
        mPresenter = new JoinPresenter(this);
        search = findViewById(R.id.sign_join_search);
        Button button = findViewById(R.id.ic_search_btn);
        mAdapter = new SignAdapter(this, mCourses);
        mRecyclerView = findViewById(R.id.sign_join_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerDecoration(this,DividerDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == IME_ACTION_SEARCH) {
                    mPresenter.search();
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.search();
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                new AlertDialog.Builder(SignJoinActivity.this)
                        .setTitle("TIPS")
                        .setMessage("加入" + mCourses.get(position).getCourseName())
                        .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.join();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Override
    protected String setbTitle() {
        return null;
    }

    @Override
    public String getCourseId() {
        return search.getText().toString();
    }

    @Override
    public void showList(List<Course> courseList) {
        mCourses.addAll(courseList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void cancelProgress() {
        missProgress();
    }

    @Override
    public void error(String s) {
        showError(s);
    }

    @Override
    public void showPro() {
        showProgress();
    }

    @Override
    public void js(String _id){
        Intent intent = new Intent();
        Course course = mCourses.get(0);
        intent.putExtra("course",course);
        setResult(RESULT_OK,intent);
        missProgress();
        finish();
    }
}
