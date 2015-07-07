package com.amytech.diablo3helper.manager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.amytech.android.library.base.BaseManager;
import com.amytech.diablo3helper.model.DiabloInfoModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午4:44:01 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class DiabloInfoManager extends BaseManager {

	private static final String INFO_URL = "http://d3.178.com/list/225921464949{0}.html";

	/**
	 * 获取资讯消息
	 */
	public static List<DiabloInfoModel> loadInfo(int page) {
		String url = MessageFormat.format(INFO_URL, page == 1 ? "" : "_" + page);
		List<DiabloInfoModel> result = new ArrayList<DiabloInfoModel>();
		return result;
	}
}
