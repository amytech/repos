package com.amytech.tot.controller;

import android.content.Context;

import com.amytech.android.library.base.BaseManager;
import com.amytech.tot.model.TOTUser;

/**
 * Title: TOT <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 上午11:45:38 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
public class UserController extends BaseManager {

	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASS = "";

	private static UserController instance;

	private UserController(Context context) {
		super(context);
	}

	public static UserController getInstance(Context context) {
		if (instance == null) {
			instance = new UserController(context);
		}
		return instance;
	}

	public void saveCurrentUser(TOTUser user) {
		spUtils.putString(KEY_USERNAME, user.getUsername());
		spUtils.putString(KEY_PASS, user.getPassword());
	}

	public TOTUser getCurrentUser() {
		TOTUser user = new TOTUser();
		user.setUsername(spUtils.getString(KEY_USERNAME, ""));
		user.setPassword(spUtils.getString(KEY_PASS, ""));
		return user;
	}
}
