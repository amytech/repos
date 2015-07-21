package com.amytech.android.library.base;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amytech.android.library.utils.SPUtils;
import com.amytech.android.library.utils.UIUtils;
import com.amytech.umeng.analytics.UMengBaseFragment;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:41:34 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseFragment extends UMengBaseFragment {
	protected String TAG = getClass().getSimpleName();

	protected SPUtils spUtils;

	protected abstract int getLayoutID();

	protected abstract void initViews();

	protected void showToUser() {
	}

	protected View rootView;

	protected Handler handler = new Handler();

	private Dialog loadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		spUtils = new SPUtils(BaseApplication.getInstance().getPackageName());
		
		if (rootView == null) {
			rootView = inflater.inflate(getLayoutID(), container, false);

			initViews();
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();

			if (parent != null) {
				parent.removeViewInLayout(rootView);
			}
		}
		return rootView;
	}

	protected void showToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(int messageRes) {
		Toast.makeText(getActivity(), messageRes, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			showToUser();
		}
	}

	public View findViewById(int id) {
		return rootView.findViewById(id);
	}

	protected void showLoadingDialog(int messageRes, boolean cancelable) {
		loadingDialog = UIUtils.showProgressDialogTips(getActivity(), messageRes, cancelable);
	}

	protected void showLoadingDialog(String message, boolean cancelable) {
		loadingDialog = UIUtils.showProgressDialogTips(getActivity(), message, cancelable);
	}

	protected void hideLoadingDialog() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}
}
