package com.zerone.wgp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.adapter.PictureAdapter;
import com.zerone.wgp.domain.ImageModel;
import com.zerone.wgp.utils.GetImgUrlLoader;
import com.zerone.wgp.utils.PictureDownloader;
import com.zerone.wgp.viewholders.PictureViewHolder;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/8/3.
 */

public class PictureFragment extends Fragment implements LoaderManager.LoaderCallbacks {
	private static final String TAG = "PictureFragment";
	private RecyclerView mRecyclerView;
	//	private static final String htmlUrl = "http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6";
	private static final String htmlUrl = "http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6";
	private List<String> imagesList;
	private PictureDownloader<PictureViewHolder> mPictureDownloader;

	public static PictureFragment newInstance() {
		return new PictureFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Handler responseHandler = new Handler();
		mPictureDownloader = new PictureDownloader<>(responseHandler);
		mPictureDownloader.setPictureDownloadListener(
				new PictureDownloader.PictureDownloadListener<PictureViewHolder>() {
					@Override
					public void onPictureDownloaded(PictureViewHolder target, Bitmap bitmap) {
						target.bind(bitmap);
					}
				}


		);
		mPictureDownloader.start();
		mPictureDownloader.getLooper();
		Log.i(TAG, "Background thread started");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.picture_fragment, container, false);
		mRecyclerView = view.findViewById(R.id.picture_recyclerview);
		mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//设置一个新的布局每行显示3个
//		mRecyclerView.setAdapter(new PictureAdapter(getActivity(), imagesList));
		return view;
	}

	//首次创建Loader时调用该方法
	@Override
	public Loader onCreateLoader(int id, Bundle args) {
		return new GetImgUrlLoader(getActivity(), htmlUrl);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	//当Loader使用完成后,data为返回的数据
	@Override
	public void onLoadFinished(Loader loader, Object data) {
		imagesList = (List<String>) data;
		mRecyclerView.setAdapter(new PictureAdapter(getActivity(), imagesList, mPictureDownloader));
	}

	@Override
	public void onLoaderReset(Loader loader) {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPictureDownloader.quit();
		Log.i(TAG, "Background thread is destroyed");
	}
}
