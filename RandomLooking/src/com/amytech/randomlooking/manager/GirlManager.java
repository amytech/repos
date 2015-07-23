package com.amytech.randomlooking.manager;

import java.text.MessageFormat;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.JsonHttpResponseHandler;
import com.amytech.randomlooking.App;
import com.amytech.randomlooking.model.GirlModel;
import com.amytech.randomlooking.parser.GirlParser;
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
public class GirlManager extends BaseManager {

	public interface GirlGetCallback {
		void girlGetSuccess(List<GirlModel> result);

		void girlGetFailure();
	}

	private static final String URL = "http://api.huceo.com/meinv/?key=e49756dd8491033290771e5661221f8e&num={0}";

	private static GirlManager instance;

	private GirlManager() {
		super(App.getInstance());
	}

	public static GirlManager getInstance() {
		if (instance == null) {
			instance = new GirlManager();
		}
		return instance;
	}

	public void load(final int count, final GirlGetCallback callback) {
		CLIENT.get(MessageFormat.format(URL, count), new JsonHttpResponseHandler("UTF-8") {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				if (callback != null) {
					callback.girlGetSuccess(GirlParser.parse(response, count));
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);

				UMengAnalytic.onError(context, throwable);

				if (callback != null) {
					callback.girlGetFailure();
				}
			}
		});
	}
}
