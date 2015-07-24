package com.amytech.android.library.share;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.amytech.android.library.base.BaseApplication;
import com.amytech.android.library.share.model.QQGetInfoResponse;
import com.amytech.android.library.share.model.QQLoginResponse;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.SPUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Title: AmyAndroidShareLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午2:38:30 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class AmyQQ {

	public interface QQLoginInterface {
		void qqLoginSuccess(QQLoginResponse response);

		void qqLoginFailure(UiError uiError);

		void qqLoginCancel();
	}

	public interface QQGetUserInterface {
		void qqGetinfoSuccess(QQGetInfoResponse response);

		void qqGetinfoFailure(UiError uiError);
	}

	private static final String QQ_APP_PKG_NAME = "com.tencent.mobileqq";

	private SPUtils spUtils;

	private Tencent tencent;

	private static AmyQQ instance;

	private static final String SP_QQ_EXPIRES = "QQ_EXPIRES";
	private static final String SP_QQ_TOKEN = "QQ_TOKEN";
	private static final String SP_QQ_OPENID = "QQ_OPENID";

	private AmyQQ(String appid) {
		tencent = Tencent.createInstance(appid, BaseApplication.getInstance());
		spUtils = new SPUtils(BaseApplication.getInstance().getPackageName());
	}

	public static AmyQQ getInstance(String appid) {
		if (instance == null) {
			instance = new AmyQQ(appid);
		}
		return instance;
	}

	public boolean isQQInstall() {
		return AppUtils.isInstallApp(BaseApplication.getInstance(), QQ_APP_PKG_NAME);
	}

	public void login(Activity activity, final QQLoginInterface listener) {

		final long startTime = System.currentTimeMillis();

		tencent.login(activity, "all", new IUiListener() {

			@Override
			public void onError(UiError error) {
				if (listener != null) {
					listener.qqLoginFailure(error);
				}
			}

			@Override
			public void onComplete(Object result) {
				if (listener != null) {
					JSONObject jsonObj = (JSONObject) result;
					QQLoginResponse model = new QQLoginResponse(jsonObj);

					spUtils.putLong(SP_QQ_EXPIRES, startTime + Long.parseLong(model.expires_in));
					spUtils.putString(SP_QQ_TOKEN, model.access_token);
					spUtils.putString(SP_QQ_OPENID, model.openid);

					tencent.setOpenId(model.openid);
					tencent.setAccessToken(model.access_token, String.valueOf(Long.parseLong(model.expires_in) - startTime));

					listener.qqLoginSuccess(model);
				}
			}

			@Override
			public void onCancel() {
				if (listener != null) {
					listener.qqLoginCancel();
				}
			}
		});
	}

	public boolean isSessionValid() {

		long currentTime = System.currentTimeMillis();

		if (spUtils.getLong(SP_QQ_EXPIRES, 0L) > currentTime) {
			tencent.setOpenId(spUtils.getString(SP_QQ_OPENID, ""));
			tencent.setAccessToken(spUtils.getString(SP_QQ_TOKEN, ""), String.valueOf(spUtils.getLong(SP_QQ_EXPIRES, 0L) - currentTime));
			if (tencent.isSessionValid()) {
				return true;
			} else {
				spUtils.putLong(SP_QQ_EXPIRES, 0L);
				spUtils.putString(SP_QQ_TOKEN, "");
				spUtils.putString(SP_QQ_OPENID, "");
				return false;
			}
		}
		return false;
	}

	public void logout(Context context) {
		tencent.logout(context);
	}

	public void getUserInfo(Context context, final QQGetUserInterface listener) {
		UserInfo info = new UserInfo(context, tencent.getQQToken());
		info.getUserInfo(new IUiListener() {
			public void onError(UiError error) {
				if (listener != null) {
					listener.qqGetinfoFailure(error);
				}
			}

			public void onComplete(Object result) {
				if (listener != null) {
					JSONObject json = (JSONObject) result;
					QQGetInfoResponse response = new QQGetInfoResponse(json);
					listener.qqGetinfoSuccess(response);
				}
			}

			public void onCancel() {

			}
		});
	}

	public void shareImage(Activity activity, QQLoginInterface listener, String imageLocalURL, String appName) {
		Bundle params = new Bundle();
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageLocalURL);
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
		params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
		// tencent.shareToQQ(activity, params, listener);
	}

	public void shareMusic(Activity activity, QQLoginInterface listener, String title, String summary, String targetURL, String imageURL, String audioURL,
			String appName) {
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetURL);
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageURL);
		params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, audioURL);
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
		params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
		// tencent.shareToQQ(activity, params, listener);
	}

	public void shareAPP(Activity activity, QQLoginInterface listener, String title, String summary, String imageURL, String appName) {
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, summary);
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageURL);
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
		// tencent.shareToQQ(activity, params, listener);
	}
}
