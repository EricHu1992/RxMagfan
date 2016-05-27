package com.eric.rxmagfan.component.myrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Eric on 16/5/25.
 */
public interface MyAdapterCallback {


    int getNormalItemCount();

    int getNormalItemViewType(int position);

    <VH extends RecyclerView.ViewHolder> VH onCreateNormalViewHolder(ViewGroup parent, int viewType);

    void loadMore();

    void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);
}
