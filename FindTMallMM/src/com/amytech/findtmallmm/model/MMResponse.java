package com.amytech.findtmallmm.model;

import java.util.ArrayList;
import java.util.List;

import com.amytech.android.library.api.BaseAPIResponse;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午6:05:45 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class MMResponse {

	public int total = 0;
	public int pageSize = 0;
	public int currentPage = 0;
	public List<MM> mmList = new ArrayList<MM>();

	private BaseAPIResponse response;

	public MMResponse() {
	}

	public BaseAPIResponse getResponse() {
		return response;
	}

	public void setResponse(BaseAPIResponse response) {
		this.response = response;
	}
}
