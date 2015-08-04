package com.amytech.android.library.base;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amytech.android.library.R;
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
@SuppressLint("ViewHolder")
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

	/**
	 * 显示一个底部菜单
	 * 
	 * @param menuItems
	 */
	public void showBottomMenu(List<Object> menuItems, BottomMenuCallback callback) {
		showBottomMenu(menuItems, true, callback);
	}

	/**
	 * 显示一个底部菜单
	 * 
	 * @param menuItems
	 */
	public void showBottomMenu(List<Object> menuItems, boolean showCancelButton, BottomMenuCallback callback) {
		BottomMenu bottomMenu = new BottomMenu(this, menuItems, showCancelButton, callback);
		bottomMenu.show();
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

	/**
	 * 从底部滑出的菜单
	 */
	private class BottomMenu extends Dialog {
		private ListView buttonList;

		private List<? extends Object> itemList;

		private BottomMenuCallback callback;

		private boolean showCancelButton = true;

		public BottomMenu(Context context, List<? extends Object> itemList, boolean showCancelButton, BottomMenuCallback callback) {
			super(context, R.style.FullScreenDialog);
			this.itemList = itemList;
			this.callback = callback;
			this.showCancelButton = showCancelButton;
			init();
		}

		private void init() {
			setContentView(R.layout.common_bottom_menu);
			getWindow().setWindowAnimations(R.style.DialogPopAnim);
			getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			getWindow().setGravity(Gravity.BOTTOM);

			buttonList = (ListView) findViewById(R.id.menu_list);
			buttonList.setAdapter(new BaseAdapter() {
				@Override
				public View getView(final int position, View convertView, ViewGroup parent) {
					View rootView = LayoutInflater.from(BaseActivity.this).inflate(R.layout.common_menu_button, parent, false);

					TextView buttonText = (TextView) rootView.findViewById(R.id.menu_text);
					buttonText.setText(getItem(position).toString());

					rootView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dismiss();
							if (callback != null) {
								((BottomMenuCallback) callback).onMenuClicked(position, getItem(position));
							}
						}
					});

					return rootView;
				}

				@Override
				public long getItemId(int position) {
					return position;
				}

				@Override
				public Object getItem(int position) {
					return itemList.get(position);
				}

				@Override
				public int getCount() {
					return itemList.size();
				}
			});

			Button cancelButton = (Button) findViewById(R.id.cancel_button);
			cancelButton.setVisibility(showCancelButton ? View.VISIBLE : View.GONE);
			cancelButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dismiss();
					if (callback != null) {
						callback.onMenuCancel();
					}
				}
			});
		}
	}

	public static interface BottomMenuCallback {
		void onMenuClicked(int position, Object item);

		void onMenuCancel();
	}
}
