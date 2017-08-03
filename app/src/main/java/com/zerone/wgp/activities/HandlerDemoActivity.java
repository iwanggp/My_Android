package com.zerone.wgp.activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerDemoActivity extends AppCompatActivity {
	private TextView tv_handler;
	private Button bt_handler;
	//mHandler在主线程创建，所以自动绑定到主线程
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					System.out.println("handlermessage thread id is" + Thread.currentThread().getId());
//					System.out.println();
					System.out.println(msg.obj);
					tv_handler.setText("完成ssssss");
					break;
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_demo);
		tv_handler = (TextView) findViewById(R.id.tv_handler);
		bt_handler = (Button) findViewById(R.id.bt_handler);
		System.out.println("Main thread id is " + Thread.currentThread().getId());
		bt_handler.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DownLoader downLoader = new DownLoader();
				downLoader.start();
			}
		});
	}

	private class DownLoader extends Thread {
		@Override
		public void run() {
			System.out.println("开始下载文件.....");
			try {
				System.out.println("DownLoadThred id is " + Thread.currentThread().getId());
				Thread.sleep(5000);
				System.out.println("下载完成");
//				Message message = new Message();
//				message.what = 1;
//				message.arg1 = 1;
//				message.arg2 = 3;
//				message.obj = "ssss";

				//下载完成后开始更新UI
//				Runnable runnable = new Runnable() {
//					@Override
//					public void run() {
//						System.out.println("Runable thread id " + Thread.currentThread().getId());
//						tv_handler.setText("haoxiaoqian");
//					}
//				};
//				mHandler.sendMessage(message);
				mHandler.sendEmptyMessage(1);
//				mHandler.post(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
