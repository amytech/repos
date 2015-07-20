package com.amytech.diablo3helper.manager;

import android.content.Context;

import com.amytech.android.library.base.BaseManager;
import com.amytech.diablo3helper.model.database.DiabloSkillDB;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月13日 下午3:47:14 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月13日 <br>
 *
 * @author marktlzhai
 */
public class SkillManager extends BaseManager {

	private static SkillManager instance;

	private DiabloSkillDB skillDB;

	private SkillManager(Context context) {
		super(context);
		
		skillDB = new DiabloSkillDB(context);
	}

	public static SkillManager getInstance(Context context) {
		if (instance == null) {
			instance = new SkillManager(context);
		}
		return instance;
	}
}
