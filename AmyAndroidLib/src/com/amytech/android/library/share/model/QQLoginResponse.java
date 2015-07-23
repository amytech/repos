package com.amytech.android.library.share.model;

import org.json.JSONObject;

/**
 * Title: AmyAndroidShareLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午3:33:14 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class QQLoginResponse {
	public String ret = "";
	public String pay_token = "";
	public String pf = "";
	public String query_authority_cost = "";
	public String authority_cost = "";
	public String openid = "";
	public String expires_in = "";
	public String pfkey = "";
	public String msg = "";
	public String access_token = "";
	public String login_cost = "";

	public QQLoginResponse(JSONObject json) {
		if (json == null || json.length() == 0) {
			return;
		}

		ret = json.optString("ret", "");
		pay_token = json.optString("pay_token", "");
		pf = json.optString("pf", "");
		query_authority_cost = json.optString("query_authority_cost", "");
		authority_cost = json.optString("authority_cost", "");
		openid = json.optString("openid", "");
		expires_in = json.optString("expires_in", "");
		pfkey = json.optString("pfkey", "");
		msg = json.optString("msg", "");
		access_token = json.optString("access_token", "");
		login_cost = json.optString("login_cost", "");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("ret=" + ret + "\n");
		builder.append("pay_token=" + pay_token + "\n");
		builder.append("pf=" + pf + "\n");
		builder.append("query_authority_cost=" + query_authority_cost + "\n");
		builder.append("authority_cost=" + authority_cost + "\n");
		builder.append("openid=" + openid + "\n");
		builder.append("expires_in=" + expires_in + "\n");
		builder.append("pfkey=" + pfkey + "\n");
		builder.append("msg=" + msg + "\n");
		builder.append("access_token=" + access_token + "\n");
		builder.append("login_cost=" + login_cost);

		return builder.toString();
	}
}
