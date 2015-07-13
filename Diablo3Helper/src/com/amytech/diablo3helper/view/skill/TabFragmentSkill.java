package com.amytech.diablo3helper.view.skill;

import java.util.List;
import java.util.Map;

import com.amytech.android.library.base.extras.BaseTabItemFragment;
import com.amytech.android.library.utils.NLog;
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

		Map<String, List<DiabloSkillModel>> skills = SkillManager.getInstance(getActivity()).loadSkills();
		NLog.d(getClass(), skills);
	}
}
