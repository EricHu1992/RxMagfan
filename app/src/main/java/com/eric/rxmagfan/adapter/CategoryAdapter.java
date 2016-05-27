package com.eric.rxmagfan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.model.CategoryModel;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eric on 16/5/8.
 */
public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryAdapter.CategoryViewHolder, CategoryModel> {

    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View onCreateItemView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return inflater.inflate(R.layout.category_item, parent, false);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(View itemView) {
        return new CategoryAdapter.CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, CategoryModel bean) {
        Uri uri = Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1402/12/c1/31189058_1392186616852.jpg");
        holder.categoryImg.setImageURI(uri);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_category)
        SimpleDraweeView categoryImg;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
