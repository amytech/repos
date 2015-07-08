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

	public DiabloInfoModel() {
		super();
	}

	public DiabloInfoModel(String title, String desc, String imageURL, String detailURL) {
		super();
		this.title = title;
		this.desc = desc;
		this.imageURL = imageURL;
		this.detailURL = detailURL;
	}

	@Override
	public String toString() {
		return title;
	}
}
