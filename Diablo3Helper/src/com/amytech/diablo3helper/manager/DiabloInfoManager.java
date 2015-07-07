package com.amytech.diablo3helper.manager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.TextHttpResponseHandler;
import com.amytech.diablo3helper.model.DiabloInfoModel;

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
public class DiabloInfoManager extends BaseManager {

	public interface LoadInfoCallback {
		void onLoadInfoSuccess(List<DiabloInfoModel> result, boolean append);

		void onLoadInfoFailure(String errorMessage);
	}

	private static final String INFO_URL = "http://d3.178.com/list/225921464949{0}.html";

	/**
	 * 获取资讯消息
	 */
	public static List<DiabloInfoModel> loadInfo(int page, final LoadInfoCallback callback, final boolean append) {
		String url = MessageFormat.format(INFO_URL, page == 1 ? "" : "_" + page);
		final List<DiabloInfoModel> result = new ArrayList<DiabloInfoModel>();
		CLIENT.post(url, new TextHttpResponseHandler("UTF-8") {

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseString) {
				if (callback != null) {
					callback.onLoadInfoSuccess(result, append);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				if (callback != null) {
					callback.onLoadInfoFailure(responseString);
				}
			}
		});

		return result;
	}
}
