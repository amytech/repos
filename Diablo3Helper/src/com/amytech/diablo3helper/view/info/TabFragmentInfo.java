package com.amytech.diablo3helper.view.info;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.utils.ImageUtils;
import com.amytech.android.library.utils.TimeUtils;
import com.amytech.android.library.utils.UIUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.DiabloInfoManager;
import com.amytech.diablo3helper.manager.DiabloInfoManager.LoadInfoCallback;
import com.amytech.diablo3helper.manager.DiabloInfoManager.ReloadInfoCallback;
import com.amytech.diablo3helper.model.DiabloInfoModel;
import com.amytech.diablo3helper.view.WebViewActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:51:53 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class TabFragmentInfo extends BaseTabItemFragment implements LoadInfoCallback, ReloadInfoCallback, OnRefreshListener2<ListView> {

	private int currentPage = 1;

	private List<DiabloInfoModel> infoData = new ArrayList<DiabloInfoModel>();

	private Topbar topbar;

	private PullToRefreshListView infoListView;
	private ListView realListview;
	private InfoAdapter infoListAdapter;

	@Override
	public void onResume() {
		super.onResume();

		infoData.clear();

		DiabloInfoManager.getInstance(getActivity()).loadInfo(currentPage, this);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_info;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.app_name);
		topbar.configLeftImgBtn(TopbarIcon.NONE, null);
		topbar.configRightImgBtn(TopbarIcon.NONE, null);

		infoListView = (PullToRefreshListView) findViewById(R.id.info_listview);
		initInfoListview();
	}

	private void initInfoListview() {
		infoListAdapter = new InfoAdapter();
		realListview = infoListView.getRefreshableView();
		realListview.setDivider(new ColorDrawable(Color.BLACK));
		realListview.setDividerHeight(1);
		realListview.setAdapter(infoListAdapter);
		infoListView.setMode(Mode.BOTH);
		infoListView.setOnRefreshListener(this);
		infoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiabloInfoModel item = (DiabloInfoModel) parent.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				Bundle data = new Bundle();
				data.putSerializable(WebViewActivity.DataKey.DIABLO_MODEL, item);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onLoadInfoSuccess(List<DiabloInfoModel> result) {
		completeLoading();

		infoData.addAll(result);
		infoListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onLoadInfoFailure(String errorMessage) {
		completeLoading();
		UIUtils.showToast(getActivity(), errorMessage);
	}

	@Override
	public void onReloadInfoSuccess(List<DiabloInfoModel> result) {
		completeLoading();

		infoData.clear();
		infoData.addAll(result);
		infoListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onReloadInfoFailure(String errorMessage) {
		completeLoading();
		UIUtils.showToast(getActivity(), errorMessage);
	}

	private void completeLoading() {
		infoListView.onRefreshComplete();
		infoListView.getLoadingLayoutProxy().setLastUpdatedLabel(
				MessageFormat.format(getString(R.string.refresh_lastupdate_label), TimeUtils.getCurrentTimeInString()));
	}

	class InfoAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;

		private InfoAdapter() {
			layoutInflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			return infoData.size();
		}

		@Override
		public DiabloInfoModel getItem(int position) {
			return infoData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final DiabloInfoModel item = getItem(position);

			ViewHolder holder = null;

			if (convertView == null) {
				holder = new ViewHolder();
				convertView = layoutInflater.inflate(R.layout.item_diablo_info, parent, false);

				holder.infoImage = (ImageView) convertView.findViewById(R.id.diablo_info_image);
				holder.infoTitle = (TextView) convertView.findViewById(R.id.diablo_info_title);
				holder.infoDesc = (TextView) convertView.findViewById(R.id.diablo_info_desc);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			DisplayImageOptions options = ImageUtils.getDisplayOptions(R.drawable.diablo_info_icon_default);
			ImageUtils.displayImage(holder.infoImage, item.imageURL, options);

			holder.infoTitle.setText(item.title);
			holder.infoDesc.setText(item.desc);

			return convertView;
		}
	}

	class ViewHolder {
		ImageView infoImage;
		TextView infoTitle;
		TextView infoDesc;
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		DiabloInfoManager.getInstance(getActivity()).reloadInfo(this);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		DiabloInfoManager.getInstance(getActivity()).loadInfo(++currentPage, this);
	}
}
