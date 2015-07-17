package com.amytech.diablo3helper.model;

import java.io.Serializable;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月14日 下午5:08:43 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月14日 <br>
 *
 * @author marktlzhai
 */
public class DiabloSkillAdvanceModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3402654379357970260L;

	public String skillAdvanceAlias = "";
	public String jobName = "";
	public String skillParentName = "";
	public String skillAdvanceName = "";
	public String skillAdvanceDesc = "";
	public int skillAdvanceIcon = 0;

	public DiabloSkillAdvanceModel() {
		super();
	}

	public DiabloSkillAdvanceModel(String skillAdvanceAlias, String jobName, String skillParentName, String skillAdvanceName, String skillAdvanceDesc,
			int skillAdvanceIcon) {
		super();
		this.skillAdvanceAlias = skillAdvanceAlias;
		this.jobName = jobName;
		this.skillParentName = skillParentName;
		this.skillAdvanceName = skillAdvanceName;
		this.skillAdvanceDesc = skillAdvanceDesc;
		this.skillAdvanceIcon = skillAdvanceIcon;
	}

	@Override
	public String toString() {
		return skillParentName + "[" + skillAdvanceName + "]";
	}
}
