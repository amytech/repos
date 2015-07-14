package com.amytech.diablo3helper.model;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月13日 下午3:51:04 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月13日 <br>
 *
 * @author marktlzhai
 */
public class DiabloSkillModel {

	public String skillAlias = "";
	public String jobName = "";
	public int skillType = 0;
	public String skillName = "";
	public String skillDesc = "";
	public String skillIcon = "";

	public DiabloSkillModel() {
		super();
	}

	public DiabloSkillModel(String skillAlias, String jobName, int skillType, String skillName, String skillDesc, String skillIcon) {
		super();
		this.skillAlias = skillAlias;
		this.jobName = jobName;
		this.skillType = skillType;
		this.skillName = skillName;
		this.skillDesc = skillDesc;
		this.skillIcon = skillIcon;
	}

	@Override
	public String toString() {
		return skillName;
	}
}
