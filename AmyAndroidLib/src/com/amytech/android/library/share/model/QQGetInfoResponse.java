package com.amytech.android.library.share.model;

import org.json.JSONObject;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午4:35:23 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class QQGetInfoResponse {

	public String ret = "";
	public String is_yellow_year_vip = "";
	public String figureurl_qq_1 = "";
	public String figureurl_qq_2 = "";
	public String nickname = "";
	public String yellow_vip_level = "";
	public String is_lost = "";
	public String msg = "";
	public String province = "";
	public String city = "";
	public String vip = "";
	public String level = "";
	public String is_yellow_vip = "";
	public String gender = "";
	public String figureurl = "";
	public String figureurl_1 = "";
	public String figureurl_2 = "";

	public QQGetInfoResponse(JSONObject json) {
		if (json == null || json.length() == 0) {
			return;
		}

		ret = json.optString("ret", "");
		is_yellow_year_vip = json.optString("is_yellow_year_vip", "");
		figureurl_qq_1 = json.optString("figureurl_qq_1", "");
		figureurl_qq_2 = json.optString("figureurl_qq_2", "");
		nickname = json.optString("nickname", "");
		yellow_vip_level = json.optString("yellow_vip_level", "");
		is_lost = json.optString("is_lost", "");
		msg = json.optString("msg", "");
		province = json.optString("province", "");
		city = json.optString("city", "");
		vip = json.optString("vip", "");
		level = json.optString("level", "");
		is_yellow_vip = json.optString("is_yellow_vip", "");
		gender = json.optString("gender", "");
		figureurl = json.optString("figureurl", "");
		figureurl_1 = json.optString("figureurl_1", "");
		figureurl_2 = json.optString("figureurl_2", "");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("ret=" + ret + "\n");
		builder.append("is_yellow_year_vip=" + is_yellow_year_vip + "\n");
		builder.append("figureurl_qq_1=" + figureurl_qq_1 + "\n");
		builder.append("figureurl_qq_2=" + figureurl_qq_2 + "\n");
		builder.append("nickname=" + nickname + "\n");
		builder.append("yellow_vip_level=" + yellow_vip_level + "\n");
		builder.append("is_lost=" + is_lost + "\n");
		builder.append("msg=" + msg + "\n");
		builder.append("province=" + province + "\n");
		builder.append("city=" + city + "\n");
		builder.append("vip=" + vip + "\n");
		builder.append("level=" + level + "\n");
		builder.append("is_yellow_vip=" + is_yellow_vip + "\n");
		builder.append("gender=" + gender + "\n");
		builder.append("figureurl=" + figureurl + "\n");
		builder.append("figureurl_1=" + figureurl_1 + "\n");
		builder.append("figureurl_2=" + figureurl_2 + "\n");

		return builder.toString();
	}
}
