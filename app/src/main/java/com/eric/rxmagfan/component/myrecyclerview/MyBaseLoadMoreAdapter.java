package com.eric.rxmagfan.component.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eric on 16/5/25.
 */
public abstract class MyBaseLoadMoreAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int NORMAL_ITEM_VIEW_TYPE = 0;
    private final int LOAD_MORE_ITEM_VIEW_TYPE = 99;

    private Context context;
    private LayoutInflater inflater;

    private int mLoadMoreId;

    public MyBaseLoadMoreAdapter(Context context, int mLoadMoreId) {
        this.context = context;
        this.mLoadMoreId = mLoadMoreId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return LOAD_MORE_ITEM_VIEW_TYPE;
        } else {
            return getNormalItemViewType(position);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_ITEM_VIEW_TYPE) {
            return onCreateLoadMoreViewHolder(parent);
        } else {
            return onCreateNormalViewHolder(inflater, parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == LOAD_MORE_ITEM_VIEW_TYPE) {
            onBindLoadMoreViewHolder((LoadMoreItemViewHolder) holder, position);
        } else {
            onBindNormalViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return getNormalItemCount() + 1;
    }


    protected abstract int getNormalItemViewType(int position);

    protected abstract int getNormalItemCount();

    protected abstract <VH extends RecyclerView.ViewHolder> VH onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    protected abstract void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract void onShowLastItem();

    /**
     * 默认的加载更多item
     *
     * @param parent
     * @return
     */
    protected VH onCreateLoadMoreViewHolder(ViewGroup parent) {
        View loadMoreItemView = inflater.inflate(mLoadMoreId, parent, false);
        return (VH) new LoadMoreItemViewHolder(loadMoreItemView);
    }

    /**
     * 每次滑动到底部的时候,将会触发此方法,这时可以用来触发加载更多
     * TODO
     *
     * @param holder
     * @param position
     */
    protected void onBindLoadMoreViewHolder(LoadMoreItemViewHolder holder, int position) {
        onShowLastItem();
    }

    static class LoadMoreItemViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreItemViewHolder(View itemView) {
            super(itemView);
        }

    }
}
