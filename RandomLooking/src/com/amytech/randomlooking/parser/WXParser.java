package com.amytech.randomlooking.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amytech.randomlooking.model.WXModel;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午6:11:43 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class WXParser extends JSONParserBase {

	public static List<WXModel> parse(JSONObject json, int count) {
		List<WXModel> result = new ArrayList<WXModel>();

		if (isSuccess(json)) {
			List<JSONObject> jsonDatas = getData(json, count);
			for (JSONObject jsonData : jsonDatas) {
				if (jsonData != null) {
					String title = jsonData.optString("title");
					String from = jsonData.optString("description");
					String picUrl = jsonData.optString("picUrl");
					String url = jsonData.optString("url");
					result.add(new WXModel(title, from, picUrl, url));
				}
			}
		}

		return result;
	}
}
