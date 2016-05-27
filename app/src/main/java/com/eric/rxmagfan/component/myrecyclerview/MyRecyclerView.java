package com.eric.rxmagfan.component.myrecyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;

import com.eric.rxmagfan.R;

import java.util.List;

/**
 * Created by Eric on 16/5/25.
 */
public class MyRecyclerView extends FrameLayout {

    private int myRecyclerviewMainLayout;
    private int mEmptyId;
    private int mLoadMoreId;

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mPtrLayout;
    protected ViewStub mEmpty;
    protected View mEmptyView;
    private MyRecyclerViewAdapter mAdapter;

    public MyRecyclerView(Context context) {
        super(context);
        initView();
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initView();
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.myrecyclerview);
        try {
            myRecyclerviewMainLayout = a.getResourceId(R.styleable.myrecyclerview_mainLayoutId, R.layout.layout_my_recyclerview);
            mEmptyId = a.getResourceId(R.styleable.myrecyclerview_layout_empty, R.layout.layout_error);
            mLoadMoreId = a.getResourceId(R.styleable.myrecyclerview_layout_moreProgress, R.layout.layout_load_more);
        } finally {
            a.recycle();
        }
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }

        try {
            View mainView = LayoutInflater.from(getContext()).inflate(myRecyclerviewMainLayout, this);
            mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
            mPtrLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.ptr_layout);
            mEmpty = (ViewStub) mainView.findViewById(R.id.net_error);
            mEmpty.setLayoutResource(mEmptyId);
            if (mEmptyId != 0)
                mEmptyView = mEmpty.inflate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public void initAdapter(MyRecyclerViewCallback callback) {
        mAdapter = new MyRecyclerViewAdapter<>(getContext(), mLoadMoreId);
        mAdapter.setCallback(callback);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setData(List dataList) {
        mAdapter.setDataList(dataList);
    }

    public void addData(List moreDataList) {
        mAdapter.addData(moreDataList);
    }

    public void addData(Object data) {
        mAdapter.addData(data);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    public void showErrorView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void dismissErrorView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mPtrLayout.setEnabled(true);
        mPtrLayout.setOnRefreshListener(listener);
    }

    /**
     * 是否刷新中
     *
     * @return
     */
    public boolean isRefreshing() {
        return mPtrLayout.isRefreshing();
    }

    /**
     * 控制刷新
     *
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        mPtrLayout.setRefreshing(refreshing);
    }


    public void setRefreshingColorResources(int... colorResIds) {
        mPtrLayout.setColorSchemeResources(colorResIds);
    }

    public void setRefreshEnable(boolean enable) {
        if (mPtrLayout != null)
            mPtrLayout.setEnabled(enable);
    }

    public void clear() {
        mAdapter.clear();
    }

    public List getData() {
        return mAdapter.getDataList();
    }
}
