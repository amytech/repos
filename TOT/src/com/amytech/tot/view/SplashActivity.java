package com.amytech.tot.view;

import java.util.Random;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.tot.R;

public class SplashActivity extends BaseActivity implements Runnable {

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {

	}

	@Override
	protected void onResume() {
		super.onResume();

		handler.postDelayed(this, 1000L + new Random().nextInt(1000));
	}

	@Override
	protected String getTypeFaceAssetsName() {
		return "splash_subtitle.ttf";
	}

	@Override
	public void onBackPressed() {
		handler.removeCallbacks(this);
		AppUtils.exit(this);
	}

	@Override
	public void run() {
		startActivity(HomeActivity.class);
		finish();
	}
}
