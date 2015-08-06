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
		BACK(R.drawable.topbar_back),
		// 设置
		SETTINGS(R.drawable.topbar_setting),
		// 分享
		SHARE(R.drawable.topbar_share),
		// 添加
		ADD(R.drawable.topbar_add),
		// 菜单
		MENU(R.drawable.topbar_more),
		// 相机
		CAMERA(R.drawable.topbar_camera),
		// 关闭
		CLOSE(R.drawable.topbar_close),
		// 定位
		LOCATION(R.drawable.topbar_location),
		// 喜欢
		LOVE(R.drawable.topbar_love),
		// ok
		OK(R.drawable.topbar_ok),
		// RSS
		RSS(R.drawable.topbar_rss),
		// 放大
		ZOOMIN(R.drawable.topbar_zoomin),
		// 缩小
		ZOOMOUT(R.drawable.topbar_zoomout);

		private int selector;

		private TopbarIcon(int selector) {
			this.selector = selector;
		}

		public int getSelector() {
			return selector;
		}
	}

	private TextView titleView;

	private ImageView leftButton;

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

		leftButton = (ImageView) rootView.findViewById(R.id.topbar_imgbtn_left);
		leftButton.setVisibility(View.GONE);

		rightButton = (ImageView) rootView.findViewById(R.id.topbar_imgbtn_right);
		rightButton.setVisibility(View.GONE);

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
			leftButton.setVisibility(View.GONE);
		} else {
			leftButton.setVisibility(View.VISIBLE);
			leftButton.setImageResource(imgIcon.getSelector());
			leftButton.setOnClickListener(listener);
		}
	}

	/**
	 * 配置右侧图片按钮
	 */
	public void configRightImgBtn(TopbarIcon imgIcon, OnClickListener listener) {
		if (imgIcon == null || imgIcon.getSelector() == 0) {
			rightButton.setVisibility(View.GONE);
		} else {
			rightButton.setVisibility(View.VISIBLE);
			rightButton.setImageResource(imgIcon.getSelector());
			rightButton.setOnClickListener(listener);
		}
	}

	public ImageView getLeftButton() {
		return leftButton;
	}

	public ImageView getRightButton() {
		return rightButton;
	}
}
