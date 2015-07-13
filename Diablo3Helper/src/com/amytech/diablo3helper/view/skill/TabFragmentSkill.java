package com.amytech.diablo3helper.view.skill;

import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
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
		Map<String, List<DiabloSkillModel>> skills = SkillManager.getInstance(getActivity()).loadSkills();

		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.skill_simulator);
		topbar.configRightImgBtn(TopbarIcon.SHARE, new OnClickListener() {
			public void onClick(View v) {
				showToast("share to friend.");
			}
		});

		checkJobSpinner = (Spinner) findViewById(R.id.skill_check_job_spinner);
		checkJobSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, CollectionUtils.toList(skills.keySet())));
	}
}
