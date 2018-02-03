package cn.krisez.sign.ui.seat_ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.krisez.sign.R;
import cn.krisez.sign.adapter.ClassAdapter;
import cn.krisez.sign.adapter.OnItemClickListener;
import cn.krisez.sign.bean.ClassSeats;
import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.persenter.class_presenter.ClassPresenter;
import cn.krisez.sign.persenter.class_presenter.ClassPresenterImp;

public class SeatActivity extends AppCompatActivity implements ISeatView {

    private RecyclerView mRecyclerView;
    private ClassAdapter mClassAdapter;
    private List<Seats> mSeatsLis = new ArrayList<>();

    //demo
    private Button mButton;
    private ProgressBar mProgressBar;
    private ScrollView mScrollView;

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
        mButton = findViewById(R.id.class_button);
        mProgressBar = findViewById(R.id.progress_bar);
        mClassAdapter = new ClassAdapter(this, mSeatsLis);
        mRecyclerView = findViewById(R.id.class_recycler);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(13, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mClassAdapter);

        mButton = findViewById(R.id.class_button);
        mProgressBar = findViewById(R.id.progress_bar);
        mScrollView = findViewById(R.id.class_scroll);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.reset();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollView.scrollBy(dx, dy);
            }
        });

        mClassAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SeatActivity.this, mSeatsLis.get(position).getBelong().getName(), Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(SeatActivity.this)
                        .setTitle("信息")
                        .setMessage(mSeatsLis.get(position).getBelong().toString())
                        .show();
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

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
    }

    @Override
    public void updateList(List<Seats> seats) {
        mSeatsLis.addAll(seats);
        mClassAdapter.notifyDataSetChanged();
    }
}
