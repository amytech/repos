package com.amytech.android.library.base;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amytech.android.library.utils.SPUtils;
import com.amytech.android.library.utils.StringUtils;
import com.amytech.android.library.utils.UIUtils;
import com.amytech.umeng.analytics.UMengBaseActivity;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月2日 下午7:44:10 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月2日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseActivity extends UMengBaseActivity {

	protected abstract int getLayoutID();

	protected abstract void initViews();

	protected String TAG = getClass().getSimpleName();

	protected LayoutInflater layoutInflater;

	protected SPUtils spUtils;

	protected Handler handler = new Handler();

	private Dialog loadingDialog;

	public void startActivity(Class<? extends BaseActivity> activityClass) {
		Intent intent = new Intent(this, activityClass);
		startActivity(intent);
	}

	public void startActivity(Class<? extends BaseActivity> activityClass, Bundle data) {
		Intent intent = new Intent(this, activityClass);
		intent.putExtras(data);
		startActivity(intent);
	}

	public void startActivityForResult(Class<? extends BaseActivity> activityClass, int requestCode) {
		Intent intent = new Intent(this, activityClass);
		startActivityForResult(intent, requestCode);
	}

	public void startActivityForResult(Class<? extends BaseActivity> activityClass, int requestCode, Bundle data) {
		Intent intent = new Intent(this, activityClass);
		intent.putExtras(data);
		startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutID());

		layoutInflater = LayoutInflater.from(this);

		spUtils = new SPUtils(getPackageName());

		initViews();

		if (StringUtils.isNotEmpty(getTypeFaceAssetsName())) {
			Typeface tf = Typeface.createFromAsset(getAssets(), getTypeFaceAssetsName());
			ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
			UIUtils.setTypeFace(viewGroup, tf);
		}
	}

	protected String getTypeFaceAssetsName() {
		return "";
	}

	protected void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(int messageRes) {
		Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show();
	}

	protected void showLoadingDialog(int messageRes, boolean cancelable) {
		loadingDialog = UIUtils.showProgressDialogTips(this, messageRes, cancelable);
	}

	protected void showLoadingDialog(String message, boolean cancelable) {
		loadingDialog = UIUtils.showProgressDialogTips(this, message, cancelable);
	}

	protected void hideLoadingDialog() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}
}
