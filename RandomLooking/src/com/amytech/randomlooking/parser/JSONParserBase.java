package com.amytech.randomlooking.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.amytech.android.library.utils.StringUtils;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午6:04:07 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class JSONParserBase {

	public static boolean isSuccess(JSONObject json) {
		if (json != null) {
			String code = json.optString("code", "");
			String msg = json.optString("msg", "");
			return StringUtils.isEquals(code, "200") && StringUtils.isEquals(msg, "ok");
		}
		return false;
	}

	public static List<JSONObject> getData(JSONObject json, int count) {
		List<JSONObject> result = new ArrayList<JSONObject>();
		for (int i = 0; i < count; i++) {
			result.add(json.optJSONObject(String.valueOf(i)));
		}
		return result;
	}
}
