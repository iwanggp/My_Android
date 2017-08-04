package com.zerone.wgp.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.zerone.wgp.domain.ImageModel;
import com.zerone.wgp.utils.HttpUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HandlerThreadDemo extends AppCompatActivity {
	private static final String TAG = "HandlerThreadDemo";
	private ImageView iv_photo;
	private final static String DOWNLOAD_PICTURE = "downloadpicture";
	private TextView tv_stock;
	/**
	 * 图片地址集合
	 */
	private String url[] = {
			"http://img.blog.csdn.net/20160903083245762",
			"http://img.blog.csdn.net/20160903083252184",
			"http://img.blog.csdn.net/20160903083257871",
			"http://img.blog.csdn.net/20160903083257871",
			"http://img.blog.csdn.net/20160903083311972",
			"http://img.blog.csdn.net/20160903083319668",
			"http://img.blog.csdn.net/20160903083326871",
			"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501740745934&di=df2675e18288f184706dc93cb7d7c2d3&imgtype=0&src=http%3A%2F%2Ffile.wmtuku.com%2Ffile%2F2017-06-27%2Fe0d85f871a8c178ead4734ce4f0e4cea.jpg",
			"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%91%A8%E6%9D%B0%E4%BC%A6&ie=utf-8&in=&cl=2&lm=-1&st=&cs=undefined&os=undefined&pn=-1&rn=1&di=&ln=undefined&fr=&fm=undefined&fmq=undefined&ic=&s=&se=&sme=undefined&tab=&width=&height=&face=&is=null&istype=#pn-1&-1&di&objURL&fromURL&W&H&T&S&TP"

	};
	//主线程handler用于更新ui，这时的handler与主线程绑定
	private Handler mUIHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Log.d(TAG, "handleMessage: 所在线程的ID is " + Thread.currentThread().getId());
			Log.e(TAG, "次数" + msg.what + "--");
			List<ImageModel> mList = (List<ImageModel>) msg.obj;
//			ImageModel imageModel = (ImageModel) msg.obj;
//			for (int i = 0; i < mList.size(); i++) {
			iv_photo.setImageBitmap(mList.get(5).getBitmap());
//			}
//			tv_stock.setText(Html.fromHtml(imageModel.getStocck()));
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_thread_demo);
		Log.d(TAG, "main thread id is -------" + Thread.currentThread().getId());
		iv_photo = (ImageView) findViewById(R.id.iv_image);
		tv_stock = (TextView) findViewById(R.id.tv_stock);
		//创建异步的HandlerThread
		HandlerThread mHandlerThread = new HandlerThread(DOWNLOAD_PICTURE);
		//必须先开启线程
		mHandlerThread.start();
		//子线程handler
		Handler childrenHandler = new Handler(mHandlerThread.getLooper(), new ChildCallback());
		Message msg = new Message();
		msg.obj = url;
		childrenHandler.sendMessage(msg);
//		for (int i = 0; i < url.length; i++) {
//			childrenHandler.sendMessage(msg);
////			childrenHandler.sendMessageDelayed(msg, 1000);
//		}
////			childrenHandler.sendMessageAtTime(i, 1000);
//			childrenHandler.sendEmptyMessageDelayed(i % url.length, 1200);
//			Log.d(TAG, "childrenHandler发送的i=" + i);
//
//		}
	}

	/*
	 * 该callback运行于子线程中
	 */
	class ChildCallback implements Handler.Callback {

		@Override
		public boolean handleMessage(Message message) {
			try {
				Thread.sleep(1000);
				Log.d(TAG, "ChildCallback Thread is " + Thread.currentThread().getId());
				//在子线程中进行网络请求
				String[] list = (String[]) message.obj;
				Log.d(TAG, "handleMessage: " + list.length);
				List<ImageModel> modelLists = new ArrayList<ImageModel>();
				Log.d(TAG, "src----" + HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%CD%BC%C6%AC&fr=ala&ala=1&alatpl=others&pos=0").toString());
				List<String> ll = HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%CD%BC%C6%AC&fr=ala&ala=1&alatpl=others&pos=0");
				for (String _list : ll) {
					Bitmap bitmap = downloaderUrlBitmap(_list);
					ImageModel imageModel = new ImageModel();
					imageModel.setBitmap(bitmap);
					modelLists.add(imageModel);
				}
//			Bitmap bitmap = downloaderUrlBitmap(url[message.what]);
//			String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
//			result = String.format(result, (int) (Math.random() * 3000 + 1000));
//			ImageModel imageModel = new ImageModel();
//			imageModel.setUrl(url[message.what]);
//			imageModel.setBitmap(bitmap);
//			imageModel.setStocck(result);
				Message msg1 = new Message();
				msg1.what = message.what;
				msg1.obj = modelLists;
//			//通知主线程更新UI
				mUIHandler.sendMessage(msg1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}

	private Bitmap downloaderUrlBitmap(String s) {
		HttpURLConnection urlConnection = null;
		BufferedInputStream in = null;
		Bitmap bitmap = null;
		try {
			final URL url = new URL(s);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream(), 1024 * 1024);
			bitmap = BitmapFactory.decodeStream(in);
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}
}
