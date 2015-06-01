package com.amytech.library.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amytech.library.R;

public class Topbar extends RelativeLayout {

	private ImageView topbarIcon;

	private TextView topbarTitle;

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

		topbarIcon = (ImageView) rootView.findViewById(R.id.topbar_icon);
		topbarTitle = (TextView) rootView.findViewById(R.id.topbar_title);

		addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	public void configIcon(int imageRes, OnClickListener listener) {
		topbarIcon.setImageResource(imageRes);
		topbarIcon.setOnClickListener(listener);
	}

	public void configIcon(int paddingRes, int imageRes, OnClickListener listener) {
		int padding = getResources().getDimensionPixelSize(paddingRes);
		topbarIcon.setPadding(padding, padding, padding, padding);
		topbarIcon.setImageResource(imageRes);
		topbarIcon.setOnClickListener(listener);
	}

	public void configTitle(int titleRes) {
		topbarTitle.setText(titleRes);
	}
}
