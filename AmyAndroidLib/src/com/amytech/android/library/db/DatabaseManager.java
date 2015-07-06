package com.amytech.android.library.db;

import java.util.List;

import android.content.ContentValues;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月6日 下午2:29:13 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月6日 <br>
 *
 * @author marktlzhai
 */
public abstract class DatabaseManager {
	abstract void add(ContentValues values);

	abstract void addAll(List<ContentValues> values);

	abstract void delete(String whereClause, String[] whereArgs);

	abstract void update(ContentValues values, String whereClause, String[] whereArgs);

	abstract void query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

	abstract void queryAll(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
}
