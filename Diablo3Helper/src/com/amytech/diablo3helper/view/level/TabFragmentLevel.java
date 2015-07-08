package com.amytech.diablo3helper.view.level;

import android.widget.Toast;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.diablo3helper.R;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:51:53 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class TabFragmentLevel extends BaseTabItemFragment {

	@Override
	protected void showToUser() {

	}

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(getActivity(), "TabFragmentLevel -> onResume", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected int getLayoutID() {
		return R.layout.fragment_level;
	}

	@Override
	protected void initViews() {

	}
}
