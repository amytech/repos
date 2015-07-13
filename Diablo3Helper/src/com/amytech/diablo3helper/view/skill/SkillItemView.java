package com.amytech.diablo3helper.view.skill;

import com.amytech.diablo3helper.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月13日 下午4:18:20 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月13日 <br>
 *
 * @author marktlzhai
 */
@SuppressLint("InflateParams")
public class SkillItemView extends LinearLayout {

	public SkillItemView(Context context) {
		super(context);
		initViews();
	}

	public SkillItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews();
	}

	public SkillItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews();
	}

	private void initViews() {
		View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_skill_item, null, false);
		addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}
}
