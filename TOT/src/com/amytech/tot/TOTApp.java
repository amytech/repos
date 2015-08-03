package com.amytech.tot;

import cn.bmob.v3.Bmob;

import com.amytech.android.library.base.BaseApplication;
import com.amytech.umeng.analytics.UMengAnalytic;

/**
 * Title: TOT <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月3日 下午3:45:11 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月3日 <br>
 *
 * @author marktlzhai
 */
public class TOTApp extends BaseApplication {

	public static final String UMENG_APPKEY = "55bf216967e58e865100542d";

	public static final String SERVER_APP_ID = "3703a8b6fd29c562415fc91562c91bd5";

	@Override
	public void onCreate() {
		super.onCreate();

		UMengAnalytic.init(this, UMENG_APPKEY, UMengAnalytic.CHANNEL_DEFAULT);

		Bmob.initialize(this, SERVER_APP_ID);
	}
}
