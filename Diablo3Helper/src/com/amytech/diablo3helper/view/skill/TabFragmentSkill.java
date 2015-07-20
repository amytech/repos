package com.amytech.diablo3helper.view.skill;

import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.utils.CollectionUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.SkillManager;
import com.amytech.diablo3helper.model.DiabloSkillModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:51:53 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class TabFragmentSkill extends BaseTabItemFragment {

	private Topbar topbar;

	private Spinner checkJobSpinner;

	private SkillItemView skillItem1;
	private SkillItemView skillItem2;
	private SkillItemView skillItem3;
	private SkillItemView skillItem4;
	private SkillItemView skillItem5;
	private SkillItemView skillItem6;

	private ImageView passiveSkill1;
	private ImageView passiveSkill2;
	private ImageView passiveSkill3;
	private ImageView passiveSkill4;

	private OnSkillClickListener skillClickListener;

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_skill;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.skill_simulator);
		topbar.configRightImgBtn(TopbarIcon.SHARE, new OnClickListener() {
			public void onClick(View v) {
				showToast("share to friend.");
			}
		});

		checkJobSpinner = (Spinner) findViewById(R.id.skill_check_job_spinner);
//		ArrayAdapter<? extends Object> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, CollectionUtils.toList(SkillManager
//				.getSkillData().keySet()));
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// checkJobSpinner.setAdapter(adapter);

		skillItem1 = (SkillItemView) findViewById(R.id.skill_item1);
		skillItem2 = (SkillItemView) findViewById(R.id.skill_item2);
		skillItem3 = (SkillItemView) findViewById(R.id.skill_item3);
		skillItem4 = (SkillItemView) findViewById(R.id.skill_item4);
		skillItem5 = (SkillItemView) findViewById(R.id.skill_item5);
		skillItem6 = (SkillItemView) findViewById(R.id.skill_item6);

		passiveSkill1 = (ImageView) findViewById(R.id.passive_skill1);
		passiveSkill2 = (ImageView) findViewById(R.id.passive_skill2);
		passiveSkill3 = (ImageView) findViewById(R.id.passive_skill3);
		passiveSkill4 = (ImageView) findViewById(R.id.passive_skill4);

		skillClickListener = new OnSkillClickListener();
		skillItem1.setOnClickListener(skillClickListener);
		skillItem2.setOnClickListener(skillClickListener);
		skillItem3.setOnClickListener(skillClickListener);
		skillItem4.setOnClickListener(skillClickListener);
		skillItem5.setOnClickListener(skillClickListener);
		skillItem6.setOnClickListener(skillClickListener);
	}

	private class OnSkillClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
//			SkillPickerDialog dialog = new SkillPickerDialog(getActivity(), (SkillItemView) v, SkillManager.getSkillData().get(
//					checkJobSpinner.getSelectedItem().toString()));
//			dialog.show();
		}
	}
}
