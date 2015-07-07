package com.amytech.diablo3helper.model;

import java.io.Serializable;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午4:41:48 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class DiabloInfoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -714927891206132151L;

	public int id = 0;

	public String title = "";

	public String desc = "";

	public String imageURL = "";

	public String detailURL = "";

	public String author = "";

	public long publishDate = 0;

	public static final class Table {
		public static final String ID = "_id";
		public static final String TITLE = "title";
		public static final String DESC = "desc";
		public static final String IMAGE_URL = "imageURL";
		public static final String DETAIL_URL = "detailURL";
		public static final String AUTHOR = "author";
		public static final String PUBLISH_DATE = "publishDate";

		public static final String[] CLOUMNS = new String[] { ID, TITLE, DESC, IMAGE_URL, DETAIL_URL, AUTHOR, PUBLISH_DATE };
	}
}
