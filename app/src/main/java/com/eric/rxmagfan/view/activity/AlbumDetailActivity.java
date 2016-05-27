package com.eric.rxmagfan.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.adapter.CategoryAdapter;
import com.eric.rxmagfan.model.CategoryModel;
import com.eric.rxmagfan.view.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Eric on 16/5/8.
 */
public class AlbumDetailActivity extends BaseActivity {

    public static final String VIEW_NAME_COVER_IMG = "album:detail:cover:image";
    public static final String VIEW_NAME_NAME_TXT = "album:detail:name:txt";

    @BindView(R.id.cover_img)
    SimpleDraweeView coverImg;
    //    @BindView(R.id.txt_album_name)
//    TextView albumNameView;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this);
        ViewCompat.setTransitionName(coverImg, VIEW_NAME_COVER_IMG);
//        ViewCompat.setTransitionName(albumNameView, VIEW_NAME_NAME_TXT);

//        initToolBar();
        initRecyclerView();

        coverImg.setImageURI(Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1402/12/c1/31189058_1392186616852.jpg"));
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this);
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
                        Toast.makeText(AlbumDetailActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

//    private void initToolBar() {
//        toolbar.setTitle("我屮");
//        toolbar.setTitleTextColor(Color.WHITE);
//    }

}
