package com.amytech.randomlooking.model;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月23日 下午6:10:34 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月23日 <br>
 *
 * @author marktlzhai
 */
public class GirlModel implements BaseItemModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 254779968840285714L;

	private String title;
	private String description;
	private String picUrl;
	private String url;

	public GirlModel() {
		super();
	}

	public GirlModel(String title, String description, String picUrl, String url) {
		super();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getSummary() {
		return description;
	}

	@Override
	public String getTargetURL() {
		int sub = url.indexOf("www.27270.com");
		if (sub > 0) {
			return url.replaceFirst("www.27270.com", "m.27270.com");
		} else {
			return url;
		}
	}

	@Override
	public String getImageURL() {
		return picUrl;
	}
}
