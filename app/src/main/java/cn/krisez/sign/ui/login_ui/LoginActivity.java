package cn.krisez.sign.ui.login_ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.persenter.login_presenter.ILoginPresenter;
import cn.krisez.sign.persenter.login_presenter.LoginPresenter;

/**
 * Created by Krisez on 2018-01-27.
 */

public class LoginActivity extends FragmentActivity{

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mProgressBar = findViewById(R.id.login_pro);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.login_tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.login_viewpager);

        List<String> itemTitles = Arrays.asList(
                getResources().getString(R.string.sign_in),
                getResources().getString(R.string.sign_up));
        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(itemTitles.get(i)));
        }
        tabLayout.setTabTextColors(Color.BLACK, Color.parseColor("#67b5f4"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LoginFrag());
        fragmentList.add(new RegisFrag());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, itemTitles));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public void success(String xh){
        Intent intent = new Intent();
        intent.putExtra("xh",xh);
        setResult(RESULT_OK,intent);
        finish();
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;
        private List<String> mItemTitle;
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> itemTitle) {
            super(fm);
            this.mFragmentList = fragmentList;
            this.mItemTitle = itemTitle;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mItemTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mItemTitle.get(position);
        }
    }
}
