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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.share.AmyQQ;
import com.amytech.android.library.share.AmyWX;
import com.amytech.android.library.share.view.PickShareDialog;
import com.amytech.android.library.share.view.PickShareDialog.ShareCallback;
import com.amytech.android.library.utils.ImageUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.R;
import com.amytech.randomlooking.manager.QiwenManager;
import com.amytech.randomlooking.manager.QiwenManager.QiwenGetCallback;
import com.amytech.randomlooking.model.QiwenModel;
import com.amytech.randomlooking.view.WebviewActivity;
import com.amytech.umeng.analytics.UMengAnalytic;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

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
public class FragmentQiwen extends BaseTabItemFragment implements QiwenGetCallback {

	private static final int LOAD_COUNT = 30;

	private Topbar topbar;

	private TextView qiwenLoadingView;

	private PullToRefreshListView qiwenList;

	private qiwenAdapter qiwenAdapter;

	private List<QiwenModel> qiwenData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_data;
	}

	@Override
	public void onResume() {
		super.onResume();
		UMengAnalytic.onEvent(App.getInstance(), App.UMeng.EVENT_TAB_QIWEN);
	}
	
	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_qiwen);

		qiwenLoadingView = (TextView) findViewById(R.id.reload_text);
		qiwenList = (PullToRefreshListView) findViewById(R.id.data_list);
		qiwenList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		qiwenList.getRefreshableView().setDividerHeight(1);
		qiwenList.setMode(Mode.PULL_FROM_START);
		qiwenList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				qiwenAdapter.clear();
				loadData();
			}
		});

		qiwenAdapter = new qiwenAdapter();
		qiwenList.getRefreshableView().setAdapter(qiwenAdapter);
		qiwenList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final QiwenModel item = (QiwenModel) parent.getItemAtPosition(position);
				Intent i = new Intent(getActivity(), WebviewActivity.class);
				i.putExtra(WebviewActivity.DATA_ITEM, item);
				startActivity(i);
			}
		});

		loadData();
	}

	private void loadData() {
		qiwenLoadingView.setText(R.string.waitting);
		qiwenLoadingView.setOnClickListener(null);
		QiwenManager.getInstance().load(LOAD_COUNT, this);
	}

	@Override
	public void qiwenGetSuccess(List<QiwenModel> result) {
		qiwenLoadingView.setVisibility(View.GONE);
		qiwenAdapter.setData(result);

		qiwenList.onRefreshComplete();
	}

	@Override
	public void qiwenGetFailure() {
		qiwenAdapter.clear();
		qiwenLoadingView.setVisibility(View.VISIBLE);
		qiwenLoadingView.setText(R.string.loading_error_click);
		qiwenLoadingView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				loadData();
			}
		});
		qiwenList.onRefreshComplete();
	}

	class qiwenAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public qiwenAdapter() {
			qiwenData = new ArrayList<QiwenModel>();
			inflater = LayoutInflater.from(App.getInstance());
		}

		public void clear() {
			qiwenData.clear();
			notifyDataSetChanged();
		}

		public void setData(List<QiwenModel> data) {
			qiwenData.clear();
			qiwenData.addAll(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return qiwenData.size();
		}

		@Override
		public QiwenModel getItem(int position) {
			return qiwenData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final QiwenModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_single, parent, false);
				holder = new ViewHolder();

				holder.qiwenImage = (ImageView) convertView.findViewById(R.id.item_single_pic);
				holder.qiwenTitle = (TextView) convertView.findViewById(R.id.item_single_title);
				holder.qiwenShare = convertView.findViewById(R.id.share_layout);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.qiwenTitle.setText(Html.fromHtml(item.getTitle()));
			ImageUtils.displayImage(App.getInstance(), holder.qiwenImage, item.getImageURL(), R.dimen.list_item_image_size, R.dimen.list_item_image_size);
			holder.qiwenShare.setOnClickListener(new OnClickListener() {
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
		public ImageView qiwenImage;
		public TextView qiwenTitle;
		public View qiwenShare;
	}
}
