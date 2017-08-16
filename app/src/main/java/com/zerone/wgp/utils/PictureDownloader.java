package com.zerone.wgp.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by D22391 on 2017/8/4.
 * 图片下载的后台任务继承HandlerThread
 */

public class PictureDownloader<T> extends HandlerThread {
	private static final String TAG = "PictureDownloader";
	private Boolean mHasQuit = false;//是否退出当前线程
	private static final int MESSAGE_DOWNLOAD = 0;
	private Handler mRequestHandler;//存储对Handler的引用，负责管理后台线程上管理下载请求消息
	private ConcurrentMap<T, String> mRequestMap = new ConcurrentHashMap<>();//一种线程安全的HashMap
	private Handler mResponseHandler;//与主线程通信的handler
	private PictureDownloadListener<T> mPictureDownloadListener;

	public interface PictureDownloadListener<T> {//定义一个回调的接口
		void onPictureDownloaded(T target, Bitmap bitmap);
	}

	public void setPictureDownloadListener(PictureDownloadListener<T> listener) {
		mPictureDownloadListener = listener;
	}

	public PictureDownloader(Handler responseHandler) {
		super(TAG);//继承自HandlerThread的子类一定要调用父类的构造方法，这个非常关键
		mResponseHandler = responseHandler;//传递过来与主线程通信的Handler
	}

	@Override
	public boolean quit() {
		mHasQuit = true;
		return super.quit();
	}

	@Override
	protected void onLooperPrepared() {//是在Looper首次检查消息队列之前调用的
		mRequestHandler = new Handler() {//在这个方法内创建Handler最合适
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == MESSAGE_DOWNLOAD) {
					T target = (T) msg.obj;
					Log.i(TAG, "Got a request for URL" + mRequestMap.get(target));
					handleRequest(target);
				}
			}
		};
	}

	private void handleRequest(final T target) {//在这里执行的是后台耗时的任务
		final String url = mRequestMap.get(target);
		if (url == null) {
			return;
		}
		final Bitmap bitmap = HttpUtil.downloaderUrlBitmap(url);
		Log.i(TAG, "handleRequest: bitmap created");
		mResponseHandler.post(new Runnable() {//发往给主线程
			@Override
			public void run() {
				if (mRequestMap.get(target) != url || mHasQuit) {
					return;
				}
				mRequestMap.remove(target);
				mPictureDownloadListener.onPictureDownloaded(target, bitmap);
			}
		});


	}

	public void queueDownload(T target, String url) {
		Log.i(TAG, "queueDownload: got url " + url);
		if (url == null) {
			mRequestMap.remove(target);
		} else {
			mRequestMap.put(target, url);
			mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD, target).sendToTarget();//发送给线程
		}
	}
}
