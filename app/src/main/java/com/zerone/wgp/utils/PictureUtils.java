package com.zerone.wgp.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by D22391 on 2017/8/1.
 * 一个处理图片压缩图片的工具类
 * 主要是压缩图片然后按照一定的比例显示
 */

public class PictureUtils {
	public static Bitmap getScaleBitmap(String path, int destWidth, int destHeigth) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		float srcWidth = options.outWidth;
		float srcHeight = options.outHeight;
		int inSampleSize = 1;//这个变量很关键，它决定缩略图像素的大小
		if (srcHeight > destHeigth || srcWidth > destWidth) {
			float heightScale = srcHeight / destHeigth;
			float widthScale = srcWidth / destWidth;
			inSampleSize = Math.round(heightScale > widthScale ? heightScale : widthScale);
		}
		options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;
		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap getScaleBitmap(String path, Activity activity) {
		Point size = new Point();
		activity.getWindowManager().getDefaultDisplay().getSize(size);
		return getScaleBitmap(path, size.x, size.y);
	}
}
