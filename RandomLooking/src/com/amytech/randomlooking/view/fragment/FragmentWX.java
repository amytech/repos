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
import com.amytech.randomlooking.manager.WXManager;
import com.amytech.randomlooking.manager.WXManager.WXGetCallback;
import com.amytech.randomlooking.model.WXModel;
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
public class FragmentWX extends BaseTabItemFragment implements WXGetCallback {

	private static final int LOAD_COUNT = 30;

	private Topbar topbar;

	private TextView wxLoadingView;

	private PullToRefreshListView wxList;

	private wxAdapter wxAdapter;

	private List<WXModel> wxData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_data;
	}

	@Override
	public void onResume() {
		super.onResume();
		UMengAnalytic.onEvent(App.getInstance(), App.UMeng.EVENT_TAB_WX);
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_weixin);

		wxLoadingView = (TextView) findViewById(R.id.reload_text);
		wxList = (PullToRefreshListView) findViewById(R.id.data_list);
		wxList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		wxList.getRefreshableView().setDividerHeight(1);
		wxList.setMode(Mode.PULL_FROM_START);
		wxList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				wxAdapter.clear();
				loadData();
			}
		});

		wxAdapter = new wxAdapter();
		wxList.getRefreshableView().setAdapter(wxAdapter);
		wxList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final WXModel item = (WXModel) parent.getItemAtPosition(position);
				Intent i = new Intent(getActivity(), WebviewActivity.class);
				i.putExtra(WebviewActivity.DATA_ITEM, item);
				startActivity(i);
			}
		});

		loadData();
	}

	private void loadData() {
		wxLoadingView.setText(R.string.waitting);
		wxLoadingView.setOnClickListener(null);
		WXManager.getInstance().load(LOAD_COUNT, this);
	}

	class wxAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public wxAdapter() {
			wxData = new ArrayList<WXModel>();
			inflater = LayoutInflater.from(App.getInstance());
		}

		public void clear() {
			wxData.clear();
			notifyDataSetChanged();
		}

		public void setData(List<WXModel> data) {
			wxData.clear();
			wxData.addAll(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return wxData.size();
		}

		@Override
		public WXModel getItem(int position) {
			return wxData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final WXModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_double, parent, false);
				holder = new ViewHolder();

				holder.wxImage = (ImageView) convertView.findViewById(R.id.item_double_pic);
				holder.wxTitle = (TextView) convertView.findViewById(R.id.item_double_title);
				holder.wxfrom = (TextView) convertView.findViewById(R.id.item_double_subtitle);
				holder.wxShare = convertView.findViewById(R.id.share_layout);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.wxTitle.setText(Html.fromHtml(item.getTitle()));
			holder.wxfrom.setText(Html.fromHtml(item.getSummary()));
			ImageUtils.displayImage(App.getInstance(), holder.wxImage, item.getImageURL(), R.dimen.list_item_image_size, R.dimen.list_item_image_size);
			holder.wxShare.setOnClickListener(new OnClickListener() {
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
		public ImageView wxImage;
		public TextView wxTitle;
		public TextView wxfrom;
		public View wxShare;
	}

	@Override
	public void wxGetSuccess(List<WXModel> result) {
		wxLoadingView.setVisibility(View.GONE);
		wxAdapter.setData(result);

		wxList.onRefreshComplete();
	}

	@Override
	public void wxGetFailure() {
		wxAdapter.clear();
		wxLoadingView.setVisibility(View.VISIBLE);
		wxLoadingView.setText(R.string.loading_error_click);
		wxLoadingView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				loadData();
			}
		});
		wxList.onRefreshComplete();
	}
}
