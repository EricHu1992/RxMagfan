package com.eric.rxmagfan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.view.BaseFragment;

/**
 * 推荐
 * Created by Eric on 16/5/8.
 */
public class MineFragment extends BaseFragment {

    public MineFragment() {

    }

    public static MineFragment newInstance() {
        MineFragment recommendFragment = new MineFragment();
        Bundle args = new Bundle();
        //TODO

        recommendFragment.setArguments(args);
        return recommendFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}
