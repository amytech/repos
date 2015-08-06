package com.amytech.android.library.base.extras;

import java.util.List;

import android.view.MotionEvent;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.CollectionUtils;
import com.amytech.android.library.views.menu.ResideMenu;
import com.amytech.android.library.views.menu.ResideMenu.OnMenuListener;
import com.amytech.android.library.views.menu.ResideMenuItem;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月6日 上午11:02:34 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月6日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseMenuActivity extends BaseActivity implements OnMenuListener {

	protected abstract int getMenuBackground();

	protected abstract List<ResideMenuItem> getLeftMenuItems();

	protected abstract List<ResideMenuItem> getRightMenuItems();

	protected ResideMenu resideMenu;

	@Override
	protected void onResume() {
		super.onResume();

		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(getMenuBackground());
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(this);
		resideMenu.setScaleValue(0.6f);

		List<ResideMenuItem> leftMenuItems = getLeftMenuItems();
		if (CollectionUtils.notEmpty(leftMenuItems)) {
			resideMenu.setMenuItems(leftMenuItems, ResideMenu.DIRECTION_LEFT);
		}

		List<ResideMenuItem> rightMenuItems = getRightMenuItems();
		if (CollectionUtils.notEmpty(rightMenuItems)) {
			resideMenu.setMenuItems(rightMenuItems, ResideMenu.DIRECTION_RIGHT);
		}
	}

	protected void openLeftMenu() {
		List<ResideMenuItem> menuItems = resideMenu.getMenuItems(ResideMenu.DIRECTION_LEFT);
		if (CollectionUtils.notEmpty(menuItems)) {
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
		}
	}

	protected void openRightMenu() {
		List<ResideMenuItem> menuItems = resideMenu.getMenuItems(ResideMenu.DIRECTION_RIGHT);
		if (CollectionUtils.notEmpty(menuItems)) {
			resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}
}
