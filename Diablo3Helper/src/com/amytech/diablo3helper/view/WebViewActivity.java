package com.amytech.diablo3helper.view;

import android.os.Bundle;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.views.webview.X5WebView;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.model.DiabloInfoModel;
import com.amytech.diablo3helper.widget.Topbar;

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
//				x5Webview.startLoadWebPage(diabloModel.detailURL);
				x5Webview.startLoadWebPage("https://sldjf.asdjfas");
			}
		}
	}
}
