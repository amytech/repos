package com.amytech.diablo3helper.view.skill;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;

import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.model.DiabloSkillModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月16日 下午5:35:36 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月16日 <br>
 *
 * @author marktlzhai
 */
public class SkillPickerDialog extends Dialog{

	private SkillItemView skillItemView;

	private List<DiabloSkillModel> SKILL_DATA = new ArrayList<DiabloSkillModel>();

	public SkillPickerDialog(Context context, SkillItemView skillItemView, List<DiabloSkillModel> skillDatas) {
		super(context, R.style.FullScreenDialog);

		this.skillItemView = skillItemView;
		this.SKILL_DATA.clear();
		this.SKILL_DATA.addAll(skillDatas);

		init();
	}

	private void init() {
		setContentView(R.layout.dialog_skill_picker);
		getWindow().setWindowAnimations(R.style.DialogPopAnim);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.BOTTOM);
	}
}
