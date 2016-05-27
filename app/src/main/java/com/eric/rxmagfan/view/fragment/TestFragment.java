package com.eric.rxmagfan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.view.BaseFragment;


/**
 * Created by Eric on 16/3/17.
 */
public class TestFragment extends BaseFragment {

    public TestFragment() {

    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
