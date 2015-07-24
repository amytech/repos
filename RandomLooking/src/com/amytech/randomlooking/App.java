package com.amytech.randomlooking;

import com.amytech.android.library.base.BaseApplication;
import com.amytech.umeng.analytics.UMengAnalytic;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 上午10:25:00 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class App extends BaseApplication {

	public static final class UMeng {
		public static final String EVENT_TAB_GIRL = "TAB_GIRL";
		public static final String EVENT_TAB_WX = "TAB_WX";
		public static final String EVENT_TAB_QIWEN = "TAB_QIWEN";
		public static final String EVENT_TAB_HUABIAN = "TAB_HUABIAN";
		public static final String EVENT_TAB_SOCIAL = "TAB_SOCIAL";
	}

	public static final String QQ_APPID = "1104707169";
	public static final String QQ_APPKEY = "aS0CqPKcsj2wamSM";

	public static final String WX_APPID = "wx05cfa11c72d7e2f5";
	public static final String WX_APP_SECRET = "a4c3f5a0ed43e878898560c9afe7e4c9";

	@Override
	public void onCreate() {
		super.onCreate();

		UMengAnalytic.init(this, "55b056ef67e58eb80500705b", UMengAnalytic.CHANNEL_DEFAULT);
	}
}
