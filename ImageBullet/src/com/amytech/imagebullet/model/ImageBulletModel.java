package com.amytech.imagebullet.model;

import cn.bmob.v3.BmobObject;

/**
 * Title: ImageBullet <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 下午5:36:21 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
public class ImageBulletModel extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7964709789019628013L;

	private String ownerUserName = "";

	private String ownerUserPortrait = "";

	private String imageURL = "";

	public ImageBulletModel() {
		setTableName("t_image_bullet");
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public String getOwnerUserPortrait() {
		return ownerUserPortrait;
	}

	public void setOwnerUserPortrait(String ownerUserPortrait) {
		this.ownerUserPortrait = ownerUserPortrait;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
