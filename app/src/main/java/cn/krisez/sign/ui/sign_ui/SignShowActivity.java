package cn.krisez.sign.ui.sign_ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.adapter.OnItemClickListener;
import cn.krisez.sign.adapter.ShowAdapter;
import cn.krisez.sign.adapter.SignAdapter;
import cn.krisez.sign.base.BaseActivity;
import cn.krisez.sign.bean.ShowStuState;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.persenter.sign_presenter.IShowPresenter;
import cn.krisez.sign.persenter.sign_presenter.ShowPresenter;
import cn.krisez.sign.widget.DividerDecoration;

/**
 * Created by Krisez on 2018-02-11.
 * 向老师展示签到情况
 */

public class SignShowActivity extends BaseActivity implements IShowStuView {

    private ShowAdapter mAdapter;
    private List<ShowStuState> mStudentsList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View setView() {
        return View.inflate(this, R.layout.activity_sign_show, null);
    }

    @Override
    protected void initSon() {
        mSwipeRefreshLayout = findViewById(R.id.sign_show_swipe);
        final IShowPresenter presenter = new ShowPresenter(this);
        mAdapter = new ShowAdapter(this, mStudentsList);
        RecyclerView recyclerView = findViewById(R.id.sign_show_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);
        presenter.getList();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.changeState(position);
                showProgress();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(SignShowActivity.this, "刷新中~~~~", Toast.LENGTH_SHORT).show();
                mStudentsList.removeAll(mStudentsList);
                mAdapter.notifyDataSetChanged();
                presenter.getList();
            }
        });

    }

    @Override
    protected String setbTitle() {
        return "列表";
    }

    @Override
    public String getSignId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public String getStuId(int pos) {
        return mStudentsList.get(pos).getObjectId();
    }

    @Override
    public void showStuState(List<ShowStuState> showStuStateList) {
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mStudentsList.removeAll(mStudentsList);
        mStudentsList.addAll(showStuStateList);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void changeState(int pos) {
        mStudentsList.get(pos).setState(true);
        mAdapter.notifyItemChanged(pos);
    }

    @Override
    public void error(String s) {
        showError(s);
    }

    @Override
    public void cancelPro() {
        missProgress();
    }
}
