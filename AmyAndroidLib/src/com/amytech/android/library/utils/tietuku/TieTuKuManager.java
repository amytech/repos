package com.amytech.android.library.utils.tietuku;

import java.io.File;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Context;

import com.amytech.android.library.base.BaseManager;
import com.amytech.android.library.utils.asynchttp.JsonHttpResponseHandler;
import com.amytech.android.library.utils.asynchttp.RequestParams;
import com.amytech.android.library.utils.tietuku.AmyImageUploader.UploadListener;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月31日 下午5:53:58 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月31日 <br>
 *
 * @author marktlzhai
 */
public class TieTuKuManager extends BaseManager {

	private static TieTuKuManager instance;

	private String token;

	private TieTuKuManager(Context context, String token) {
		super(context);
		this.token = token;
	}

	public static TieTuKuManager getInstance(Context context, String token) {
		if (instance == null) {
			instance = new TieTuKuManager(context, token);
		}
		return instance;
	}

	public void uploadFile(File imageFile, UploadListener listener) {
		AmyImageUploader uploader = new AmyImageUploader(token);
		UploadRequest request = new UploadRequest(imageFile);
		uploader.setUploadListener(listener);
		uploader.execute(request);
	}

	public void getAlbum(String openKey, int page) {
		String url = "http://api.tietuku.com/v2/api/getalbum";
		RequestParams params = new RequestParams();
		params.add("key", openKey);
		params.add("returntype", "json");
		params.add("p", String.valueOf(page));
		CLIENT.post(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				super.onSuccess(statusCode, headers, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
			}
		});
	}
}
