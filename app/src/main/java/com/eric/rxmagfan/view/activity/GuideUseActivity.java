package com.eric.rxmagfan.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.adapter.GuidePagerAdapter;
import com.eric.rxmagfan.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Eric on 16/5/4.
 */
public class GuideUseActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        showView();
    }

    private List<Integer> getList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.howuse1);
        list.add(R.mipmap.howuse2);
        list.add(R.mipmap.howuse3);
        list.add(R.mipmap.howuse4);
        list.add(R.mipmap.howuse5);
        return list;
    }

    private void showView() {
        Observable.just(getList()).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                viewPager.setAdapter(new GuidePagerAdapter(GuideUseActivity.this, integers));
            }
        });
    }

}
