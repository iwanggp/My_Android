package com.zerone.wgp.domain;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by D22391 on 2017/8/3.
 * 创建一个图片的bean
 */
public class ImageModel {
	private String url;
	private Bitmap mBitmap;
	private String stocck;

	public String getStocck() {
		return stocck;
	}

	public void setStocck(String stocck) {
		this.stocck = stocck;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		mBitmap = bitmap;
	}
}
