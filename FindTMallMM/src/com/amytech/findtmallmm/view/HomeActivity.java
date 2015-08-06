package com.amytech.findtmallmm.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.amytech.android.library.api.APIList;
import com.amytech.android.library.api.APIManager;
import com.amytech.android.library.api.APIManager.APIListener;
import com.amytech.android.library.api.BaseAPIRequest;
import com.amytech.android.library.api.BaseAPIResponse;
import com.amytech.android.library.base.extras.BaseMenuActivity;
import com.amytech.android.library.utils.CollectionUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.android.library.views.menu.ResideMenuItem;
import com.amytech.findtmallmm.App;
import com.amytech.findtmallmm.R;
import com.amytech.findtmallmm.controller.MMStyleManager;
import com.amytech.findtmallmm.model.MM;
import com.amytech.findtmallmm.model.MMResponse;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月6日 上午10:15:58 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月6日 <br>
 *
 * @author marktlzhai
 */
public class HomeActivity extends BaseMenuActivity implements APIListener {

	private Topbar topbar;

	private GridView mmGridview;

	private MMAdapter mmAdapter;

	private MMResponse mmData = new MMResponse();

	private String currentStyle = "";

	@Override
	public void openMenu() {

	}

	@Override
	public void closeMenu() {

	}

	@Override
	protected int getMenuBackground() {
		return R.drawable.splash_bg;
	}

	@Override
	protected List<ResideMenuItem> getLeftMenuItems() {
		List<ResideMenuItem> leftMenus = new ArrayList<ResideMenuItem>();

		Set<String> styles = MMStyleManager.loadStyleList(spUtils);
		if (CollectionUtils.notEmpty(styles)) {
			for (final String style : styles) {
				ResideMenuItem resideMenuItem = new ResideMenuItem(this, android.R.color.transparent, style);
				resideMenuItem.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						currentStyle = style;
						resideMenu.closeMenu();
						loadMM();
					}
				});
				leftMenus.add(resideMenuItem);
			}
		}

		return leftMenus;
	}

	@Override
	protected List<ResideMenuItem> getRightMenuItems() {
		return null;
	}

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
				openLeftMenu();
			}
		});

		topbar.configRightImgBtn(TopbarIcon.SETTINGS, new OnClickListener() {
			public void onClick(View v) {
				openRightMenu();
			}
		});

		mmGridview = (GridView) findViewById(R.id.home_gridview);
		mmAdapter = new MMAdapter();
		mmGridview.setAdapter(mmAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		resideMenu.addIgnoredView(findViewById(R.id.home_layout));

		loadMM();
	}

	private void loadMM() {
		mmAdapter.clear();

		@SuppressWarnings("serial")
		BaseAPIRequest request = new BaseAPIRequest(App.API_ID, App.API_SECRET) {
			@Override
			public Map<String, String> getCustomParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("type", currentStyle);
				params.put("page", String.valueOf(mmData.currentPage));
				return params;
			}
		};
		APIManager.getInstance(this).sendRequest(APIList.TMallGirl.LIST, request, this);
	}

	class MMAdapter extends BaseAdapter {

		public void clear() {
			mmData.mmList.clear();
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mmData.mmList.size();
		}

		@Override
		public MM getItem(int position) {
			return mmData.mmList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final MM item = getItem(position);
			TextView tv = new TextView(HomeActivity.this);
			tv.setText(item.realName);
			return tv;
		}
	}

	@Override
	public void onAPISuccess(BaseAPIResponse response) {
		mmData.setResponse(response);
		mmAdapter.notifyDataSetChanged();
	}

	@Override
	public void onAPIFailure() {
		showToast(R.string.load_mm_error);
	}
}
