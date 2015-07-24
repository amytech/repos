package com.amytech.android.library.share;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import com.amytech.android.library.base.BaseApplication;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月24日 上午11:53:59 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月24日 <br>
 *
 * @author marktlzhai
 */
public class AmyWX {

	private IWXAPI weixin;

	private static AmyWX instance;

	private AmyWX(String appid) {
		weixin = WXAPIFactory.createWXAPI(BaseApplication.getInstance(), appid);
		weixin.registerApp(appid);
	}

	public static AmyWX getInstance(String appid) {
		if (instance == null) {
			instance = new AmyWX(appid);
		}
		return instance;
	}

	public boolean isWXInstall() {
		return weixin.isWXAppInstalled();
	}

	public void share2WX(String title, String summary, String targetURL, boolean timeline, Bitmap sharedBitmap) {
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = targetURL;
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = title;
		msg.description = summary;

		Bitmap thumbBmp = Bitmap.createScaledBitmap(sharedBitmap, 150, 150, true);
		sharedBitmap.recycle();
		msg.thumbData = bmpToByteArray(thumbBmp, true);

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		req.scene = timeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
		weixin.sendReq(req);
	}

	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
