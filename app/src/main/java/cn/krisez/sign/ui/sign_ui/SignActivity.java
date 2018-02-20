package cn.krisez.sign.ui.sign_ui;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.App;
import cn.krisez.sign.R;
import cn.krisez.sign.adapter.OnItemClickListener;
import cn.krisez.sign.adapter.SignAdapter;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.bean.sign.ClassSign;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.bean.sign.CourseBelong;
import cn.krisez.sign.persenter.sign_presenter.ISignPresenter;
import cn.krisez.sign.persenter.sign_presenter.SignPresenter;
import cn.krisez.sign.widget.DividerDecoration;

/**
 * Created by Krisez on 2018-02-10.
 */

public class SignActivity extends BaseActivity implements ISignView {

    private RecyclerView mRecyclerView;
    private List<Course> mCourseList = new ArrayList<>();
    private SignAdapter mAdapter;

    private ISignPresenter mPresenter;

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign, null);
    }

    @Override
    protected void initSon() {
        mPresenter = new SignPresenter(this);

        mAdapter = new SignAdapter(this, mCourseList);
        mRecyclerView = findViewById(R.id.sign_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getCourses();
        findViewById(R.id.sign_create_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if (App.getUser().getType().equals("1")) {
                    intent = new Intent(SignActivity.this, SignJoinActivity.class);
                    startActivityForResult(intent, 1);
                } else if (App.getUser().getType().equals("2")) {
                    intent = new Intent(SignActivity.this, SignCreateActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = null;
                if (App.getUser().getType().equals("2")) {
                    intent= new Intent(SignActivity.this, SignStartActivity.class);
                } else {
                    intent= new Intent(SignActivity.this, SignDoingActivity.class);
                } intent.putExtra("course_id", mCourseList.get(position).getObjectId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Course course = (Course) data.getSerializableExtra("course");
                mCourseList.add(0, course);
                mAdapter.notifyItemInserted(0);
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Course course = (Course) data.getSerializableExtra("course");
                mCourseList.add(0, course);
                mAdapter.notifyItemInserted(0);
            }
        }
    }

    @Override
    protected String setbTitle() {
        return "课程";
    }

    @Override
    public void showList(List<Course> courses) {
        mCourseList.addAll(courses);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
