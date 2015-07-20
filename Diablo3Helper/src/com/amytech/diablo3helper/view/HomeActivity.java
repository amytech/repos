package com.amytech.diablo3helper.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amytech.android.library.base.extras.BaseTabActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.view.info.TabFragmentInfo;
import com.amytech.diablo3helper.view.items.TabFragmentItems;
import com.amytech.diablo3helper.view.level.TabFragmentLevel;
import com.amytech.diablo3helper.view.skill.TabFragmentSkill;
import com.amytech.diablo3helper.view.system.TabFragmentMore;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月6日 下午5:51:57 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月6日 <br>
 *
 * @author marktlzhai
 */
public class HomeActivity extends BaseTabActivity {

	@Override
	protected List<TabItem> loadTabFragment() {
		List<TabItem> tabList = new ArrayList<TabItem>();

		// 资讯
		tabList.add(new TabItem(TabFragmentInfo.class, R.string.tab_info, R.drawable.tab_icon_info));
		// 技能
		tabList.add(new TabItem(TabFragmentSkill.class, R.string.tab_skill, R.drawable.tab_icon_skill));
		// 装备
		tabList.add(new TabItem(TabFragmentItems.class, R.string.tab_items, R.drawable.tab_icon_items));
		// 天梯
		tabList.add(new TabItem(TabFragmentLevel.class, R.string.tab_level, R.drawable.tab_icon_level));
		// 更多
		tabList.add(new TabItem(TabFragmentMore.class, R.string.tab_more, R.drawable.tab_icon_more));

		return tabList;
	}

	@SuppressLint("InflateParams")
	@Override
	protected View getTabView(LayoutInflater layoutInflater, final TabItem item) {
		View rootView = layoutInflater.inflate(R.layout.view_tab_widget, null, false);

		ImageView tabIcon = (ImageView) rootView.findViewById(R.id.tab_icon);
		TextView tabName = (TextView) rootView.findViewById(R.id.tab_name);

		tabIcon.setImageResource(item.tabIcon);
		tabName.setText(item.tabName);

		return rootView;
	}

	@Override
	protected int getTabWidgetSelector() {
		return R.drawable.selector_tab_background;
	}

	@Override
	public void onBackPressed() {
		AppUtils.exit(this);
	}
}
