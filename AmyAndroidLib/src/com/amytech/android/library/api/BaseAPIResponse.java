package com.amytech.android.library.api;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午4:48:03 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class BaseAPIResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3560577558214980130L;

	public int returnCode = 0;
	public String returnError = "";
	public JSONObject returnObject;

	public BaseAPIResponse(int returnCode, String returnError) {
		this.returnCode = returnCode;
		this.returnError = returnError;
	}

	public boolean isSuccess() {
		return returnCode == 0;
	}
}
