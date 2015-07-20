package com.amytech.diablo3helper.manager;

import java.io.File;

import com.amytech.diablo3helper.DiabloApplication;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月13日 下午3:27:17 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月13日 <br>
 *
 * @author marktlzhai
 */
public class Constants {

	public static final File PATH_FILE = DiabloApplication.getInstance().getFilesDir();
	public static final File PATH_CACHE = DiabloApplication.getInstance().getCacheDir();

	public static final String URL_DEFAULT_INFO = "http://d3.178.com/list/225921464949{0}.html";

	public static final String SP_INFO_URL = "DIABLO_INFO_URL";
}
