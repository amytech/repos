package com.amytech.diablo3helper.view;

import java.util.Iterator;

import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.StringUtils;
import com.amytech.diablo3helper.R;
import com.amytech.umeng.analytics.UMengAnalytic;
import com.umeng.onlineconfig.UmengOnlineConfigureListener;

public class SplashActivity extends BaseActivity {

	private Button enterButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		enterButton = (Button) findViewById(R.id.splash_enter_btn);
		enterButton.setVisibility(View.GONE);

		UMengAnalytic.init(this, "55acb3d767e58e28c2002036", UMengAnalytic.CHANNEL_DEFAULT, new UmengOnlineConfigureListener() {
			@Override
			public void onDataReceived(JSONObject data) {
				if (data != null && data.length() > 0) {
					for (Iterator<String> iterator = data.keys(); iterator.hasNext();) {
						String key = iterator.next();
						String value = data.optString(key);
						if (StringUtils.isNotEmpty(value)) {
							spUtils.putString(key, value);
						}
					}
				}

				enableEnter();
			}
		});
		
		handle
	}

	private void enableEnter() {
		enterButton.setVisibility(View.VISIBLE);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {
		AppUtils.exit(this);
	}
}
