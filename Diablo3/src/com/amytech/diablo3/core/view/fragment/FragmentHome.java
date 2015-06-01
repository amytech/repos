package com.amytech.diablo3.core.view.fragment;

import android.view.View;
import android.view.View.OnClickListener;

import com.amytech.diablo3.R;
import com.amytech.library.core.framework.BaseFragment;
import com.amytech.library.core.view.Topbar;

public class FragmentHome extends BaseFragment {

	private Topbar topbar;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_home;
	}

	@Override
	protected void showToUser() {
		initTopbar();
	}

	private void initTopbar() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.configIcon(R.dimen.margin_small_more, R.drawable.ic_launcher, new OnClickListener() {
			public void onClick(View v) {

			}
		});
		topbar.configTitle(R.string.app_name);
	}
}
