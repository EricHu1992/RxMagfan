package com.eric.rxmagfan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerview数据适配.有多次一举之嫌
 * Created by Eric on 16/5/8.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> beans;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.beans = new ArrayList<>();
    }

    public void setBeans(List<T> beans) {
        this.beans.clear();
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, beans.get(position));
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = onCreateItemView(inflater, parent, viewType);
        return onCreateViewHolder(itemView);
    }

    public abstract View onCreateItemView(LayoutInflater inflater, ViewGroup parent, int viewType);

    public abstract VH onCreateViewHolder(View itemView);

    public abstract void onBindViewHolder(VH holder, T bean);


    @Override
    public int getItemCount() {
        return this.beans.size();
    }

}
