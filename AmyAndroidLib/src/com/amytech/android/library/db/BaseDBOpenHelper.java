package com.amytech.android.library.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月6日 上午11:57:34 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月6日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseDBOpenHelper extends SQLiteOpenHelper {

	public BaseDBOpenHelper(Context context, int version, String dbName) {
		super(context, dbName, null, version);
	}
}
