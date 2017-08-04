package com.zerone.wgp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.domain.ImageModel;
import com.zerone.wgp.utils.PictureDownloader;
import com.zerone.wgp.viewholders.PictureViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<String> mlist;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private static final String TAG = "PictureAdapter";
	private PictureDownloader<RecyclerView.ViewHolder> mPictureDownloader;

	public PictureAdapter(Context context, List<String> mlist, PictureDownloader pictureDownloader) {
		this.mContext = context;
		this.mlist = mlist;
		this.mPictureDownloader = pictureDownloader;
		Log.d(TAG, "PictureAdapter: " + mlist.toString() + "-------->><><><><>");
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
		Log.d(TAG, "onBindViewHolder: " + mlist.toString() + "------>>>>>>>");
//		((ImageModel)holder).setBitmap();
		mPictureDownloader.queueDownload(holder, mlist.get(position));


	}

	@Override
	public int getItemCount() {
		Log.d(TAG, "getItemCount: " + mlist.size());
		return mlist.size();
	}
}
