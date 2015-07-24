package com.amytech.randomlooking.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amytech.randomlooking.model.HuabianModel;

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
public class HuabianParser extends JSONParserBase {

	public static List<HuabianModel> parse(JSONObject json, int count) {
		List<HuabianModel> result = new ArrayList<HuabianModel>();

		if (isSuccess(json)) {
			List<JSONObject> jsonDatas = getData(json, count);
			for (JSONObject jsonData : jsonDatas) {
				String title = jsonData.optString("title");
				String date = jsonData.optString("time");
				String picUrl = jsonData.optString("picUrl");
				String url = jsonData.optString("url");
				result.add(new HuabianModel(title, date, picUrl, url));
			}
		}

		return result;
	}
}
