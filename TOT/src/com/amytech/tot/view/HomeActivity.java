package com.amytech.tot.view;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.tot.R;
import com.amytech.tot.view.buss.LeftDrawer;
import com.amytech.tot.view.buss.LeftDrawer.DrawerItem;
import com.amytech.tot.view.buss.LeftDrawer.LeftDrawerListener;

/**
 * Title: TOT <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月3日 下午4:05:44 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月3日 <br>
 *
 * @author marktlzhai
 */
public class HomeActivity extends BaseActivity implements LeftDrawerListener {

	private Topbar topbar;

	private DrawerLayout drawer;

	private LeftDrawer leftDrawer;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_home;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.app_name);
		topbar.configLeftImgBtn(TopbarIcon.MENU, new OnClickListener() {
			public void onClick(View v) {
				drawer.openDrawer(Gravity.START);
			}
		});
		topbar.configRightImgBtn(TopbarIcon.ADD, new OnClickListener() {
			public void onClick(View v) {
				startActivity(PublishTOTActivity.class);
			}
		});

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		leftDrawer = (LeftDrawer) findViewById(R.id.left_drawer);
		leftDrawer.setDrawerListener(this);
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(Gravity.START)) {
			drawer.closeDrawer(Gravity.START);
		} else {
			AppUtils.exit(this);
		}
	}

	@Override
	public void onDrawerItemClick(final DrawerItem item) {
		drawer.closeDrawer(Gravity.START);
		handler.postDelayed(new Runnable() {
			public void run() {
				showToast(getString(item.getTxtRes()) + " clicked.");
			}
		}, 500);
	}
}
