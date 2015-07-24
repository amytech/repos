package com.amytech.randomlooking.model;

import java.io.Serializable;

/**
 * Title: RandomLooking <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月24日 下午3:26:07 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月24日 <br>
 *
 * @author marktlzhai
 */
public interface BaseItemModel extends Serializable {
	String getTitle();

	String getSummary();

	String getTargetURL();

	String getImageURL();
}
