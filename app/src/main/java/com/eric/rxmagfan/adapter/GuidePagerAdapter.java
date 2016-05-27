package com.eric.rxmagfan.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eric.rxmagfan.view.activity.MainActivity;

import java.util.List;

/**
 * Created by Eric on 16/5/5.
 */
public class GuidePagerAdapter extends AbstractViewPagerAdapter<Integer> {

    private Context context;
    //    private LayoutInflater inflater;
    private ViewGroup.LayoutParams childLp;

    public GuidePagerAdapter(Context context, List<Integer> pagerBeans) {
        super(pagerBeans);
        this.context = context;
//        inflater = LayoutInflater.from(context);
        childLp = new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
    }

    public View newView(int position) {
        ImageView childImgView = new ImageView(context);
        childImgView.setLayoutParams(childLp);
        childImgView.setImageResource(mData.get(position));
        if (position == getCount() - 1) {
            childImgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goMainPage();
                }
            });
        }
        return childImgView;
    }

    private void goMainPage() {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

}
