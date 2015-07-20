package com.amytech.diablo3helper.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amytech.android.library.db.BaseDBOpenHelper;
import com.amytech.android.library.utils.NLog;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月20日 下午3:07:06 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月20日 <br>
 *
 * @author marktlzhai
 */
public class DiabloSkillDB extends BaseDBOpenHelper {

	private static final String DB_NAME = "diablo_skills";
	private static final int DB_VERSION = 1;

	private static final String TABLE_SKILL = "t_skill";
	private static final String TABLE_SKILL_ADVANCE = "t_skill_advance";

	public DiabloSkillDB(Context context) {
		super(context, DB_NAME, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(getCreateTableSkillSQL());
			db.execSQL(getCreateTableSkillAdvanceSQL());
		} catch (Exception e) {
			NLog.e("DiabloSkillDB", "create table failure.", e);
		}

		NLog.e("DiabloSkillDB", "create table success.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion <= oldVersion) {
			return;
		}

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILL_ADVANCE);

		onCreate(db);

		NLog.e("DiabloSkillDB", "update table success.");
	}

	private String getCreateTableSkillSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE \"" + TABLE_SKILL + "\" (");
		sb.append("`_alias`	TEXT,");
		sb.append("`_job`	TEXT,");
		sb.append("`_skill_type`	INTEGER DEFAULT 1,");
		sb.append("`_name`	TEXT,");
		sb.append("`_desc`	TEXT,");
		sb.append("`_icon`	TEXT,");
		sb.append("PRIMARY KEY(_alias,_job,_skill_type)");
		sb.append(")");
		return sb.toString();
	}

	private String getCreateTableSkillAdvanceSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE \"" + TABLE_SKILL_ADVANCE + "\" (");
		sb.append("`_alias`	TEXT,");
		sb.append("`_job`	TEXT,");
		sb.append("`_parent`	TEXT,");
		sb.append("`_name`	TEXT,");
		sb.append("`_desc`	TEXT,");
		sb.append("`_icon`	INTEGER,");
		sb.append("PRIMARY KEY(_alias,_job,_parent)");
		sb.append(")");
		return sb.toString();
	}
}
