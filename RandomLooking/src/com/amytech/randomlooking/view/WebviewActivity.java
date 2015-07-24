package com.amytech.randomlooking.view;

import com.amytech.android.library.base.extras.BaseWebViewActivity;
import com.amytech.android.library.utils.NLog;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.webview.AmyWebView;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月24日 下午12:38:52 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月24日 <br>
 *
 * @author marktlzhai
 */
public class WebviewActivity extends BaseWebViewActivity {

	public static final String DATA_KEY_TITLE = "DATA_KEY_TITLE";
	public static final String DATA_KEY_URL = "DATA_KEY_URL";

	@Override
	public void initTopbar(Topbar topbar) {
		topbar.setTitle(getIntent().getStringExtra(DATA_KEY_TITLE));
	}

	@Override
	public void initWebview(AmyWebView webview) {
		NLog.e("load url: " + getIntent().getStringExtra(DATA_KEY_URL));
		webview.startLoadWebPage(getIntent().getStringExtra(DATA_KEY_URL));
	}
}
