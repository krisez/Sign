package cn.krisez.sign.ui.seat_ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.App;
import cn.krisez.sign.R;
import cn.krisez.sign.adapter.ClassAdapter;
import cn.krisez.sign.adapter.OnItemClickListener;
import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.persenter.class_presenter.ClassPresenter;
import cn.krisez.sign.persenter.class_presenter.ClassPresenterImp;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import cn.krisez.sign.utils.Utils;

public class SeatActivity extends AppCompatActivity implements ISeatView {


    private RecyclerView mRecyclerView;
    private ClassAdapter mClassAdapter;
    private List<Seats> mSeatsLis = new ArrayList<>();

    //demo
    private ProgressBar mProgressBar;
    private ScrollView mScrollView1;//stu
    private ScrollView mScrollView2;//tea
    private TextView mTips;

    private ClassPresenterImp mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        mPresenter = new ClassPresenter(this);
        initOperation();
    }

    //操作初始化
    private void initOperation() {
        mProgressBar = findViewById(R.id.progress_bar);
        mClassAdapter = new ClassAdapter(this, mSeatsLis);
        mRecyclerView = findViewById(R.id.class_recycler);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(13, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mClassAdapter);

        mProgressBar = findViewById(R.id.progress_bar);
        mScrollView1 = findViewById(R.id.class_scroll);
        mScrollView2 = findViewById(R.id.class_scroll_t);
        mTips = findViewById(R.id.seat_tips);
        mPresenter.reset();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollView1.scrollBy(dx, dy);
                mScrollView2.scrollBy(dx, dy);
            }
        });

        mClassAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if (App.getUser().getType().equals("1")&&mSeatsLis.get(position).getBelong()==null) {//学生
                    new AlertDialog.Builder(SeatActivity.this)
                            .setTitle("选座")
                            .setMessage("最后确认！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (!isExistStu()) {
                                        showProgress();
                                        mPresenter.update(position);
                                    } else {
                                        Toast toast = Toast.makeText(SeatActivity.this, "您已经选取了!", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }
                                }
                            })
                            .setNegativeButton("重选", null)
                            .show();
                } else {
                    new AlertDialog.Builder(SeatActivity.this)
                            .setTitle("信息")
                            .setMessage(mSeatsLis.get(position).getBelong().toString())
                            .show();
                }
               /*BmobQuery<Students> query = new BmobQuery<>();
                query.addWhereEqualTo("name","陈骏");
                query.findObjects(new FindListener<Students>() {
                    @Override
                    public void done(List<Students> list, BmobException e) {
                        for (int i=0;i<mSeatsLis.size();i++)
                            mSeatsLis.get(i).setBelong(list.get(0));
                    }
                });

                ClassSeats classSeats = new ClassSeats();
                classSeats.setSeats(mSeatsLis);
                classSeats.update("830095186e", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });*/
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private boolean isExistStu() {
        Students students = SharedPreferenceUtil.getStudent();
        Log.d("SeatActivity", "isExistStu:" + students.toString());
        int i = 0;
        while (i < mSeatsLis.size()) {
            if (mSeatsLis.get(i).getBelong() != null) {
                Log.d("SeatActivity", "isExistStu:" + mSeatsLis.get(i).getBelong().toString());
                if (mSeatsLis.get(i).getBelong().toString().equals(students.toString()))
                    return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateList(List<Seats> seats) {
        mSeatsLis.clear();
        if(App.getUser().getType().equals("1")) {
            mSeatsLis.addAll(seats);
            mScrollView1.setVisibility(View.VISIBLE);
            mClassAdapter.notifyDataSetChanged();
        }
        else {
            for (int i = seats.size()-1; i >= 0 ; i--) {
                mSeatsLis.add(seats.get(i));
            }
            mRecyclerView.scrollBy(0, Utils.dip2px(this,200));
            mScrollView2.setVisibility(View.VISIBLE);
            mClassAdapter.notifyDataSetChanged();
        }
        if(!mSeatsLis.isEmpty()){
            mTips.setVisibility(View.GONE);
        }
    }

    @Override
    public String getTeacher() {
        return getIntent().getStringExtra("teacher");
    }

    @Override
    public String getClassRoom() {
        return getIntent().getStringExtra("class");

    }
}
