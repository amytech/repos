package com.amytech.android.library.base.extras;

import com.amytech.android.library.R;
import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.webview.AmyWebView;

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
public abstract class BaseWebViewActivity extends BaseActivity {

	public abstract void initTopbar(Topbar topbar);

	public abstract void initWebview(AmyWebView webview);

	public static final class DataKey {
		public static final String DIABLO_MODEL = "diablo_model";
	}

	private AmyWebView amyWebview;

	private Topbar topbar;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_webview;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		amyWebview = (AmyWebView) findViewById(R.id.amywebview);
		
		initTopbar(topbar);
		initWebview(amyWebview);
	}

	@Override
	public void onBackPressed() {
		if (amyWebview.browserCanBack()) {
			amyWebview.navigateBack();
		} else {
			finish();
		}
	}
}