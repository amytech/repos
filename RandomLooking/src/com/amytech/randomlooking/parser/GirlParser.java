package com.amytech.randomlooking.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amytech.randomlooking.model.GirlModel;

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
public class GirlParser extends JSONParserBase {

	public static List<GirlModel> parse(JSONObject json, int count) {
		List<GirlModel> result = new ArrayList<GirlModel>();

		if (isSuccess(json)) {
			List<JSONObject> jsonDatas = getData(json, count);
			for (JSONObject jsonData : jsonDatas) {
				String title = jsonData.optString("title");
				String description = jsonData.optString("description");
				String picUrl = jsonData.optString("picUrl");
				String url = jsonData.optString("url");
				result.add(new GirlModel(title, description, picUrl, url));
			}
		}

		return result;
	}
}
