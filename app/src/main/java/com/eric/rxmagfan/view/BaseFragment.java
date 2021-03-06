package com.eric.rxmagfan.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Eric on 16/3/17.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = onCreateMyView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    protected abstract View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    public void justStartActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }

}
