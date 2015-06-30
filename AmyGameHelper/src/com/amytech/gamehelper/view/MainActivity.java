package com.amytech.gamehelper.view;

import com.amytech.gamehelper.view.fragment.FragmentHome;
import com.amytech.library.core.framework.BaseActivity;
import com.amytech.library.core.framework.BaseFragment;

public class MainActivity extends BaseActivity {

	@Override
	protected Class<? extends BaseFragment> getHomeFragment() {
		return FragmentHome.class;
	}
}
