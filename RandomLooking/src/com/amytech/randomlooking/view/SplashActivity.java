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
	private Button loginQQButton;
	private Button loginWXButton;
	private LoginButtonClickListener btnListener;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		noLoginButton = (Button) findViewById(R.id.no_login_enter);
		loginQQButton = (Button) findViewById(R.id.login_qq);
		loginWXButton = (Button) findViewById(R.id.login_weixin);

		btnListener = new LoginButtonClickListener();
		noLoginButton.setOnClickListener(btnListener);
		loginQQButton.setOnClickListener(btnListener);
		loginWXButton.setOnClickListener(btnListener);

	}

	@Override
	public void onBackPressed() {
		UMengAnalytic.onKillProcess(this);
		AppUtils.exit(this);
	}

	class LoginButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int btnID = v.getId();
			switch (btnID) {
			case R.id.no_login_enter:
				enter();
				break;
			case R.id.login_qq:
				enterQQ();
				break;
			case R.id.login_weixin:
				enterWX();
				break;
			}
		}

		private void enterWX() {
		}

		private void enterQQ() {
		}

		private void enter() {

		}
	}
}
