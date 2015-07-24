package com.amytech.randomlooking.view;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.amytech.android.library.base.extras.BaseTabActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.randomlooking.R;
import com.amytech.randomlooking.view.fragment.FragmentHuabian;
import com.amytech.randomlooking.view.fragment.FragmentGirl;
import com.amytech.randomlooking.view.fragment.FragmentJoy;
import com.amytech.randomlooking.view.fragment.FragmentSocial;
import com.amytech.randomlooking.view.fragment.FragmentWX;
import com.amytech.umeng.analytics.UMengAnalytic;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午4:18:18 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
@SuppressLint("InflateParams")
public class HomeActivity extends BaseTabActivity {

	@Override
	protected List<TabItem> loadTabFragment() {
		List<TabItem> tabList = new ArrayList<TabItem>();
		// 美女图片
		tabList.add(new TabItem(FragmentGirl.class, R.string.tab_girl, android.R.color.transparent));
		// 微信精选
		tabList.add(new TabItem(FragmentWX.class, R.string.tab_weixin, android.R.color.transparent));
		// 娱乐花边
		tabList.add(new TabItem(FragmentJoy.class, R.string.tab_qiwen, android.R.color.transparent));
		// 奇闻趣事
		tabList.add(new TabItem(FragmentHuabian.class, R.string.tab_huabian, android.R.color.transparent));
		// 社会新闻
		tabList.add(new TabItem(FragmentSocial.class, R.string.tab_social, android.R.color.transparent));

		return tabList;
	}

	@Override
	protected View getTabView(LayoutInflater layoutInflater, TabItem item) {
		View rootView = layoutInflater.inflate(R.layout.view_tab_widget, null, false);

		TextView tabName = (TextView) rootView.findViewById(R.id.tab_name);

		tabName.setText(item.tabName);

		return rootView;
	}

	@Override
	protected int getTabWidgetSelector() {
		return R.drawable.selector_tab_background;
	}

	@Override
	public void onBackPressed() {
		UMengAnalytic.onKillProcess(this);
		AppUtils.exit(this);
	}
}
