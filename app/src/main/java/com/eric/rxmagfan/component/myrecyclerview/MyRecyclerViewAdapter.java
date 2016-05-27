package com.eric.rxmagfan.component.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 16/5/25.
 */
public class MyRecyclerViewAdapter<T> extends MyBaseLoadMoreAdapter {

    private MyRecyclerViewCallback callback;
    private List<T> dataList;

    public MyRecyclerViewAdapter(Context context, int mLoadMoreId) {
        super(context, mLoadMoreId);
        dataList = new ArrayList<>();
    }

    public void setDataList(List<T> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addData(List<T> moreDataList) {
        this.dataList.addAll(moreDataList);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        this.dataList.add(data);
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setCallback(MyRecyclerViewCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int getNormalItemViewType(int position) {
        return NORMAL_ITEM_VIEW_TYPE;
    }

    @Override
    protected int getNormalItemCount() {
        return dataList.size();
    }

    @Override
    protected void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (callback != null) {
            callback.onBindNormalViewHolder(dataList.get(position),holder, position);
        }
    }

    @Override
    protected RecyclerView.ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        if (callback != null) {
            return callback.onCreateNormalViewHolder(inflater, parent, viewType);
        }
        return null;
    }

    @Override
    protected void onShowLastItem() {
        if (callback != null) {
            callback.onShowLastItem();
        }
    }

    public void clear() {
        this.dataList.clear();
        notifyDataSetChanged();
    }
}