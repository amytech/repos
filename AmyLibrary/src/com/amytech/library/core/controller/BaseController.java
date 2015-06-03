package com.amytech.library.core.controller;

import com.loopj.android.http.AsyncHttpClient;

public class BaseController {
	protected static AsyncHttpClient asyncHttpClient;

	protected BaseController() {
		asyncHttpClient = new AsyncHttpClient();
	}
}
