package com.amytech.library.core.framework;

import android.os.Bundle;

import com.amytech.library.BuildConfig;
import com.amytech.library.R;
import com.amytech.library.utils.NLog;
import com.fragmentmaster.app.FragmentMaster;
import com.fragmentmaster.app.IMasterFragment;
import com.fragmentmaster.app.MasterActivity;
import com.fragmentmaster.app.Request;

/**
 * 基础Activity
 * 
 * @author AmyTech
 */
public abstract class BaseActivity extends MasterActivity {

	private static final String TAG = "BaseActivity";

	private FragmentMaster.FragmentLifecycleCallbacks mLifecycleCallbacks = new FragmentMaster.SimpleFragmentLifecycleCallbacks() {
		@Override
		public void onFragmentResumed(IMasterFragment fragment) {
			if (BuildConfig.DEBUG)
				NLog.d(TAG, "[onResume]     " + fragment.toString());
		}

		@Override
		public void onFragmentActivated(IMasterFragment fragment) {
			if (BuildConfig.DEBUG)
				NLog.d(TAG, "[onActivated]  " + fragment.toString());
		}

		@Override
		public void onFragmentDeactivated(IMasterFragment fragment) {
			if (BuildConfig.DEBUG)
				NLog.d(TAG, "[onDeactivated]" + fragment.toString());
		}

		@Override
		public void onFragmentPaused(IMasterFragment fragment) {
			if (BuildConfig.DEBUG)
				NLog.d(TAG, "[onPaused]     " + fragment.toString());
		}
	};

	protected abstract Class<? extends BaseFragment> getHomeFragment();

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_base);

		FragmentMaster fragmentMaster = getFragmentMaster();
		fragmentMaster.registerFragmentLifecycleCallbacks(mLifecycleCallbacks);
		fragmentMaster.install(R.id.container, new Request(getHomeFragment()), true);
	}
}
