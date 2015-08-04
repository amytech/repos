package com.amytech.imagebullet.controller;

import android.content.Context;
import cn.bmob.v3.BmobUser;

import com.amytech.android.library.base.BaseManager;

/**
 * Title: ImageBullet <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 下午5:17:58 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
public class UserController extends BaseManager {

	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";

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

	public void saveUser(BmobUser user) {
		spUtils.putString(KEY_USERNAME, user.getUsername());
		spUtils.putString(KEY_PASSWORD, user.getPassword());
		spUtils.putString(KEY_EMAIL, user.getEmail());
	}

	public BmobUser getUser() {
		BmobUser user = new BmobUser();
		user.setUsername(spUtils.getString(KEY_USERNAME, ""));
		user.setPassword(spUtils.getString(KEY_PASSWORD, ""));
		user.setEmail(spUtils.getString(KEY_EMAIL, ""));
		return user;
	}
}
