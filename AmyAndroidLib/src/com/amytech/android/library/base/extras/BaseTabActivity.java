package com.amytech.android.library.base.extras;

import java.util.List;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.amytech.android.library.R;
import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.CollectionUtils;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:20:39 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseTabActivity extends BaseActivity {

	protected FragmentTabHost fragmentTabHost;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_base_tab;
	}

	@Override
	protected void initViews() {
		fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		List<TabItem> tabItems = loadTabFragment();
		if (CollectionUtils.notEmpty(tabItems)) {
			for (TabItem tabItem : tabItems) {
				TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.tabName).setIndicator(getTabView(layoutInflater, tabItem));
				fragmentTabHost.addTab(tabSpec, tabItem.tabFragment, null);
			}

			TabWidget tabWidget = fragmentTabHost.getTabWidget();
			int childCount = tabWidget.getChildCount();
			if (childCount > 0) {
				for (int i = 0; i < childCount; i++) {
					tabWidget.getChildAt(i).setBackgroundResource(getTabWidgetSelector());
				}
			}
		}
	}

	protected abstract List<TabItem> loadTabFragment();

	protected abstract View getTabView(LayoutInflater layoutInflater, TabItem item);

	protected abstract int getTabWidgetSelector();

	public class TabItem {
		public Class<? extends BaseTabItemFragment> tabFragment;
		public String tabName;
		public int tabIcon;

		public TabItem(Class<? extends BaseTabItemFragment> tabFragment, String tabName, int tabIcon) {
			super();
			this.tabFragment = tabFragment;
			this.tabName = tabName;
			this.tabIcon = tabIcon;
		}

		public TabItem(Class<? extends BaseTabItemFragment> tabFragment, int tabNameRes, int tabIcon) {
			super();
			this.tabFragment = tabFragment;
			this.tabName = getString(tabNameRes);
			this.tabIcon = tabIcon;
		}
	}
}
