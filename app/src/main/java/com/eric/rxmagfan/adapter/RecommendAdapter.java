package com.eric.rxmagfan.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eric.rxmagfan.R;
import com.eric.rxmagfan.model.AlbumModel;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eric on 16/5/8.
 */
public class RecommendAdapter extends BaseRecyclerViewAdapter<RecommendAdapter.AlbumViewHolder, AlbumModel> {

    private RecommendAdapterCallback adapterCallback;

    public RecommendAdapter(Context context, RecommendAdapterCallback adapterCallback) {
        super(context);
        this.adapterCallback = adapterCallback;
    }

    public interface RecommendAdapterCallback {
        void onItemClick(int position, AlbumViewHolder viewHolder, AlbumModel bean);
    }

    @Override
    public View onCreateItemView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return inflater.inflate(R.layout.album_item, parent, false);
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(View itemView) {
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, final AlbumModel bean) {
        Uri uri = Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1402/12/c1/31189058_1392186616852.jpg");
        holder.nameView.setText(holder.getAdapterPosition() + "position");
        holder.coverView.setImageURI(uri);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallback.onItemClick(holder.getAdapterPosition(), holder, bean);
            }
        });
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_album_cover)
        public SimpleDraweeView coverView;

        @BindView(R.id.txt_album_name)
        public TextView nameView;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
