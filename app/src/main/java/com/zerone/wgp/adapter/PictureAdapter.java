package com.zerone.wgp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.domain.ImageModel;
import com.zerone.wgp.viewholders.PictureViewHolder;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/8/4.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ImageModel> mlist;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public PictureAdapter(Context context, List<ImageModel> mlist) {
        this.mContext = context;
        this.mlist = mlist;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_image, parent, false);
        PictureViewHolder pictureViewHolder = new PictureViewHolder(view, mContext);
        return pictureViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PictureViewHolder)holder).bind(mlist.get(position));

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
