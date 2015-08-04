package com.amytech.imagebullet.view.drawer;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amytech.imagebullet.R;

/**
 * Title: ImageBullet <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 下午5:28:15 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
@SuppressLint("InflateParams")
public class LeftDrawer extends LinearLayout {

	public interface LeftDrawerListener {
		void onDrawerItemClick(DrawerItem item);
	}

	public enum DrawerItem {
		// 分隔线
		CATEGORY_LINE(0, 0),
		// 最热排行
		HOT(R.drawable.drawericon_hot, R.string.drawer_hot),
		// 最新排行
		NEWEST(R.drawable.drawericon_new, R.string.drawer_new),
		// 分享该软件
		SHARE_APP(R.drawable.drawericon_share, R.string.drawer_share),
		// 设置
		SETTINGS(R.drawable.drawericon_settings, R.string.drawer_settings),
		// 用户反馈
		FEEDBACK(R.drawable.drawericon_feedback, R.string.drawer_feedback),
		// 关于
		ABOUT(R.drawable.drawericon_about, R.string.drawer_about);

		private int iconRes;
		private int txtRes;

		private DrawerItem(int iconRes, int txtRes) {
			this.iconRes = iconRes;
			this.txtRes = txtRes;
		}

		public int getIconRes() {
			return iconRes;
		}

		public int getTxtRes() {
			return txtRes;
		}
	}

	private ListView drawerList;
	private DrawerAdapter drawerAdapter;
	private LeftDrawerListener listener;

	public LeftDrawer(Context context) {
		super(context);
		init();
	}

	public LeftDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LeftDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void setDrawerListener(LeftDrawerListener listener) {
		this.listener = listener;
	}

	private void init() {
		View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_drawer, null);
		addView(rootView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		drawerList = (ListView) rootView.findViewById(R.id.drawer_list);

		initDrawerList();
	}

	private void initDrawerList() {
		drawerAdapter = new DrawerAdapter();
		drawerList.setAdapter(drawerAdapter);
		drawerList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DrawerItem item = (DrawerItem) parent.getItemAtPosition(position);
				if (listener != null) {
					listener.onDrawerItemClick(item);
				}
			}
		});
	}

	@SuppressLint("ViewHolder")
	private class DrawerAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;

		private List<DrawerItem> itemList;

		private static final int TYPE_LINE = 0;
		private static final int TYPE_ITEM = 1;

		private DrawerAdapter() {
			layoutInflater = LayoutInflater.from(getContext());
			itemList = new ArrayList<DrawerItem>();
			itemList.add(DrawerItem.HOT);
			itemList.add(DrawerItem.NEWEST);
			itemList.add(DrawerItem.CATEGORY_LINE);
			itemList.add(DrawerItem.SHARE_APP);
			itemList.add(DrawerItem.SETTINGS);
			itemList.add(DrawerItem.FEEDBACK);
			itemList.add(DrawerItem.ABOUT);
		}

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public DrawerItem getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final DrawerItem item = getItem(position);
			int itemType = getItemViewType(position);

			switch (itemType) {
			case TYPE_LINE:
				convertView = layoutInflater.inflate(R.layout.item_empty_line, parent, false);
				break;
			case TYPE_ITEM:
				convertView = layoutInflater.inflate(R.layout.item_drawer, parent, false);

				ImageView itemIcon = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
				itemIcon.setImageResource(item.getIconRes());
				TextView itemName = (TextView) convertView.findViewById(R.id.drawer_item_name);
				itemName.setText(item.getTxtRes());
				break;
			}

			return convertView;
		}

		@Override
		public int getItemViewType(int position) {
			DrawerItem item = getItem(position);
			if (item == DrawerItem.CATEGORY_LINE) {
				return TYPE_LINE;
			}
			return TYPE_ITEM;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}
	}
}
