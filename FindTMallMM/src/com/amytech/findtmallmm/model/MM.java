package com.amytech.findtmallmm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午6:06:53 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class MM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439370089417757322L;

	public String avatarUrl = "";

	public String type = "";

	public String city = "";

	public int height = 0;

	public int weight = 0;

	public List<String> images = new ArrayList<String>();

	public String realName = "";

	public int totalFanNum = 0;

	public long userID = 0;

	public String url = "";

	public MM(JSONObject mmObj) {
		if (mmObj != null) {
			avatarUrl = mmObj.optString("avatarUrl");
			type = mmObj.optString("type");
			city = mmObj.optString("city");
			height = mmObj.optInt("height");
			weight = mmObj.optInt("weight");
			realName = mmObj.optString("realName");
			totalFanNum = mmObj.optInt("totalFanNum");
			userID = mmObj.optLong("userId");
			url = mmObj.optString("link");
			JSONArray mmImgArry = mmObj.optJSONArray("imgList");
			images.clear();
			if (mmImgArry != null && mmImgArry.length() > 0) {
				for (int i = 0; i < mmImgArry.length(); i++) {
					images.add(mmImgArry.optString(i));
				}
			}
		}
	}
}
