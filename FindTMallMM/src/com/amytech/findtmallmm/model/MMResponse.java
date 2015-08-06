package com.amytech.findtmallmm.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amytech.android.library.api.BaseAPIResponse;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午6:05:45 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class MMResponse {

	public int total = 0;
	public int pageSize = 0;
	public int currentPage = 1;
	public List<MM> mmList = new ArrayList<MM>();

	public MMResponse() {
	}

	public void setResponse(BaseAPIResponse response) {
		JSONObject mmData = response.returnObject.optJSONObject("pagebean");
		if (mmData != null) {
			total = mmData.optInt("allNum");
			pageSize = mmData.optInt("allPages");
			currentPage = mmData.optInt("currentPage");
			JSONArray mmArry = mmData.optJSONArray("contentlist");
			if (mmArry != null && mmArry.length() > 0) {
				mmList.clear();
				for (int i = 0; i < mmArry.length(); i++) {
					mmList.add(new MM(mmArry.optJSONObject(i)));
				}
			}
		}
	}
}
