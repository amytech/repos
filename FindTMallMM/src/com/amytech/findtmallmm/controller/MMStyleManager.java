package com.amytech.findtmallmm.controller;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;

import com.amytech.android.library.utils.SPUtils;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午6:01:45 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class MMStyleManager {

	private static final String KEY_STYLE_LIST = "style_list";

	public static void saveStyleList(SPUtils sputils, JSONArray styleArry) {
		Set<String> styleSet = new HashSet<String>();
		if (styleArry != null && styleArry.length() > 0) {
			for (int i = 0; i < styleArry.length(); i++) {
				styleSet.add(styleArry.optString(i));
			}
		}
		sputils.putStringSet(KEY_STYLE_LIST, styleSet);
	}

	public static Set<String> loadStyleList(SPUtils spUtils) {
		Set<String> styleSet = new HashSet<String>();
		return spUtils.getStringSet(KEY_STYLE_LIST, styleSet);
	}
}
