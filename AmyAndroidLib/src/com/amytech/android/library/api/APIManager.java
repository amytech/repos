package com.amytech.android.library.api;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Context;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.JsonHttpResponseHandler;
import com.amytech.android.library.utils.asynchttp.RequestParams;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午4:31:09 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
@SuppressWarnings("deprecation")
public class APIManager extends BaseManager {

	private static final String API_HOST = "http://route.showapi.com/";

	public interface APIListener {
		void onAPISuccess(BaseAPIResponse response);

		void onAPIFailure();
	}

	private static APIManager instance;

	private APIManager(Context context) {
		super(context);
	}

	public static APIManager getInstance(Context context) {
		if (instance == null) {
			instance = new APIManager(context);
		}
		return instance;
	}

	public void sendRequest(String api, BaseAPIRequest request, final APIListener listener) {
		String apiURL = API_HOST + api;
		RequestParams params = new RequestParams(request.getCustomParams());
		params.put("showapi_appid", request.appid);
		params.put("showapi_sign", request.secret);
		params.put("showapi_timestamp", request.timestamp);
		CLIENT.post(apiURL, params, new JsonHttpResponseHandler() {
			private boolean success = false;

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				success = true;
				if (listener != null) {
					int returnCode = response.optInt("showapi_res_code", -1);
					String returnMessage = response.optString("showapi_res_error");
					BaseAPIResponse apiResponse = new BaseAPIResponse(returnCode, returnMessage);
					if (apiResponse.isSuccess()) {
						apiResponse.returnObject = response.optJSONObject("showapi_res_body");
						listener.onAPISuccess(apiResponse);
					} else {
						listener.onAPIFailure();
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				success = false;
				if (listener != null) {
					listener.onAPIFailure();
				}
			}

			@Override
			public void onFinish() {
				super.onFinish();
				if (listener != null && !success) {
					listener.onAPIFailure();
				}
			}
		});
	}
}
