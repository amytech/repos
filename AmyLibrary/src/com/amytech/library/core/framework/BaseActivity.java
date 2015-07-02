package com.amytech.library.core.framework;

import android.app.Activity;
import android.os.Bundle;

import com.amytech.library.R;

/**
 * 基础Activity
 * 
 * @author AmyTech
 */
public abstract class BaseActivity extends Activity {

	private static final String TAG = "BaseActivity";

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_base);
	}
}
