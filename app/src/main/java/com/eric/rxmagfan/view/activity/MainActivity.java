package com.eric.rxmagfan.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.adapter.MainTabPagerAdapter;
import com.eric.rxmagfan.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initTabLayout();
        initViewPager();
    }

    private void initToolBar() {
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("我屮艸芔茻");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.recommend));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.focus));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.category));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.mine));
    }

    private void initViewPager() {
        MainTabPagerAdapter mPagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
