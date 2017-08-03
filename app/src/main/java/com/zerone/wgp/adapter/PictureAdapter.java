package com.zerone.wgp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zerone.wgp.domain.ImageModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public PictureAdapter(Context context, List<ImageModel> mlist) {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
