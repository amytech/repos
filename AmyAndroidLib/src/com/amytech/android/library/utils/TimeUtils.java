package com.amytech.android.library.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 时间相关工具类
 * 
 * @author AmyTech
 */
public class TimeUtils {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

	private TimeUtils() {
		throw new AssertionError();
	}

	public static String getMM_SS(int milliSecond) {
		double secondTime = (double) milliSecond / 1000d;
		int minute = (int) secondTime / 60;
		int second = (int) (secondTime - minute * 60);
		if (String.valueOf(second).length() < 2) {
			return String.valueOf(minute) + ":0" + String.valueOf(second);
		} else {
			return String.valueOf(minute) + ":" + String.valueOf(second);
		}
	}

	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}
}