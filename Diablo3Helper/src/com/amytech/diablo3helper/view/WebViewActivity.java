package com.amytech.diablo3helper.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.android.library.views.webview.X5WebView;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.model.DiabloInfoModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月8日 下午7:11:58 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月8日 <br>
 *
 * @author marktlzhai
 */
public class WebViewActivity extends BaseActivity {

	public static final class DataKey {
		public static final String DIABLO_MODEL = "diablo_model";
	}

	private X5WebView x5Webview;

	private Topbar topbar;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_webview;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		x5Webview = (X5WebView) findViewById(R.id.x5webview);

		Bundle data = getIntent().getExtras();
		if (data != null) {
			DiabloInfoModel diabloModel = (DiabloInfoModel) data.getSerializable(DataKey.DIABLO_MODEL);
			if (diabloModel != null) {
				topbar.setTitle(diabloModel.title);
				topbar.configLeftImgBtn(TopbarIcon.BACK, new OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});
				topbar.configRightImgBtn(TopbarIcon.SHARE, new OnClickListener() {
					public void onClick(View v) {
						showToast(TopbarIcon.SHARE.name());
					}
				});

				x5Webview.startLoadWebPage(diabloModel.detailURL);
			}
		}
	}

	@Override
	public void onBackPressed() {
		if (x5Webview.browserCanBack()) {
			x5Webview.navigateBack();
		} else {
			super.onBackPressed();
		}
	}
}
