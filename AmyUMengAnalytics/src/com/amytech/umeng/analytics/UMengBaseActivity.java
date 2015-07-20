package com.amytech.umeng.analytics;

import android.support.v4.app.FragmentActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * Title: AmyUMengAnalytics <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月20日 下午4:57:11 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月20日 <br>
 *
 * @author marktlzhai
 */
public class UMengBaseActivity extends FragmentActivity {
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
