package com.amytech.imagebullet;

import cn.bmob.v3.Bmob;

import com.amytech.android.library.base.BaseApplication;
import com.amytech.umeng.analytics.UMengAnalytic;

/**
 * Title: ImageBullet <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 下午3:11:17 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
public class App extends BaseApplication {

	public static final String IMAGE_TOKEN = "7f4fc67f5bad806e91d6a4478171d388b07ac32e:alRxQ25RRXJJbzVQNzJYZEJDbTRWMEk0OFcwPQ==:eyJkZWFkbGluZSI6MTQzODY1ODk4MiwiYWN0aW9uIjoiZ2V0IiwidWlkIjoiNTEzMTUxIiwiYWlkIjoiMTA3MDczMiJ9";

	public static final String UMENG_APPKEY = "55bf216967e58e865100542d";

	public static final String SERVER_APP_ID = "3703a8b6fd29c562415fc91562c91bd5";

	@Override
	public void onCreate() {
		super.onCreate();

		UMengAnalytic.init(this, UMENG_APPKEY, UMengAnalytic.CHANNEL_DEFAULT);

		Bmob.initialize(this, SERVER_APP_ID);
	}
}
