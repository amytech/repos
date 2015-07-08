package com.amytech.diablo3helper;

import com.amytech.android.library.base.BaseApplication;
import com.amytech.android.library.utils.ImageUtils;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月6日 下午4:29:13 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月6日 <br>
 *
 * @author marktlzhai
 */
public class DiabloApplication extends BaseApplication {

	@Override
	public void onCreate() {
		super.onCreate();

		ImageUtils.init(this);
	}
}
