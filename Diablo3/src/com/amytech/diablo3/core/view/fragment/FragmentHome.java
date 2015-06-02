package com.amytech.diablo3.core.view.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amytech.diablo3.R;
import com.amytech.diablo3.core.model.HomeFunctionModel;
import com.amytech.library.core.framework.BaseFragment;
import com.amytech.library.core.view.Topbar;
import com.amytech.library.utils.AppUtils;

public class FragmentHome extends BaseFragment {

	private Topbar topbar;

	private GridView homeFunctionGrid;
	private FunctionAdapter homeFunctionAdapter;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_home;
	}

	@Override
	protected void showToUser() {
		initTopbar();
		initFunctionGrid();
	}

	private void initFunctionGrid() {
		homeFunctionGrid = (GridView) findViewById(R.id.function_grid);
		homeFunctionAdapter = new FunctionAdapter();
		homeFunctionGrid.setAdapter(homeFunctionAdapter);
		homeFunctionGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				HomeFunctionModel item = (HomeFunctionModel) parent.getItemAtPosition(position);
				if (item.getFragment() != null) {
					startFragment(item.getFragment());
				} else {
					if (item == HomeFunctionModel.Exit) {
						AppUtils.exit();
					}
				}
			}
		});
	}

	private void initTopbar() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.configIcon(R.dimen.margin_small_more, R.drawable.ic_launcher, new OnClickListener() {
			public void onClick(View v) {

			}
		});
		topbar.configTitle(R.string.app_name);
	}

	private class FunctionAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;

		public FunctionAdapter() {
			layoutInflater = LayoutInflater.from(getBaseActivity());
		}

		@Override
		public int getCount() {
			return HomeFunctionModel.values().length;
		}

		@Override
		public HomeFunctionModel getItem(int position) {
			return HomeFunctionModel.values()[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = layoutInflater.inflate(R.layout.item_home_function, parent, false);

			HomeFunctionModel item = getItem(position);

			ImageView iconView = (ImageView) convertView.findViewById(R.id.item_func_icon);
			TextView nameView = (TextView) convertView.findViewById(R.id.item_func_name);

			iconView.setImageResource(item.getIconRes());
			nameView.setText(item.getNameRes());

			return convertView;
		}
	}
}
