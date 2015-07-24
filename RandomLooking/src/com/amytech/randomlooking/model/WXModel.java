package com.amytech.randomlooking.model;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月24日 下午3:10:47 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月24日 <br>
 *
 * @author marktlzhai
 */
public class WXModel implements BaseItemModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118697283100699648L;

	private String title;
	private String from;
	private String picUrl;
	private String url;

	public WXModel() {
		super();
	}

	public WXModel(String title, String from, String picUrl, String url) {
		super();
		this.title = title;
		this.from = from;
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
		return from;
	}

	@Override
	public String getTargetURL() {
		return url;
	}

	@Override
	public String getImageURL() {
		return picUrl;
	}
}
