package cn.krisez.sign.ui.main_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.adapter.TableAdapter;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.persenter.table_presenter.ITablePresenter;
import cn.krisez.sign.persenter.table_presenter.TablePresenter;
import cn.krisez.sign.ui.login_ui.LoginActivity;
import cn.krisez.sign.ui.person_ui.PersonActivity;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import cn.krisez.sign.widget.DividerDecoration;

/**
 * Created by Krisez on 2018-01-27.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ITableView {

    public static final int REQUEST_LOGIN = 100;
    public static final int REQUEST_PERSON = 200;

    private RecyclerView mRecyclerView;
    private TableAdapter mTableAdapter;
    private List<KeBiao> mKeBiaos = new ArrayList<>();

    //nav的id，main的tip
    private TextView mTextViewId;
    private TextView mTips;

    private ITablePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new TablePresenter(this);

        init();
        initOperation();
    }

    private void initOperation() {
        mTips = findViewById(R.id.main_tip);
        mTableAdapter = new TableAdapter(this, mKeBiaos);
        mRecyclerView = findViewById(R.id.table_recycler);

        if (SharedPreferenceUtil.getXh().isEmpty()) {
            mTips.setVisibility(View.VISIBLE);
            mTextViewId.setText("未登录");
        } else {
            mPresenter.getTable();
            mTextViewId.setText(SharedPreferenceUtil.getXh());
        }
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerDecoration(this, DividerDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mTableAdapter);

        mTableAdapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mTextViewId = navigationView.getHeaderView(0).findViewById(R.id.nav_id_textView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_refresh) {
            Toast.makeText(this, "课表刷新中", Toast.LENGTH_SHORT).show();
            mKeBiaos.removeAll(mKeBiaos);
            mPresenter.updateTable();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_personal) {
            if (!SharedPreferenceUtil.getXh().isEmpty()) {
                intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivityForResult(intent, REQUEST_PERSON);
            } else {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
            }
        } else if (id == R.id.nav_sign) {
        } else if (id == R.id.nav_work) {
            //intent = new Intent(MainActivity.this, WorkActivity.class);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showTable(List<KeBiao> keBiaoList) {
        if (mTips.getVisibility() == View.VISIBLE) {
            mTips.setVisibility(View.GONE);
        }
        mKeBiaos.addAll(keBiaoList);
        mTableAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_LOGIN:
                mTextViewId.setText(data.getStringExtra("xh"));
                break;
            case REQUEST_PERSON:
                if(resultCode == PersonActivity.RESULT_LOGOUT) {
                    mTextViewId.setText("未登录");
                    mKeBiaos.removeAll(mKeBiaos);
                    mTips.setVisibility(View.VISIBLE);
                    mTableAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
