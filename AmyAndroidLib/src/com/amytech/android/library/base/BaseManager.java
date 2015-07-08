package com.amytech.android.library.base;

import android.content.Context;

import com.amytech.android.library.utils.asynchttp.AsyncHttpClient;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午4:53:32 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class BaseManager {
	protected static final AsyncHttpClient CLIENT = new AsyncHttpClient();

	protected Context context;

	public BaseManager(Context context) {
		this.context = context;

		CLIENT.setUserAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
	}
}
