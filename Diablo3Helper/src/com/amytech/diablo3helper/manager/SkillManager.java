package com.amytech.diablo3helper.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amytech.android.library.base.BaseManager;
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

	private SkillManager(Context context) {
		super(context);

		skillDB = SQLiteDatabase.openOrCreateDatabase(Constants.DB_FILE_SKILL, null);
	}

	public static SkillManager getInstance(Context context) {
		if (instance == null) {
			instance = new SkillManager(context);
		}
		return instance;
	}

	public Map<String, List<DiabloSkillModel>> loadSkills() {
		Map<String, List<DiabloSkillModel>> result = new HashMap<String, List<DiabloSkillModel>>();
		Cursor rs = skillDB.query(SkillTable.TABLE_NAME, SkillTable.C_ALL, null, null, null, null, null);

		if (!rs.moveToFirst()) {
			return result;
		}

		do {
			DiabloSkillModel skill = new DiabloSkillModel();
			skill.skillAlias = rs.getString(rs.getColumnIndex(SkillTable.C_ALIAS));
			skill.jobName = rs.getString(rs.getColumnIndex(SkillTable.C_JOB));
			skill.skillType = rs.getInt(rs.getColumnIndex(SkillTable.C_SKILL_TYPE));
			skill.skillName = rs.getString(rs.getColumnIndex(SkillTable.C_NAME));
			skill.skillDesc = rs.getString(rs.getColumnIndex(SkillTable.C_DESC));
			skill.skillIcon = rs.getString(rs.getColumnIndex(SkillTable.C_ICON));

			addSkill(result, skill);
		} while (rs.moveToNext());

		return result;
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
}
