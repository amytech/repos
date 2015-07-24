package com.amytech.randomlooking.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amytech.randomlooking.model.QiwenModel;

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
public class QiwenParser extends JSONParserBase {

	public static List<QiwenModel> parse(JSONObject json, int count) {
		List<QiwenModel> result = new ArrayList<QiwenModel>();

		if (isSuccess(json)) {
			List<JSONObject> jsonDatas = getData(json, count);
			for (JSONObject jsonData : jsonDatas) {
				if (jsonData != null) {
					String title = jsonData.optString("title");
					String description = jsonData.optString("description");
					String picUrl = jsonData.optString("picUrl");
					String url = jsonData.optString("url");
					result.add(new QiwenModel(title, description, picUrl, url));
				}
			}
		}

		return result;
	}
}
