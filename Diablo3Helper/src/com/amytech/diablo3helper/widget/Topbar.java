package com.amytech.diablo3helper.widget;

import com.amytech.diablo3helper.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月8日 下午2:32:39 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月8日 <br>
 *
 * @author marktlzhai
 */
public class Topbar extends RelativeLayout {

	private TextView titleView;

	public Topbar(Context context) {
		super(context);
		initView();
	}

	public Topbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public Topbar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@SuppressLint("InflateParams")
	private void initView() {
		View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_topbar, null, false);

		titleView = (TextView) rootView.findViewById(R.id.topbar_title);

		addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	public void setTitle(int titleRes) {
		titleView.setText(titleRes);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}
}
