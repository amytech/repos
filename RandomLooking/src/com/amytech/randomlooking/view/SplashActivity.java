package com.amytech.randomlooking.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.randomlooking.R;
import com.amytech.umeng.analytics.UMengAnalytic;

public class SplashActivity extends BaseActivity {

	private Button noLoginButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		noLoginButton = (Button) findViewById(R.id.no_login_enter);
		noLoginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		UMengAnalytic.onKillProcess(this);
		AppUtils.exit(this);
	}
}
