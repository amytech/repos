package com.amytech.randomlooking.view;

import java.io.Serializable;
import java.text.MessageFormat;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

import com.amytech.android.library.base.extras.BaseWebViewActivity;
import com.amytech.android.library.share.AmyQQ;
import com.amytech.android.library.share.AmyWX;
import com.amytech.android.library.share.view.PickShareDialog;
import com.amytech.android.library.share.view.PickShareDialog.ShareCallback;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.android.library.views.webview.AmyWebView;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.R;
import com.amytech.randomlooking.model.BaseItemModel;

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

	public static final String DATA_ITEM = "DATA_ITEM";

	@Override
	public void initTopbar(Topbar topbar) {
		final Serializable item = getIntent().getSerializableExtra(DATA_ITEM);

		if (item == null) {
			finish();
		}

		topbar.configLeftImgBtn(TopbarIcon.BACK, new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		topbar.configRightImgBtn(TopbarIcon.SHARE, new OnClickListener() {
			public void onClick(View v) {
				share(item);
			}
		});

		if (item instanceof BaseItemModel) {
			topbar.setTitle(((BaseItemModel) item).getTitle());
		}
	}

	@Override
	public void initWebview(AmyWebView webview) {
		Serializable item = getIntent().getSerializableExtra(DATA_ITEM);

		if (item == null) {
			finish();
		}

		if (item instanceof BaseItemModel) {
			webview.startLoadWebPage(((BaseItemModel) item).getTargetURL());
		}
	}

	private void share(final Serializable item) {
		PickShareDialog shareDialog = new PickShareDialog(this, App.WX_APPID, App.QQ_APPID, new ShareCallback() {
			@Override
			public void shareqq() {
				if (item instanceof BaseItemModel) {
					AmyQQ.getInstance(App.QQ_APPID).shareImage(WebviewActivity.this, ((BaseItemModel) item).getImageURL(),
							((BaseItemModel) item).getTargetURL(), getString(R.string.app_name));
				}
			}

			@Override
			public void sharewx() {
				if (item instanceof BaseItemModel) {
					AmyWX.getInstance(App.WX_APPID).share2WX(getString(R.string.app_share_title), ((BaseItemModel) item).getTitle(),
							((BaseItemModel) item).getTargetURL(), false, BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo));
				}
			}

			@Override
			public void sharewxf() {
				if (item instanceof BaseItemModel) {
					AmyWX.getInstance(App.WX_APPID).share2WX(getString(R.string.app_share_title), ((BaseItemModel) item).getTitle(),
							((BaseItemModel) item).getTargetURL(), true, BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo));
				}
			}

			@Override
			public void sharesms() {
				if (item instanceof BaseItemModel) {
					Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
					intent.putExtra("sms_body", MessageFormat.format(getString(R.string.app_share_sms), ((BaseItemModel) item).getTargetURL()));
					startActivity(intent);
				}
			}

			@Override
			public void shareCancel() {

			}
		});
		shareDialog.show();
	}
}
