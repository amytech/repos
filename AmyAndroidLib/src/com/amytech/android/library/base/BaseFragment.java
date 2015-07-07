package com.amytech.android.library.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Title: AmyAndroidLib <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月7日 下午3:41:34 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月7日 <br>
 *
 * @author marktlzhai
 */
public abstract class BaseFragment extends Fragment {
	protected String TAG = getClass().getSimpleName();

	protected abstract int getLayoutID();

	protected abstract void initViews();

	protected View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(getLayoutID(), container, false);

			initViews();
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();

			if (parent != null) {
				parent.removeViewInLayout(rootView);
			}
		}
		return rootView;
	}

	public View findViewById(int id) {
		return rootView.findViewById(id);
	}
}
