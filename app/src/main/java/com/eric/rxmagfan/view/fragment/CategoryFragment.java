package com.eric.rxmagfan.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.adapter.CategoryAdapter;
import com.eric.rxmagfan.model.CategoryModel;
import com.eric.rxmagfan.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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
public class CategoryFragment extends BaseFragment {

    public CategoryFragment() {

    }

    public static CategoryFragment newInstance() {
        CategoryFragment recommendFragment = new CategoryFragment();
        Bundle args = new Bundle();
        //TODO

        recommendFragment.setArguments(args);
        return recommendFragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;

    @Override
    protected View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryAdapter = new CategoryAdapter(getActivity());
        recyclerView.setAdapter(categoryAdapter);

        Observable.create(new Observable.OnSubscribe<List<CategoryModel>>() {
            @Override
            public void call(Subscriber<? super List<CategoryModel>> subscriber) {
                List<CategoryModel> categoryModels = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    CategoryModel bean = new CategoryModel();

                    categoryModels.add(bean);
                }
                subscriber.onNext(categoryModels);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CategoryModel>>() {
                    @Override
                    public void call(List<CategoryModel> categoryModels) {
                        categoryAdapter.setBeans(categoryModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
