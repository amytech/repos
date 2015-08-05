package com.amytech.findtmallmm.view;

import org.json.JSONArray;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amytech.android.library.api.APIList;
import com.amytech.android.library.api.APIManager;
import com.amytech.android.library.api.APIManager.APIListener;
import com.amytech.android.library.api.BaseAPIRequest;
import com.amytech.android.library.api.BaseAPIResponse;
import com.amytech.android.library.base.BaseActivity;
import com.amytech.findtmallmm.App;
import com.amytech.findtmallmm.R;
import com.amytech.findtmallmm.controller.MMStyleManager;

public class SplashActivity extends BaseActivity implements APIListener {

	private TextView statusView;

	private Button reloadButton;
	private Button enterButton;
	private Button enterOfflineButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadingStyle();
	}

	private void loadingStyle() {
		BaseAPIRequest request = new BaseAPIRequest(App.API_ID, App.API_SECRET);
		APIManager.getInstance(this).sendRequest(APIList.TMallGirl.STYLE, request, this);
	}

	@Override
	protected void initViews() {
		statusView = (TextView) findViewById(R.id.splash_status);
		statusView.setText(R.string.splash_status_loading);

		reloadButton = (Button) findViewById(R.id.enter_reload);
		reloadButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				reloadButton.setVisibility(View.GONE);
				enterButton.setVisibility(View.GONE);
				enterOfflineButton.setVisibility(View.GONE);

				statusView.setText(R.string.splash_status_loading);
				loadingStyle();
			}
		});

		enterButton = (Button) findViewById(R.id.enter);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		enterOfflineButton = (Button) findViewById(R.id.enter_offline);
		enterOfflineButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onAPISuccess(BaseAPIResponse response) {
		JSONArray styleArry = response.returnObject.optJSONArray("allTypeList");
		MMStyleManager.saveStyleList(spUtils, styleArry);

		statusView.setText(R.string.splash_status_success);
		enterButton.setVisibility(View.VISIBLE);
		reloadButton.setVisibility(View.GONE);
		enterOfflineButton.setVisibility(View.GONE);
	}

	@Override
	public void onAPIFailure() {
		statusView.setText(R.string.splash_status_failure);
		enterButton.setVisibility(View.GONE);
		reloadButton.setVisibility(View.VISIBLE);
		enterOfflineButton.setVisibility(View.VISIBLE);
	}
}
