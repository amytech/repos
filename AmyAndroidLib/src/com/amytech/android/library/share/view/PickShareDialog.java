package com.amytech.android.library.share.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amytech.android.library.R;
import com.amytech.android.library.share.AmyQQ;
import com.amytech.android.library.share.AmyWX;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月24日 上午11:54:27 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月24日 <br>
 *
 * @author marktlzhai
 */
public class PickShareDialog extends Dialog implements android.view.View.OnClickListener {

	public interface ShareCallback {
		void shareqq();

		void sharewx();

		void sharewxf();

		void sharesms();

		void shareCancel();
	}

	private ShareCallback callback;

	private TextView shareQQ;
	private TextView shareWX;
	private TextView shareWXF;
	private TextView shareSMS;

	private Button cancelButton;

	private AmyWX weixin;
	private AmyQQ qq;

	public PickShareDialog(Context context, String wxappid, String qqappid, ShareCallback callback) {
		super(context, R.style.FullScreenDialog);
		weixin = AmyWX.getInstance(wxappid);
		qq = AmyQQ.getInstance(qqappid);
		this.callback = callback;

		init();
	}

	private void init() {
		setContentView(R.layout.dialog_share_dialog);
		getWindow().setWindowAnimations(R.style.DialogPopAnim);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.BOTTOM);

		cancelButton = (Button) findViewById(R.id.share_cancel);

		shareQQ = (TextView) findViewById(R.id.share_to_qq);
		shareWX = (TextView) findViewById(R.id.share_to_wx);
		shareWXF = (TextView) findViewById(R.id.share_to_wxf);
		shareSMS = (TextView) findViewById(R.id.share_to_sms);

		shareQQ.setEnabled(qq.isQQInstall());
		shareWX.setEnabled(weixin.isWXInstall());
		shareWXF.setEnabled(weixin.isWXInstall());
		shareSMS.setEnabled(true);

		shareQQ.setOnClickListener(this);
		shareWX.setOnClickListener(this);
		shareWXF.setOnClickListener(this);
		shareSMS.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		dismiss();
		if (callback != null) {
			int viewid = v.getId();
			if (viewid == R.id.share_to_qq) {
				callback.shareqq();
			}
			if (viewid == R.id.share_to_wx) {
				callback.sharewx();
			}
			if (viewid == R.id.share_to_wxf) {
				callback.sharewxf();
			}
			if (viewid == R.id.share_to_sms) {
				callback.sharesms();
			}
			if (viewid == R.id.share_cancel) {
				callback.shareCancel();
			}
		}
	}
}
