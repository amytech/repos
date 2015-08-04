package com.amytech.tot.model;

import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Title: TOT <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月3日 下午7:38:03 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月3日 <br>
 *
 * @author marktlzhai
 */
public class TOTModel extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4088746649875986057L;

	private String title = "";
	private String desc = "";
	
	private String item1 = "";
	private String item2 = "";
	private String item3 = "";
	private String item4 = "";
	private String item5 = "";
	private String item6 = "";
	
	private String item1Image = "";
	private String item2Image = "";
	private String item3Image = "";
	private String item4Image = "";
	private String item5Image = "";
	private String item6Image = "";
	
	private Boolean isSingle = true;
	private BmobDate createData = new BmobDate(new Date(System.currentTimeMillis()));
	private String ownerUsername = "";

	public TOTModel() {
		setTableName("t_tot");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(Boolean isSingle) {
		this.isSingle = isSingle;
	}

	public BmobDate getCreateData() {
		return createData;
	}

	public void setCreateData(BmobDate createData) {
		this.createData = createData;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getItem5() {
		return item5;
	}

	public void setItem5(String item5) {
		this.item5 = item5;
	}

	public String getItem6() {
		return item6;
	}

	public void setItem6(String item6) {
		this.item6 = item6;
	}

	public String getItem1Image() {
		return item1Image;
	}

	public void setItem1Image(String item1Image) {
		this.item1Image = item1Image;
	}

	public String getItem2Image() {
		return item2Image;
	}

	public void setItem2Image(String item2Image) {
		this.item2Image = item2Image;
	}

	public String getItem3Image() {
		return item3Image;
	}

	public void setItem3Image(String item3Image) {
		this.item3Image = item3Image;
	}

	public String getItem4Image() {
		return item4Image;
	}

	public void setItem4Image(String item4Image) {
		this.item4Image = item4Image;
	}

	public String getItem5Image() {
		return item5Image;
	}

	public void setItem5Image(String item5Image) {
		this.item5Image = item5Image;
	}

	public String getItem6Image() {
		return item6Image;
	}

	public void setItem6Image(String item6Image) {
		this.item6Image = item6Image;
	}
}
