package com.amytech.diablo3helper.view;

import java.io.IOException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.FileUtils;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.Constants;

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

		if (Constants.FILE_SKILL_DB.exists() && Constants.FILE_SKILL_DB.isFile()) {
			enterButton.setVisibility(View.VISIBLE);
		} else {
			try {
				FileUtils.writeFile(Constants.FILE_SKILL_DB, getAssets().open("skill.db"));
			} catch (IOException e) {
				e.printStackTrace();
				showToast(R.string.init_offline_data_error);
			} finally {
				enterButton.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void onBackPressed() {
		AppUtils.exit();
	}
}
