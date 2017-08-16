package com.zerone.wgp.viewholders;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zerone.wgp.activities.R;
import com.zerone.wgp.domain.ImageModel;

/**
 * Created by Administrator on 2017/8/4.
 */
public class PictureViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private ImageView mImageView;

    public PictureViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mImageView = itemView.findViewById(R.id.iv_picture);
    }

    public void bind(Bitmap bitmap) {
//        Picasso.with(mContext).load(url)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into((ImageView) mImageView);
        mImageView.setImageBitmap(bitmap);
    }
}
