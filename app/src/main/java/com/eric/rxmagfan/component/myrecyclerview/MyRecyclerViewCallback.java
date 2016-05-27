package com.eric.rxmagfan.component.myrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eric on 16/5/25.
 */
public interface MyRecyclerViewCallback {

    void onBindNormalViewHolder(Object t, RecyclerView.ViewHolder holder, int position);

    RecyclerView.ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    void onShowLastItem();

}
