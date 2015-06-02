package com.amytech.diablo3.core.model;

import java.io.Serializable;

import com.amytech.diablo3.R;
import com.amytech.diablo3.core.view.fragment.FragmentBlues;
import com.amytech.diablo3.core.view.fragment.FragmentDB;
import com.amytech.diablo3.core.view.fragment.FragmentFeedback;
import com.amytech.diablo3.core.view.fragment.FragmentGemUpdate;
import com.amytech.diablo3.core.view.fragment.FragmentHero;
import com.amytech.diablo3.core.view.fragment.FragmentJobBBS;
import com.amytech.diablo3.core.view.fragment.FragmentLevel;
import com.amytech.diablo3.core.view.fragment.FragmentNews;
import com.amytech.diablo3.core.view.fragment.FragmentPictures;
import com.amytech.diablo3.core.view.fragment.FragmentSetting;
import com.amytech.diablo3.core.view.fragment.FragmentSkillTools;
import com.amytech.diablo3.core.view.fragment.FragmentVideos;
import com.amytech.library.core.framework.BaseFragment;

public enum HomeFunctionModel implements Serializable {
	// 综合新闻
	News(R.drawable.icon_news, R.string.function_news, FragmentNews.class),
	// 官方蓝贴
	Blues(R.drawable.icon_blues, R.string.function_blues, FragmentBlues.class),
	// 推荐图集
	Pictures(R.drawable.icon_picture, R.string.function_pictures, FragmentPictures.class),
	// 技能模拟器
	SkillTools(R.drawable.icon_skill, R.string.function_skill_tools, FragmentSkillTools.class),
	// 宝石升级
	GemUpdate(R.drawable.icon_gemupdate, R.string.function_gem_update, FragmentGemUpdate.class),
	// 职业讨论
	JobBBS(R.drawable.icon_jobbbs, R.string.function_job_bbs, FragmentJobBBS.class),
	// 视频欣赏
	Videos(R.drawable.icon_videos, R.string.function_videos, FragmentVideos.class),
	// 天梯排名
	Level(R.drawable.icon_level, R.string.function_level, FragmentLevel.class),
	// 英雄排名
	Hero(R.drawable.icon_hero, R.string.function_hero, FragmentHero.class),
	// 数据库
	DB(R.drawable.icon_db, R.string.function_db, FragmentDB.class),
	// 发送反馈
	Feedback(R.drawable.icon_feedback, R.string.function_feedback, FragmentFeedback.class),
	// 系统设置
	Settings(R.drawable.icon_setting, R.string.function_setting, FragmentSetting.class),
	// 退出
	Exit(R.drawable.icon_exit, R.string.function_exit, null);

	private int iconRes;

	private int nameRes;

	private Class<? extends BaseFragment> fragment;

	private HomeFunctionModel(int iconRes, int nameRes, Class<? extends BaseFragment> fragment) {
		this.iconRes = iconRes;
		this.nameRes = nameRes;
		this.fragment = fragment;
	}

	public int getNameRes() {
		return nameRes;
	}

	public int getIconRes() {
		return iconRes;
	}

	public Class<? extends BaseFragment> getFragment() {
		return fragment;
	}
}
