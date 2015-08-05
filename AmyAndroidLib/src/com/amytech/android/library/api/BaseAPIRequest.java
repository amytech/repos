package com.amytech.android.library.api;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午4:36:19 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class BaseAPIRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5827737173468521327L;

	public String appid = "";
	public String secret = "";
	public String timestamp = "";

	public BaseAPIRequest(String appid, String secret) {
		this.appid = appid;
		this.secret = secret;
		this.timestamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
	}

	public Map<String, String> getCustomParams() {
		return null;
	}
}
