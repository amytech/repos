package com.amytech.diablo3helper.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amytech.android.library.base.BaseManager;
import com.amytech.diablo3helper.model.DiabloSkillAdvanceModel;
import com.amytech.diablo3helper.model.DiabloSkillModel;

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

	private SQLiteDatabase skillDB;

	private static final Map<String, List<DiabloSkillModel>> SKILL_DATA_CACHE = new HashMap<String, List<DiabloSkillModel>>();

	private static final Map<DiabloSkillModel, List<DiabloSkillAdvanceModel>> SKILL_ADVANCE_DATA_CACHE = new HashMap<DiabloSkillModel, List<DiabloSkillAdvanceModel>>();

	private SkillManager(Context context) {
		super(context);

		skillDB = SQLiteDatabase.openOrCreateDatabase(Constants.FILE_SKILL_DB, null);
	}

	public static SkillManager getInstance(Context context) {
		if (instance == null) {
			instance = new SkillManager(context);
		}
		return instance;
	}

	public Map<String, List<DiabloSkillModel>> loadSkills() {
		if (SKILL_DATA_CACHE.size() > 0) {
			return SKILL_DATA_CACHE;
		}

		SKILL_DATA_CACHE.clear();
		Cursor skillRS = skillDB.query(SkillTable.TABLE_NAME, SkillTable.C_ALL, null, null, null, null, null);

		if (!skillRS.moveToFirst()) {
			return SKILL_DATA_CACHE;
		}

		do {
			DiabloSkillModel skill = new DiabloSkillModel();
			skill.skillAlias = skillRS.getString(skillRS.getColumnIndex(SkillTable.C_ALIAS));
			skill.jobName = skillRS.getString(skillRS.getColumnIndex(SkillTable.C_JOB));
			skill.skillType = skillRS.getInt(skillRS.getColumnIndex(SkillTable.C_SKILL_TYPE));
			skill.skillName = skillRS.getString(skillRS.getColumnIndex(SkillTable.C_NAME));
			skill.skillDesc = skillRS.getString(skillRS.getColumnIndex(SkillTable.C_DESC));
			skill.skillIcon = skillRS.getString(skillRS.getColumnIndex(SkillTable.C_ICON));

			addSkill(SKILL_DATA_CACHE, skill);
		} while (skillRS.moveToNext());

		return SKILL_DATA_CACHE;
	}

	public Map<DiabloSkillModel, List<DiabloSkillAdvanceModel>> loadSkillAdvances() {
		if (SKILL_ADVANCE_DATA_CACHE.size() > 0) {
			return SKILL_ADVANCE_DATA_CACHE;
		}

		SKILL_ADVANCE_DATA_CACHE.clear();

		Cursor skillAdvanceRS = skillDB.query(SkillAdvanceTable.TABLE_NAME, SkillAdvanceTable.C_ALL, null, null, null, null, null);

		if (!skillAdvanceRS.moveToFirst()) {
			return SKILL_ADVANCE_DATA_CACHE;
		}

		do {
			DiabloSkillAdvanceModel skillAdvance = new DiabloSkillAdvanceModel();
			skillAdvance.skillAdvanceAlias = skillAdvanceRS.getString(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_ALIAS));
			skillAdvance.jobName = skillAdvanceRS.getString(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_JOB));
			skillAdvance.skillParentName = skillAdvanceRS.getString(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_PARENT));
			skillAdvance.skillAdvanceName = skillAdvanceRS.getString(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_NAME));
			skillAdvance.skillAdvanceDesc = skillAdvanceRS.getString(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_DESC));
			skillAdvance.skillAdvanceIcon = skillAdvanceRS.getInt(skillAdvanceRS.getColumnIndex(SkillAdvanceTable.C_ICON));

			addSkillAdvance(SKILL_ADVANCE_DATA_CACHE, skillAdvance);
		} while (skillAdvanceRS.moveToNext());

		return SKILL_ADVANCE_DATA_CACHE;
	}

	private void addSkill(Map<String, List<DiabloSkillModel>> result, DiabloSkillModel skill) {
		List<DiabloSkillModel> skills = result.get(skill.jobName);
		if (skills == null) {
			skills = new ArrayList<DiabloSkillModel>();
			skills.add(skill);
			result.put(skill.jobName, skills);
		} else {
			skills.add(skill);
		}
	}

	private void addSkillAdvance(Map<DiabloSkillModel, List<DiabloSkillAdvanceModel>> result, DiabloSkillAdvanceModel skillAdvance) {
		DiabloSkillModel parent = new DiabloSkillModel(skillAdvance.jobName, skillAdvance.skillParentName);
		List<DiabloSkillAdvanceModel> skillAdvances = result.get(parent);
		if (skillAdvances == null) {
			skillAdvances = new ArrayList<DiabloSkillAdvanceModel>();
			skillAdvances.add(skillAdvance);
			result.put(parent, skillAdvances);
		} else {
			skillAdvances.add(skillAdvance);
		}
	}

	private static class SkillTable {
		private static final String TABLE_NAME = "t_skill";

		public static final String C_ALIAS = "_alias";
		public static final String C_JOB = "_job";
		public static final String C_SKILL_TYPE = "_skill_type";
		public static final String C_NAME = "_name";
		public static final String C_DESC = "_desc";
		public static final String C_ICON = "_icon";

		public static final String[] C_ALL = new String[] { C_ALIAS, C_JOB, C_SKILL_TYPE, C_NAME, C_DESC, C_ICON };
	}

	private static class SkillAdvanceTable {
		private static final String TABLE_NAME = "t_skill_advance";

		public static final String C_ALIAS = "_alias";
		public static final String C_JOB = "_job";
		public static final String C_PARENT = "_parent";
		public static final String C_NAME = "_name";
		public static final String C_DESC = "_desc";
		public static final String C_ICON = "_icon";

		public static final String[] C_ALL = new String[] { C_ALIAS, C_JOB, C_PARENT, C_NAME, C_DESC, C_ICON };
	}
}
