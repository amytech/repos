package com.amytech.diablo3.core.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.amytech.diablo3.R;
import com.amytech.library.core.framework.BaseFragment;

public class FragmentNews extends BaseFragment {

	private ListView newsList;

	@Override
	protected int getLayoutID() {
		return R.layout.fragment_news;
	}

	@Override
	protected void showToUser() {
		newsList = (ListView) findViewById(R.id.news_listview);
	}

	class NewsAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;

		public NewsAdapter() {
			layoutInflater = LayoutInflater.from(getBaseActivity());
		}

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
	}
}
