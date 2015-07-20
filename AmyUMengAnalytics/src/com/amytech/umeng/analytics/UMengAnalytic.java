package com.amytech.umeng.analytics;

import java.util.Map;

import android.content.Context;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.UmengOnlineConfigureListener;

/**
 * Title: AmyUMengAnalytics <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月20日 下午4:45:40 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月20日 <br>
 *
 * @author marktlzhai
 */
public class UMengAnalytic {

	public static final String CHANNEL_DEFAULT = "DEFAULT";
	public static final String CHANNEL_QQ = "QQ";
	public static final String CHANNEL_BAIDU = "Baidu";
	public static final String CHANNEL_91 = "91";
	public static final String CHANNEL_ANZHI = "AnZhi";

	public static void init(Context context, String appKey, String channel, UmengOnlineConfigureListener listener) {
		AnalyticsConfig.setAppkey(appKey);
		AnalyticsConfig.enableEncrypt(true);
		AnalyticsConfig.setChannel(channel);

		MobclickAgent.setDebugMode(true);
		MobclickAgent.openActivityDurationTrack(false);

		OnlineConfigAgent.getInstance().setDebugMode(true);
		OnlineConfigAgent.getInstance().setOnlineConfigListener(listener);
		OnlineConfigAgent.getInstance().updateOnlineConfig(context);
	}

	public static void onEvent(Context context, String event) {
		MobclickAgent.onEvent(context, event);
	}

	public static void onEvent(Context context, String event, Map<String, String> data) {
		MobclickAgent.onEvent(context, event, data);
	}

	public static void onEventValue(Context context, String event, Map<String, String> data, int duration) {
		MobclickAgent.onEventValue(context, event, data, duration);
	}

	public static void onError(Context context, String error) {
		MobclickAgent.reportError(context, error);
	}

	public static void onError(Context context, Throwable t) {
		MobclickAgent.reportError(context, t);
	}

	public static void onKillProcess(Context context) {
		MobclickAgent.onKillProcess(context);
	}

	public static String getConfig(Context context, String key) {
		return OnlineConfigAgent.getInstance().getConfigParams(context, key);
	}
}
