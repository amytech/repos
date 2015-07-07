package com.amytech.diablo3helper.model.database;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.amytech.android.library.utils.CollectionUtils;
import com.amytech.android.library.utils.NLog;
import com.amytech.diablo3helper.model.DiabloInfoModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午5:27:12 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public class DiabloInfoDB extends SQLiteOpenHelper {

	private static final String DB_NAME = "DiabloInformation.db";

	private static final int DB_VERSION = 1;

	private static final String INFO_TABLE_NAME = "diablo_info";

	public DiabloInfoDB(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder builder = new StringBuilder();
		builder.append("create table " + INFO_TABLE_NAME + "(");
		builder.append(DiabloInfoModel.Table.ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,");
		builder.append(DiabloInfoModel.Table.TITLE + " TEXT,");
		builder.append(DiabloInfoModel.Table.DESC + " TEXT,");
		builder.append(DiabloInfoModel.Table.IMAGE_URL + " TEXT,");
		builder.append(DiabloInfoModel.Table.DETAIL_URL + " TEXT,");
		builder.append(DiabloInfoModel.Table.AUTHOR + " TEXT,");
		builder.append(DiabloInfoModel.Table.PUBLISH_DATE + " NUMBER");
		builder.append(")");
		String sql = builder.toString();
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + INFO_TABLE_NAME);
		onCreate(db);
	}

	public void add(DiabloInfoModel model) {
		SQLiteDatabase db = getWritableDatabase();

		try {
			ContentValues values = new ContentValues();
			values.put(DiabloInfoModel.Table.TITLE, model.title);
			values.put(DiabloInfoModel.Table.DESC, model.desc);
			values.put(DiabloInfoModel.Table.IMAGE_URL, model.imageURL);
			values.put(DiabloInfoModel.Table.DETAIL_URL, model.detailURL);
			values.put(DiabloInfoModel.Table.AUTHOR, model.author);
			values.put(DiabloInfoModel.Table.PUBLISH_DATE, model.publishDate);

			db.insert(INFO_TABLE_NAME, null, values);
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public void addAll(List<DiabloInfoModel> models) {
		if (CollectionUtils.isEmpty(models)) {
			return;
		}

		SQLiteDatabase db = getWritableDatabase();

		try {
			db.beginTransaction();

			for (DiabloInfoModel diabloInfoModel : models) {
				ContentValues values = new ContentValues();
				values.put(DiabloInfoModel.Table.TITLE, diabloInfoModel.title);
				values.put(DiabloInfoModel.Table.DESC, diabloInfoModel.desc);
				values.put(DiabloInfoModel.Table.IMAGE_URL, diabloInfoModel.imageURL);
				values.put(DiabloInfoModel.Table.DETAIL_URL, diabloInfoModel.detailURL);
				values.put(DiabloInfoModel.Table.AUTHOR, diabloInfoModel.author);
				values.put(DiabloInfoModel.Table.PUBLISH_DATE, diabloInfoModel.publishDate);

				db.insert(INFO_TABLE_NAME, null, values);
			}
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (db != null) {
				db.endTransaction();
				db.close();
			}
		}
	}

	public void delete(int id) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.delete(INFO_TABLE_NAME, DiabloInfoModel.Table.ID + " = ?", new String[] { String.valueOf(id) });
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public void clear() {
		SQLiteDatabase db = getWritableDatabase();
		try {
			db.delete(INFO_TABLE_NAME, null, null);
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public void update(DiabloInfoModel model) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put(DiabloInfoModel.Table.TITLE, model.title);
			values.put(DiabloInfoModel.Table.DESC, model.desc);
			values.put(DiabloInfoModel.Table.IMAGE_URL, model.imageURL);
			values.put(DiabloInfoModel.Table.DETAIL_URL, model.detailURL);
			values.put(DiabloInfoModel.Table.AUTHOR, model.author);
			values.put(DiabloInfoModel.Table.PUBLISH_DATE, model.publishDate);

			db.update(INFO_TABLE_NAME, values, DiabloInfoModel.Table.ID + " = ?", new String[] { String.valueOf(model.id) });
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public DiabloInfoModel query(int id) {
		SQLiteDatabase db = getWritableDatabase();
		DiabloInfoModel result = null;
		Cursor dbRS = null;
		try {
			dbRS = db.query(INFO_TABLE_NAME, DiabloInfoModel.Table.CLOUMNS, DiabloInfoModel.Table.ID + " = ?", new String[] { String.valueOf(id) }, null, null,
					null);
			if (!dbRS.moveToFirst()) {
				return null;
			}

			do {
				result = new DiabloInfoModel();
				result.id = dbRS.getInt(dbRS.getColumnIndex(DiabloInfoModel.Table.ID));
				result.title = dbRS.getString(dbRS.getColumnIndex(DiabloInfoModel.Table.TITLE));
				result.desc = dbRS.getString(dbRS.getColumnIndex(DiabloInfoModel.Table.DESC));
				result.imageURL = dbRS.getString(dbRS.getColumnIndex(DiabloInfoModel.Table.IMAGE_URL));
				result.detailURL = dbRS.getString(dbRS.getColumnIndex(DiabloInfoModel.Table.DETAIL_URL));
				result.author = dbRS.getString(dbRS.getColumnIndex(DiabloInfoModel.Table.AUTHOR));
				result.publishDate = dbRS.getLong(dbRS.getColumnIndex(DiabloInfoModel.Table.PUBLISH_DATE));

				return result;
			} while (dbRS.moveToNext());
		} catch (Exception e) {
			NLog.e("DB ERROR", e);
		} finally {
			if (dbRS != null) {
				dbRS.close();
			}

			if (db != null) {
				db.close();
			}
		}

		return null;
	}

	public List<DiabloInfoModel> queryAll(int limit) {

	}
}
