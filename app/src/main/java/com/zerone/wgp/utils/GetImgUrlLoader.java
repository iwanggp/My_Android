package com.zerone.wgp.utils;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by D22391 on 2017/8/4.
 * 获取图片地址的Loader工具类
 * 中间的泛型为通过loader类返回的值,在写该类的时候提前声明回省很多的事
 */

public class GetImgUrlLoader extends AsyncTaskLoader<List<String>> {
	private String url;
	private List<String> mList;

	public GetImgUrlLoader(Context context, String url) {
		super(context);
		this.url = url;
	}

	@Override
	protected void onStartLoading() {
		super.onStartLoading();
		if (mList != null) {
			deliverResult(mList);
		} else {
			forceLoad();
		}

	}

	//后台进行的任务
	@Override
	public List<String> loadInBackground() {
		mList = HttpUtil.getImgSrc(url);
		return mList;
	}
}
