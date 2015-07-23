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
	@Override
	public void onCreate() {
		super.onCreate();
		UMengAnalytic.init(this, "55b056ef67e58eb80500705b", UMengAnalytic.CHANNEL_DEFAULT);
	}
}
