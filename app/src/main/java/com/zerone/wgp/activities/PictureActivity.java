package com.zerone.wgp.activities;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.domain.ImageModel;
import com.zerone.wgp.fragment.PictureFragment;
import com.zerone.wgp.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import static com.zerone.wgp.utils.HttpUtil.downloaderUrlBitmap;

/**
 * Created by Administrator on 2017/8/3.
 */

public class PictureActivity extends BaseFragmentActivity {
	private static final String TAG = "PictureActivity";

	@Override
	protected Fragment createFragment() {
//		mHandlerThread = new HandlerThread("pictures");
//		mHandlerThread.start();
//		Handler getBitMapHandler = new Handler(mHandlerThread.getLooper(), new MyCallBack());
//		getBitMapHandler.sendEmptyMessage(100);
		return PictureFragment.newInstance();
	}

//	class MyCallBack implements Handler.Callback {
//
//		@Override
//		public boolean handleMessage(Message message) {
//			Log.d(TAG, "handleMessage id is " + Thread.currentThread().getId() + "----------->>>>>>>>>>");
//			try {
//				//在子线程中进行网络请求
//				String[] list = (String[]) message.obj;
//				List<ImageModel> modelLists = new ArrayList<ImageModel>();
//				Log.d(TAG, "src----" + HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6").toString());
//				List<String> ll = HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6");
//				Log.d(TAG, "llllllll" + ll.toString() + "========>>>>>>>>>");
//				for (String _list : ll) {
//					Bitmap bitmap = downloaderUrlBitmap(_list);
//					ImageModel imageModel = new ImageModel();
//					imageModel.setBitmap(bitmap);
//					modelLists.add(imageModel);
//					Log.d(TAG, "Successfull ..........");
//				}
//				Log.d(TAG, "modelList===" + modelLists.toString());
//				Message msg1 = new Message();
//				msg1.what = message.what;
//				msg1.obj = modelLists;
//				Log.d(TAG, "obj obj obj " + msg1.obj.toString());
//				mHandler.sendMessage(msg1);
////			//通知主线程更新UI
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return false;
//
//		}
//	}
}
