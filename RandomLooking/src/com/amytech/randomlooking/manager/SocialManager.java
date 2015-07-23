package com.amytech.randomlooking.manager;

import android.content.Context;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午12:20:28 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class SocialManager {

	private static SocialManager instance;

	private Context context;

	private SocialManager(Context context) {

	}

	public static SocialManager getInstance(Context context) {
		if (instance == null) {
			instance = new SocialManager(context);
		}
		return instance;
	}
}
