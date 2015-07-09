package com.amytech.android.library.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amytech.android.library.R;

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

	public enum TopbarIcon {
		// 无
		NONE(0),
		// 返回
		BACK(R.drawable.icon_back),
		// 设置
		SETTINGS(R.drawable.icon_settings),
		// 分享
		SHARE(R.drawable.icon_share),
		// 添加
		ADD(R.drawable.icon_add),
		// 菜单
		MENU(R.drawable.icon_menu);

		private int selector;

		private TopbarIcon(int selector) {
			this.selector = selector;
		}

		public int getSelector() {
			return selector;
		}
	}

	private TextView titleView;

	private View leftButtonLayout;
	private ImageView leftButton;

	private View rightButtonLayout;
	private ImageView rightButton;

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

		leftButtonLayout = rootView.findViewById(R.id.topbar_imgbtn_left_layout);
		leftButton = (ImageView) rootView.findViewById(R.id.topbar_imgbtn_left);
		leftButtonLayout.setVisibility(View.GONE);

		rightButtonLayout = rootView.findViewById(R.id.topbar_imgbtn_right_layout);
		rightButton = (ImageView) rootView.findViewById(R.id.topbar_imgbtn_right);
		rightButtonLayout.setVisibility(View.GONE);

		addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	public void setTitle(int titleRes) {
		titleView.setText(titleRes);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	/**
	 * 配置左侧图片按钮
	 */
	public void configLeftImgBtn(TopbarIcon imgIcon, OnClickListener listener) {
		if (imgIcon == null || imgIcon.getSelector() == 0) {
			leftButtonLayout.setVisibility(View.GONE);
		} else {
			leftButtonLayout.setVisibility(View.VISIBLE);
			leftButton.setImageResource(imgIcon.getSelector());
			leftButton.setOnClickListener(listener);
		}
	}

	/**
	 * 配置右侧图片按钮
	 */
	public void configRightImgBtn(TopbarIcon imgIcon, OnClickListener listener) {
		if (imgIcon == null || imgIcon.getSelector() == 0) {
			rightButtonLayout.setVisibility(View.GONE);
		} else {
			rightButtonLayout.setVisibility(View.VISIBLE);
			rightButton.setImageResource(imgIcon.getSelector());
			rightButton.setOnClickListener(listener);
		}
	}
}
