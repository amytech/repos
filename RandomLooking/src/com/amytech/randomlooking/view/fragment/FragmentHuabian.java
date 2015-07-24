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
import com.amytech.randomlooking.manager.HuabianManager;
import com.amytech.randomlooking.manager.HuabianManager.HuabianGetCallback;
import com.amytech.randomlooking.model.HuabianModel;
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
public class FragmentHuabian extends BaseTabItemFragment implements HuabianGetCallback {

	private static final int LOAD_COUNT = 50;

	private Topbar topbar;

	private TextView huabianLoadingView;

	private PullToRefreshListView huabianList;

	private huabianAdapter huabianAdapter;

	private List<HuabianModel> huabianData;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_data;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.tab_huabian);

		huabianLoadingView = (TextView) findViewById(R.id.reload_text);
		huabianList = (PullToRefreshListView) findViewById(R.id.data_list);
		huabianList.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.color_base_title)));
		huabianList.getRefreshableView().setDividerHeight(1);
		huabianList.setMode(Mode.PULL_FROM_START);
		huabianList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				huabianAdapter.clear();
				loadData();
			}
		});

		huabianAdapter = new huabianAdapter();
		huabianList.getRefreshableView().setAdapter(huabianAdapter);
		huabianList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final HuabianModel item = (HuabianModel) parent.getItemAtPosition(position);
				Intent i = new Intent(getActivity(), WebviewActivity.class);
				i.putExtra(WebviewActivity.DATA_ITEM, item);
				startActivity(i);
			}
		});

		loadData();
	}

	private void loadData() {
		huabianLoadingView.setText(R.string.waitting);
		huabianLoadingView.setOnClickListener(null);
		HuabianManager.getInstance().load(LOAD_COUNT, this);
	}

	class huabianAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public huabianAdapter() {
			huabianData = new ArrayList<HuabianModel>();
			inflater = LayoutInflater.from(App.getInstance());
		}

		public void clear() {
			huabianData.clear();
			notifyDataSetChanged();
		}

		public void setData(List<HuabianModel> data) {
			huabianData.clear();
			huabianData.addAll(data);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return huabianData.size();
		}

		@Override
		public HuabianModel getItem(int position) {
			return huabianData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final HuabianModel item = getItem(position);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_double, parent, false);
				holder = new ViewHolder();

				holder.huabianImage = (ImageView) convertView.findViewById(R.id.item_double_pic);
				holder.huabianTitle = (TextView) convertView.findViewById(R.id.item_double_title);
				holder.huabianDate = (TextView) convertView.findViewById(R.id.item_double_subtitle);
				holder.huabianShare = convertView.findViewById(R.id.share_layout);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.huabianTitle.setText(Html.fromHtml(item.getTitle()));
			holder.huabianDate.setText(Html.fromHtml(item.getSummary()));
			ImageUtils.displayImage(App.getInstance(), holder.huabianImage, item.getImageURL(), R.dimen.list_item_image_size, R.dimen.list_item_image_size);
			holder.huabianShare.setOnClickListener(new OnClickListener() {
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
		public ImageView huabianImage;
		public TextView huabianTitle;
		public TextView huabianDate;
		public View huabianShare;
	}

	@Override
	public void huabianGetSuccess(List<HuabianModel> result) {
		huabianLoadingView.setVisibility(View.GONE);
		huabianAdapter.setData(result);

		huabianList.onRefreshComplete();
	}

	@Override
	public void huabianGetFailure() {
		huabianAdapter.clear();
		huabianLoadingView.setVisibility(View.VISIBLE);
		huabianLoadingView.setText(R.string.loading_error_click);
		huabianLoadingView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				loadData();
			}
		});
		huabianList.onRefreshComplete();
	}
}
