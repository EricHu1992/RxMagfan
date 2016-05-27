package com.eric.rxmagfan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eric.rxmagfan.view.fragment.CategoryFragment;
import com.eric.rxmagfan.view.fragment.FocusFragment;
import com.eric.rxmagfan.view.fragment.MineFragment;
import com.eric.rxmagfan.view.fragment.RecommendFragment;
import com.eric.rxmagfan.view.fragment.TestFragment;


/**
 * Created by Eric on 16/3/17.
 */
public class MainTabPagerAdapter extends FragmentStatePagerAdapter {

    private final int pagerCounts = 4;

    public MainTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RecommendFragment.newInstance();
            case 1:
                return FocusFragment.newInstance();
            case 2:
                return CategoryFragment.newInstance();
            case 3:
                return MineFragment.newInstance();
        }

        return TestFragment.newInstance();
    }

    @Override
    public int getCount() {
        return pagerCounts;
    }
}
