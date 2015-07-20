package com.amytech.umeng.analytics;

import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Title: AmyUMengAnalytics <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月20日 下午4:56:24 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月20日 <br>
 *
 * @author marktlzhai
 */
public class UMengBaseFragment extends Fragment {
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().getSimpleName());
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().getSimpleName());
	}
}
