package com.eric.rxmagfan.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.component.myrecyclerview.MyRecyclerView;
import com.eric.rxmagfan.component.myrecyclerview.MyRecyclerViewCallback;
import com.eric.rxmagfan.view.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 推荐
 * Created by Eric on 16/5/8.
 */
public class FocusFragment extends BaseFragment implements MyRecyclerViewCallback {

    private boolean loading;
    private int pageNum = 0;
    private int pageSize = 10;

    public FocusFragment() {

    }

    public static FocusFragment newInstance() {
        FocusFragment recommendFragment = new FocusFragment();
        Bundle args = new Bundle();
        //TODO

        recommendFragment.setArguments(args);
        return recommendFragment;
    }

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_focus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMyRecyclerView(view);
    }

    private MyRecyclerView myRecyclerView;

    private void initMyRecyclerView(View view) {
        myRecyclerView = (MyRecyclerView) view;
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.initAdapter(this);
        myRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        initErrorView();
        loadMore();
    }

    private void refresh() {
        int pageNum = 1;
        loadData(pageNum, new RefreshDataSuccessAction(pageNum));
    }

    private void loadMore() {
        int nextPageNum = pageNum + 1;
        loadData(nextPageNum, new GetDataSuccessAction(nextPageNum));
    }

    class RefreshDataSuccessAction implements Action1<List<String>> {

        private int pageNum;

        RefreshDataSuccessAction(int pageNum) {
            this.pageNum = pageNum;
        }

        @Override
        public void call(List<String> strings) {
            FocusFragment.this.pageNum = pageNum;
            myRecyclerView.setData(strings);
        }
    }

    class GetDataSuccessAction implements Action1<List<String>> {

        private int pageNum;

        GetDataSuccessAction(int pageNum) {
            this.pageNum = pageNum;
        }

        @Override
        public void call(List<String> strings) {
            FocusFragment.this.pageNum = pageNum;
            myRecyclerView.addData(strings);
        }
    }

    class GetDataFailedAction implements Action1<Throwable> {

        @Override
        public void call(Throwable throwable) {
            throwable.printStackTrace();
            if (myRecyclerView.getData().size() > 0) {
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
            } else {
                myRecyclerView.showErrorView();
            }
            stopLoading();
        }
    }

    private void loadData(final int pageNum, Action1<List<String>> nextAction) {
        loading = true;
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                if (new Random().nextBoolean()) {
                    List<String> dataList = new ArrayList<>();
                    for (int i = 1; i < pageSize; i++) {
                        dataList.add("pageNum" + pageNum + " ; String::" + i);
                    }
                    subscriber.onNext(dataList);
                    subscriber.onCompleted();
                } else {
                    String string = null;
                    int a = string.length();
                }
            }
        }).subscribeOn(Schedulers.io())
                .delay(1300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nextAction, new GetDataFailedAction(), new Action0() {
                    @Override
                    public void call() {
                        stopLoading();
                    }
                });
    }

    private void stopLoading() {
        loading = false;
        myRecyclerView.setRefreshing(false);
    }

    @Override
    public void onBindNormalViewHolder(Object t, RecyclerView.ViewHolder holder, int position) {
        ItemHolder holder1 = (ItemHolder) holder;
        Uri uri = Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1402/12/c1/31189058_1392186616852.jpg");
        holder1.nameView.setText(t + "position");
        holder1.coverView.setImageURI(uri);
    }

    @Override
    public RecyclerView.ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.album_item, parent, false);
        ItemHolder itemHolder = new ItemHolder(itemView);
        return itemHolder;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_album_cover)
        public SimpleDraweeView coverView;

        @BindView(R.id.txt_album_name)
        public TextView nameView;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onShowLastItem() {
        if (!loading) {
            loadMore();
        }
    }

    public void initErrorView() {
        Button retryBtn = (Button) myRecyclerView.getEmptyView().findViewById(R.id.button);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerView.dismissErrorView();
                myRecyclerView.setRefreshing(true);
                loadMore();
            }
        });
    }
}
