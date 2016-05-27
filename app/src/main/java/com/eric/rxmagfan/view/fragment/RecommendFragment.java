package com.eric.rxmagfan.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.adapter.RecommendAdapter;
import com.eric.rxmagfan.model.AlbumModel;
import com.eric.rxmagfan.component.itemdecoration.GridSpacingItemDecoration;
import com.eric.rxmagfan.view.BaseFragment;
import com.eric.rxmagfan.view.activity.AlbumDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 推荐
 * Created by Eric on 16/5/8.
 */
public class RecommendFragment extends BaseFragment implements RecommendAdapter.RecommendAdapterCallback {

    public RecommendFragment() {

    }

    public static RecommendFragment newInstance() {
        RecommendFragment recommendFragment = new RecommendFragment();
        Bundle args = new Bundle();
        //TODO

        recommendFragment.setArguments(args);
        return recommendFragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindDimen(R.dimen.album_space_size)
    int albumSpaceSize;

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        final RecommendAdapter recommendAdapter = new RecommendAdapter(getActivity(), this);
        recyclerView.setAdapter(recommendAdapter);


        recyclerView.addItemDecoration(new GridSpacingItemDecoration(albumSpaceSize, true));

        Observable.create(new Observable.OnSubscribe<List<AlbumModel>>() {
            @Override
            public void call(Subscriber<? super List<AlbumModel>> subscriber) {
                List<AlbumModel> beans = new ArrayList<>();
                for (int i = 0; i < 25; i++) {
                    AlbumModel bean = new AlbumModel();

                    beans.add(bean);
                }
                subscriber.onNext(beans);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AlbumModel>>() {
                    @Override
                    public void call(List<AlbumModel> beans) {
                        recommendAdapter.setBeans(beans);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public void onItemClick(int position, RecommendAdapter.AlbumViewHolder viewHolder, AlbumModel bean) {

        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),

                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                new Pair<View, String>(viewHolder.coverView,
                        AlbumDetailActivity.VIEW_NAME_COVER_IMG),
                new Pair<View, String>(viewHolder.nameView,
                        AlbumDetailActivity.VIEW_NAME_NAME_TXT)
        );

        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());
//        justStartActivity(AlbumDetailActivity.class);
    }
}
