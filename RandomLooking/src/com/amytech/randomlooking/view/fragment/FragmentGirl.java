package com.amytech.randomlooking.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.utils.ImageUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.R;
import com.amytech.randomlooking.manager.GirlManager;
import com.amytech.randomlooking.manager.GirlManager.GirlGetCallback;
import com.amytech.randomlooking.model.GirlModel;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

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
public class FragmentGirl extends BaseTabItemFragment implements GirlGetCallback {

	private Topbar topbar;

	private TextView girlLoadingView;

	private PullToRefreshListView girlList;

	private GirlAdapter girlAdapter;

	private List<GirlModel> girlData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_girl;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_girl);
		topbar.configRightImgBtn(TopbarIcon.SHARE, new OnClickListener() {
			public void onClick(View v) {

			}
		});

		girlLoadingView = (TextView) findViewById(R.id.reload_text);
		girlList = (PullToRefreshListView) findViewById(R.id.girl_list);
		girlList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		girlList.getRefreshableView().setDividerHeight(1);

		girlAdapter = new GirlAdapter();
		girlList.getRefreshableView().setAdapter(girlAdapter);

		loadData();
	}

	private void loadData() {
		girlLoadingView.setText(R.string.waitting);
		girlLoadingView.setOnClickListener(null);
		GirlManager.getInstance().load(10, this);
	}

	@Override
	public void girlGetSuccess(List<GirlModel> result) {
		girlLoadingView.setVisibility(View.GONE);
		girlAdapter.setData(result);
	}

	@Override
	public void girlGetFailure() {
		girlAdapter.clear();
		girlLoadingView.setVisibility(View.VISIBLE);
		girlLoadingView.setText(R.string.loading_error_click);
		girlLoadingView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				loadData();
			}
		});
	}

	class GirlAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public GirlAdapter() {
			girlData = new ArrayList<GirlModel>();
			inflater = LayoutInflater.from(App.getInstance());
		}

		public void clear() {
			girlData.clear();
			notifyDataSetChanged();
		}

		public void setData(List<GirlModel> data) {
			girlData.clear();
			girlData.addAll(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return girlData.size();
		}

		@Override
		public GirlModel getItem(int position) {
			return girlData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			GirlModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_girl, parent, false);
				holder = new ViewHolder();

				holder.girlImage = (ImageView) convertView.findViewById(R.id.girl_pic);
				holder.girlTitle = (TextView) convertView.findViewById(R.id.girl_title);
				holder.girlDesc = (TextView) convertView.findViewById(R.id.girl_desc);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.girlTitle.setText(Html.fromHtml(item.title));
			holder.girlDesc.setText(item.description);
			ImageUtils.displayImage(holder.girlImage, item.picUrl, ImageUtils.getDefaultDisplayOptions());

			return convertView;
		}
	}

	class ViewHolder {
		public ImageView girlImage;
		public TextView girlTitle;
		public TextView girlDesc;
	}
}
