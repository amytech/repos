package com.amytech.diablo3helper.manager;

import java.text.MessageFormat;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.TextHttpResponseHandler;
import com.amytech.diablo3helper.model.DiabloInfoModel;
import com.amytech.diablo3helper.parser.DiabloInfoParser;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午4:44:01 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
@SuppressWarnings("deprecation")
public class DiabloInfoManager extends BaseManager {

	private static DiabloInfoManager instance;

	private DiabloInfoManager(Context context) {
		super(context);
	}

	public static DiabloInfoManager getInstance(Context context) {
		if (instance == null) {
			instance = new DiabloInfoManager(context);
		}

		return instance;
	}

	public interface ReloadInfoCallback {
		void onReloadInfoSuccess(List<DiabloInfoModel> result);

		void onReloadInfoFailure(String errorMessage);
	}

	public interface LoadInfoCallback {
		void onLoadInfoSuccess(List<DiabloInfoModel> result);

		void onLoadInfoFailure(String errorMessage);
	}

	public void reloadInfo(final ReloadInfoCallback callback) {
		String url = MessageFormat.format(spUtils.getString(Constants.SP_INFO_URL, Constants.URL_DEFAULT_INFO), "");
		CLIENT.get(url, new TextHttpResponseHandler("UTF-8") {

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if (callback != null) {
					List<DiabloInfoModel> result = DiabloInfoParser.parseList(responseString);
					callback.onReloadInfoSuccess(result);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				if (callback != null) {
					callback.onReloadInfoFailure(responseString);
				}
			}
		});
	}

	/**
	 * 获取资讯消息
	 */
	public void loadInfo(final int page, final LoadInfoCallback callback) {
		String url = MessageFormat.format(spUtils.getString(Constants.SP_INFO_URL, Constants.URL_DEFAULT_INFO), page == 1 ? "" : "_" + page);
		CLIENT.get(url, new TextHttpResponseHandler("UTF-8") {

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if (callback != null) {
					List<DiabloInfoModel> result = DiabloInfoParser.parseList(responseString);
					callback.onLoadInfoSuccess(result);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				if (callback != null) {
					callback.onLoadInfoFailure(responseString);
				}
			}
		});
	}
}
