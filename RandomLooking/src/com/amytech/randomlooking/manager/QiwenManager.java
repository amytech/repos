package com.amytech.randomlooking.manager;

import java.text.MessageFormat;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.JsonHttpResponseHandler;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.model.QiwenModel;
import com.amytech.randomlooking.parser.QiwenParser;
import com.amytech.umeng.analytics.UMengAnalytic;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午5:57:57 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
@SuppressWarnings("deprecation")
public class QiwenManager extends BaseManager {

	public interface QiwenGetCallback {
		void qiwenGetSuccess(List<QiwenModel> result);

		void qiwenGetFailure();
	}

	private static final String URL = "http://api.huceo.com/qiwen/?key=e49756dd8491033290771e5661221f8e&num={0}";

	private static QiwenManager instance;

	private QiwenManager() {
		super(App.getInstance());
	}

	public static QiwenManager getInstance() {
		if (instance == null) {
			instance = new QiwenManager();
		}
		return instance;
	}

	public void load(final int count, final QiwenGetCallback callback) {
		CLIENT.get(MessageFormat.format(URL, count), new JsonHttpResponseHandler("UTF-8") {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				if (callback != null) {
					callback.qiwenGetSuccess(QiwenParser.parse(response, count));
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);

				UMengAnalytic.onError(context, throwable);

				if (callback != null) {
					callback.qiwenGetFailure();
				}
			}
		});
	}
}
