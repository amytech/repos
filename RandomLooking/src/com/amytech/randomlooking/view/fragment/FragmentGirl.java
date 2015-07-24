package com.amytech.randomlooking.view.fragment;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.share.AmyQQ;
import com.amytech.android.library.share.AmyWX;
import com.amytech.android.library.share.view.PickShareDialog;
import com.amytech.android.library.share.view.PickShareDialog.ShareCallback;
import com.amytech.android.library.utils.ImageUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.R;
import com.amytech.randomlooking.manager.GirlManager;
import com.amytech.randomlooking.manager.GirlManager.GirlGetCallback;
import com.amytech.randomlooking.model.GirlModel;
import com.amytech.randomlooking.view.WebviewActivity;
import com.amytech.umeng.analytics.UMengAnalytic;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
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

	private static final int LOAD_COUNT = 30;

	private Topbar topbar;

	private TextView girlLoadingView;

	private PullToRefreshListView girlList;

	private GirlAdapter girlAdapter;

	private List<GirlModel> girlData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_data;
	}

	@Override
	public void onResume() {
		super.onResume();
		UMengAnalytic.onEvent(App.getInstance(), App.UMeng.EVENT_TAB_GIRL);
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_girl);

		girlLoadingView = (TextView) findViewById(R.id.reload_text);
		girlList = (PullToRefreshListView) findViewById(R.id.data_list);
		girlList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		girlList.getRefreshableView().setDividerHeight(1);
		girlList.setMode(Mode.PULL_FROM_START);
		girlList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				girlAdapter.clear();
				loadData();
			}
		});

		girlAdapter = new GirlAdapter();
		girlList.getRefreshableView().setAdapter(girlAdapter);
		girlList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final GirlModel item = (GirlModel) parent.getItemAtPosition(position);
				Intent i = new Intent(getActivity(), WebviewActivity.class);
				i.putExtra(WebviewActivity.DATA_ITEM, item);
				startActivity(i);
			}
		});

		loadData();
	}

	private void loadData() {
		girlLoadingView.setText(R.string.waitting);
		girlLoadingView.setOnClickListener(null);
		GirlManager.getInstance().load(LOAD_COUNT, this);
	}

	@Override
	public void girlGetSuccess(List<GirlModel> result) {
		girlLoadingView.setVisibility(View.GONE);
		girlAdapter.setData(result);

		girlList.onRefreshComplete();
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
		girlList.onRefreshComplete();
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

			final GirlModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_single, parent, false);
				holder = new ViewHolder();

				holder.girlImage = (ImageView) convertView.findViewById(R.id.item_single_pic);
				holder.girlTitle = (TextView) convertView.findViewById(R.id.item_single_title);
				holder.girlShare = convertView.findViewById(R.id.share_layout);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.girlTitle.setText(Html.fromHtml(item.getTitle()));
			ImageUtils.displayImage(App.getInstance(), holder.girlImage, item.getImageURL(), R.dimen.list_item_image_size, R.dimen.list_item_image_size);
			holder.girlShare.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					PickShareDialog shareDialog = new PickShareDialog(getActivity(), App.WX_APPID, App.QQ_APPID, new ShareCallback() {
						@Override
						public void shareqq() {
							AmyQQ.getInstance(App.QQ_APPID).share(getActivity(), getString(R.string.app_share_title), item.getTitle(), item.getTargetURL(),
									item.getImageURL(), getString(R.string.app_name));
						}

						@Override
						public void sharewx() {
							AmyWX.getInstance(App.WX_APPID).share2WX(getString(R.string.app_share_title), item.getTitle(), item.getTargetURL(), false,
									BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo));
						}

						@Override
						public void sharewxf() {
							AmyWX.getInstance(App.WX_APPID).share2WX(getString(R.string.app_share_title), item.getTitle(), item.getTargetURL(), true,
									BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo));
						}

						@Override
						public void sharesms() {
							Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
							intent.putExtra("sms_body", MessageFormat.format(getString(R.string.app_share_sms), item.getTargetURL()));
							startActivity(intent);
						}

						@Override
						public void shareCancel() {

						}
					});
					shareDialog.show();
				}
			});

			return convertView;
		}
	}

	class ViewHolder {
		public ImageView girlImage;
		public TextView girlTitle;
		public View girlShare;
	}
}
