package com.amytech.findtmallmm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: FindTMallMM <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月5日 下午6:06:53 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月5日 <br>
 *
 * @author marktlzhai
 */
public class MM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439370089417757322L;

	public String avatarUrl = "";

	public String cardUrl = "";

	public String city = "";

	public int height = 0;

	public int weight = 0;

	public List<String> images = new ArrayList<String>();

	public String realName = "";

	public int totalFanNum = 0;

	public long userID = 0;

	public String url = "";

}
