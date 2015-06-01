package com.amytech.library.core.framework;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amytech.library.core.view.LoadingDialog;
import com.fragmentmaster.app.MasterFragment;

/**
 * 基础Fragment
 * 
 * @author AmyTech
 */
public abstract class BaseFragment extends MasterFragment {
	protected abstract int getLayoutID();

	protected abstract void showToUser();

	private View rootView;

	private Dialog loadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(getLayoutID(), container, false);
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeViewInLayout(rootView);
			}
		}

		return rootView;
	}

	protected View findViewById(int viewID) {
		return rootView.findViewById(viewID);
	}

	public void showToast(int messageRes) {
		showShortToast(messageRes);
	}

	public void showToast(String message) {
		showShortToast(message);
	}

	public void showShortToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	public void showShortToast(int messageResID) {
		Toast.makeText(getActivity(), messageResID, Toast.LENGTH_SHORT).show();
	}

	public void showLongToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
	}

	public void showLongToast(int messageResID) {
		Toast.makeText(getActivity(), messageResID, Toast.LENGTH_LONG).show();
	}

	public String getFragmentTag() {
		return getClass().getName();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			showToUser();
		}
	}

	public void showLoadingDialog(String message, boolean cancelable) {
		hideLoadingDialog();
		loadingDialog = LoadingDialog.showLoadingDialog(getActivity(), message, cancelable);
	}

	public void hideLoadingDialog() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}
}
