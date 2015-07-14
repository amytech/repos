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

	public static final File PATH_DOWNLOAD = DiabloApplication.getInstance().getFilesDir();
	public static final File PATH_CACHE = DiabloApplication.getInstance().getCacheDir();

	public static final String URL_SKILL_DB_FILE = "http://448601869.opendrive.com/files/MjRfNTExNzM0XzlTNVBn/skill.db";
	public static final File DB_FILE_SKILL = new File(PATH_DOWNLOAD, "skill.db");

	public static final String URL_CONF_FILE = "http://448601869.opendrive.com/files/MjRfNTExNzM2X3JYeEZs/diablo_helper.txt";
	public static final File CONF_FILE = new File(PATH_DOWNLOAD, "diablo_helper.conf");

	public static final class SP {
		public static final String SKILL_DB_VERSION = "SKILL_DB_VERSION";
	}
}
