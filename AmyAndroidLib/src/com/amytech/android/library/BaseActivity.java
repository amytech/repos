package com.amytech.android.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
public class BaseActivity extends FragmentActivity {

	protected String TAG = getClass().getSimpleName();

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
