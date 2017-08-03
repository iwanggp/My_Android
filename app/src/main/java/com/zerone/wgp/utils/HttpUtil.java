package com.zerone.wgp.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by D22391 on 2017/8/3.
 * 一个访问Http的工具类
 */

public class HttpUtil {
	private static URL mURL;
	private static HttpURLConnection mHttpURLConnection;
	private static BufferedInputStream bis;
	private static String htmlSource;
	// 编码
	private static final String ECODING = "UTF-8";
	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*\t/^[\\w-]+(\\.png)$/>";
	//	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
//	private static final String urlRegex = "http://[^\":<>]*\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png)";
	private static final String urlRegex = "http://[^\":<>]*\\.(jpg|jpeg|png)";
	// 获取src路径的正则
	private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
	/**
	 * 获取网页源码的工具类
	 *
	 * @param url
	 * @return
	 */
	public static String getHtmlSrc(String url) {
		try {
			mURL = new URL(url);
			mHttpURLConnection = (HttpURLConnection) mURL.openConnection();
			mHttpURLConnection.setRequestMethod("GET");
			mHttpURLConnection.setConnectTimeout(5 * 1000);
			//通过输入流获取html二进制数据
			bis = new BufferedInputStream(mHttpURLConnection.getInputStream(), 1024 * 1024);
			byte[] data = readInputStream(bis);
			htmlSource = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mHttpURLConnection != null) {
				mHttpURLConnection.disconnect();
			}
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return htmlSource;
	}

	/** */
	/**
	 * 把二进制流转化为byte字节数组
	 *
	 * @param instream
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream instream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1204 * 1024];
		int len = 0;
		while ((len = instream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		instream.close();
		return outStream.toByteArray();
	}

	/**
	 * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0　的List
	 * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
	 *
	 * @param htmlCode HTML源码
	 * @return <img>标签 src 属性指向的图片地址的List集合
	 * @author Carl He
	 */
	/***
	 * 获取ImageUrl地址
	 *
	 * @param HTML
	 * @return
	 */
	public static List<String> getImageUrl(String HTML) {
		Matcher matcher = Pattern.compile(urlRegex).matcher(HTML);
		List<String> listImgUrl = new ArrayList<String>();
		while (matcher.find()) {
			listImgUrl.add(matcher.group());
		}
		return listImgUrl;
	}

	/***
	 * 获取ImageSrc地址
	 *
	 * @param listImageUrl
	 * @return
	 */
	public static List<String> getImageSrc(List<String> listImageUrl) {
		List<String> listImgSrc = new ArrayList<String>();
		for (String image : listImageUrl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
			}
		}
		return listImgSrc;
	}

	public static List<String> getImgSrc(String url) {
		List<String> imgUrl = getImageUrl(getHtmlSrc(url));
		List<String> imgSrc = getImageSrc(imgUrl);
		return imgUrl;
	}
}
