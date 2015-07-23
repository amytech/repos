package com.amytech.randomlooking.view.fragment;

import android.widget.TextView;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.randomlooking.R;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午5:24:06 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class FragmentJoy extends BaseTabItemFragment {

	@Override
	protected int getLayoutID() {
		return R.layout.layout_test;
	}

	@Override
	protected void initViews() {
		TextView test = (TextView) findViewById(R.id.layout_test_tv);
		test.setText(R.string.tab_joy);
	}
}
