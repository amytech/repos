package com.amytech.diablo3helper.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.diablo3helper.R;

public class SplashActivity extends BaseActivity {

	private Button enterButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		enterButton = (Button) findViewById(R.id.splash_enter_btn);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
				finish();
			}
		});
	}
}
