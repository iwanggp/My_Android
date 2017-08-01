package com.zerone.wgp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by D22391 on 2017/8/1.
 * 一个日期的时间工具类
 * 在这个项目中用于给文件命名的
 */

public class DateString {
	private final static String DETAIL_LSH_FORMAT = "yyyyMMddHHmmss";
	private static SimpleDateFormat lshFormat = new SimpleDateFormat(DETAIL_LSH_FORMAT);

	public synchronized static String lshFormat(Date date) {
		if (date == null)
			return "";
		else
			return lshFormat.format(date);
	}
}
