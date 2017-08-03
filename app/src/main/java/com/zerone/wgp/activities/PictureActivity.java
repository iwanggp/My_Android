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
    private HandlerThread mHandlerThread;
    private List<Bitmap> mBitmaps;
    private List<ImageModel> mList;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "Thread id is " + Thread.currentThread().getId() + "----------->>>>>>>>>>");
            mList = (List<ImageModel>) msg.obj;
            Log.d(TAG, "handleMessage: " + mList.size());
        }
    };

    @Override
    protected Fragment createFragment() {
        mHandlerThread = new HandlerThread("pictures");
        mHandlerThread.start();
        Handler getBitMapHandler = new Handler(mHandlerThread.getLooper(), new MyCallBack());
        getBitMapHandler.sendEmptyMessage(100);
        return PictureFragment.newInstance(mList);
    }

    class MyCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            Log.d(TAG, "handleMessage id is " + Thread.currentThread().getId() + "----------->>>>>>>>>>");
            try {
                //在子线程中进行网络请求
                String[] list = (String[]) message.obj;
                List<ImageModel> modelLists = new ArrayList<ImageModel>();
                Log.d(TAG, "src----" + HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6").toString());
                List<String> ll = HttpUtil.getImgSrc("http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=%E5%91%A8%E6%9D%B0%E4%BC%A6");
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
                mHandler.sendMessage(msg1);
//			//通知主线程更新UI
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;

        }
    }
}
