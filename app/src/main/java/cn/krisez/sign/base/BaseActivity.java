package cn.krisez.sign.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.krisez.sign.R;

/**
 * Created by Krisez on 2018-02-03.
 */


@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity{

    private ProgressBar mProgressBar;
    protected Toolbar toolbar;
    private TextView bTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initToolbar();
        mProgressBar = findViewById(R.id.base_progress);
        LinearLayout linearLayout = findViewById(R.id.base_layout);
        linearLayout.addView(setView());
        initSon();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitle(setbTitle());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        bTitle = (TextView) findViewById(R.id.base_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected abstract View setView();
    protected abstract void initSon();
    protected abstract String setbTitle();//不要title的时候设置return ""

    protected void missProgress(){
        mProgressBar.setVisibility(View.GONE);
    }
    protected void showProgress(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    protected void showError(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
