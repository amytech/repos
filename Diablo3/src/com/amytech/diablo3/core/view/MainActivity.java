package com.amytech.diablo3.core.view;

import com.amytech.diablo3.core.view.fragment.FragmentHome;
import com.amytech.library.core.framework.BaseActivity;
import com.amytech.library.core.framework.BaseFragment;

/**
 * 主Activity，Fragment容器
 * 
 * @author AmyTech
 */
public class MainActivity extends BaseActivity {

	@Override
	protected Class<? extends BaseFragment> getHomeFragment() {
		return FragmentHome.class;
	}
}
