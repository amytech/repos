package com.amytech.imagebullet.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.imagebullet.R;

public class SplashActivity extends BaseActivity implements OnClickListener {

	private Button loginQQButton;

	private Button enterLookButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		loginQQButton = (Button) findViewById(R.id.login_qq);
		enterLookButton = (Button) findViewById(R.id.enter_look);

		loginQQButton.setOnClickListener(this);
		enterLookButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.login_qq:

			break;
		case R.id.enter_look:
			startActivity(HomeActivity.class);
			finish();
			break;
		}
	}
}
