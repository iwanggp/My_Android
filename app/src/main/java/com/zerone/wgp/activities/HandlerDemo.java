package com.zerone.wgp.activities;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import static android.R.id.message;

public class HandlerDemo extends AppCompatActivity {
	//在UI主线程上绑定一个handler，则该handler与UI线程为同一个线程
	private static final String TAG = "HandlerDemo";
	private Handler mHandler = new Handler();
	//创建一个HandlerThread为子线程
	private HandlerThread mHandlerThread;
	private Handler mCheckMsgHandler;
	private boolean isUpdateInfo = false;
	private static final int MSG_UPDATE_INFO = 100;
	private TextView tv_stock;
	private String[] messges = new String[]

			{
					"I love haoxiaoqian",
					"baby haoxianqian",
					"baby wangongpeng",
					"wanggongpeng",
					"hanqing"
			};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_demo2);
		updateInfo();
		tv_stock = (TextView) findViewById(R.id.tv_stock);
		Log.d(TAG, "Main Thread id is" + Thread.currentThread().getId());
	}

	//一个更新信息的子线程
	private void updateInfo() {
		mHandlerThread = new HandlerThread("update-info");
		mHandlerThread.start();
		Log.d(TAG, "updateInfo() thread id is " + Thread.currentThread().getId());
		mCheckMsgHandler = new Handler(mHandlerThread.getLooper()) {
			//该方法为子线程中运行的方法
			@Override
			public void handleMessage(Message msg) {
				try {
					//目前已经在子线程中了，在这里调用方法
					Log.d(TAG, "checkForUpdate haoxiaoqian" + Thread.currentThread().getId());
					checkForUpdate();//这里是要进行的耗时操作
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (isUpdateInfo) {
					mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
				}
			}
		};
	}

	private void checkForUpdate() throws InterruptedException {
		Thread.sleep(1000);
		String itme = null;
		for (int i = 0; i < messges.length; i++) {
			itme = messges[i];
		}
		final String finalItme = itme;
		mHandler.post(new Runnable() {
			@Override
			public void run() {

				Log.d(TAG, "checkForUpdate thread id is" + Thread.currentThread().getId());
				String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
				result = String.format(result, (int) (Math.random() * 3000 + 1000));
				tv_stock.setText(Html.fromHtml(result));
				Log.d(TAG, "run: " + finalItme);
			}
		});


	}


	@Override
	protected void onResume() {
		super.onResume();
		isUpdateInfo = true;
		mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
	}

	@Override
	protected void onPause() {
		super.onPause();
		isUpdateInfo = false;
		mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandlerThread.quit();
	}
}
