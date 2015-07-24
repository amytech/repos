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
import com.amytech.randomlooking.manager.SocialManager;
import com.amytech.randomlooking.manager.SocialManager.SocialCallback;
import com.amytech.randomlooking.model.SocialModel;
import com.amytech.randomlooking.view.WebviewActivity;
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
public class FragmentSocial extends BaseTabItemFragment implements SocialCallback {

	private static final int LOAD_COUNT = 50;

	private Topbar topbar;

	private TextView socialLoadingView;

	private PullToRefreshListView socialList;

	private socialAdapter socialAdapter;

	private List<SocialModel> socialData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_data;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_social);

		socialLoadingView = (TextView) findViewById(R.id.reload_text);
		socialList = (PullToRefreshListView) findViewById(R.id.data_list);
		socialList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		socialList.getRefreshableView().setDividerHeight(1);
		socialList.setMode(Mode.PULL_FROM_START);
		socialList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				socialAdapter.clear();
				loadData();
			}
		});

		socialAdapter = new socialAdapter();
		socialList.getRefreshableView().setAdapter(socialAdapter);
		socialList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final SocialModel item = (SocialModel) parent.getItemAtPosition(position);
				Intent i = new Intent(getActivity(), WebviewActivity.class);
				i.putExtra(WebviewActivity.DATA_ITEM, item);
				startActivity(i);
			}
		});

		loadData();
	}

	private void loadData() {
		socialLoadingView.setText(R.string.waitting);
		socialLoadingView.setOnClickListener(null);
		SocialManager.getInstance().load(LOAD_COUNT, this);
	}

	class socialAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public socialAdapter() {
			socialData = new ArrayList<SocialModel>();
			inflater = LayoutInflater.from(App.getInstance());
		}

		public void clear() {
			socialData.clear();
			notifyDataSetChanged();
		}

		public void setData(List<SocialModel> data) {
			socialData.clear();
			socialData.addAll(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return socialData.size();
		}

		@Override
		public SocialModel getItem(int position) {
			return socialData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final SocialModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_double, parent, false);
				holder = new ViewHolder();

				holder.socialImage = (ImageView) convertView.findViewById(R.id.item_double_pic);
				holder.socialTitle = (TextView) convertView.findViewById(R.id.item_double_title);
				holder.socialDate = (TextView) convertView.findViewById(R.id.item_double_subtitle);
				holder.socialShare = convertView.findViewById(R.id.share_layout);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.socialTitle.setText(Html.fromHtml(item.getTitle()));
			holder.socialDate.setText(Html.fromHtml(item.getSummary()));
			ImageUtils.displayImage(App.getInstance(), holder.socialImage, item.getImageURL(), R.dimen.list_item_image_size, R.dimen.list_item_image_size);
			holder.socialShare.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					PickShareDialog shareDialog = new PickShareDialog(getActivity(), App.WX_APPID, App.QQ_APPID, new ShareCallback() {
						@Override
						public void shareqq() {
							AmyQQ.getInstance(App.QQ_APPID).shareImage(getActivity(), item.getImageURL(), item.getTargetURL(), getString(R.string.app_name));
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
		public ImageView socialImage;
		public TextView socialTitle;
		public TextView socialDate;
		public View socialShare;
	}

	@Override
	public void socialGetSuccess(List<SocialModel> result) {
		socialLoadingView.setVisibility(View.GONE);
		socialAdapter.setData(result);

		socialList.onRefreshComplete();
	}

	@Override
	public void socialGetFailure() {
		socialAdapter.clear();
		socialLoadingView.setVisibility(View.VISIBLE);
		socialLoadingView.setText(R.string.loading_error_click);
		socialLoadingView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				loadData();
			}
		});
		socialList.onRefreshComplete();
	}
}
