package com.amytech.diablo3helper.view.skill;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.amytech.android.library.utils.ImageUtils;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.SkillManager;
import com.amytech.diablo3helper.model.DiabloSkillAdvanceModel;
import com.amytech.diablo3helper.model.DiabloSkillModel;

/**
 * Title: Diablo3Helper <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年7月16日 下午5:35:36 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年7月16日 <br>
 *
 * @author marktlzhai
 */
public class SkillPickerDialog extends Dialog {

	private SkillItemView skillItemView;

	private ExpandableListView skillListView;
	private SkillAdapter skillAdapter;

	private TextView skillInfoDisplay;

	private List<DiabloSkillModel> skillDatas;

	public SkillPickerDialog(Context context, SkillItemView skillItemView, List<DiabloSkillModel> skillDatas) {
		super(context, R.style.FullScreenDialog);

		this.skillItemView = skillItemView;
		this.skillDatas = skillDatas;

		init();
	}

	private void init() {
		setContentView(R.layout.dialog_skill_picker);
		getWindow().setWindowAnimations(R.style.DialogPopAnim);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.BOTTOM);

		skillListView = (ExpandableListView) findViewById(R.id.skill_list);
		skillAdapter = new SkillAdapter();
		skillListView.setAdapter(skillAdapter);
		skillListView.setOnGroupClickListener(new SkillItemClickListener());
		skillListView.setOnChildClickListener(new SkillAdvanceItemClickListener());

		skillInfoDisplay = (TextView) findViewById(R.id.skill_info);
	}

	class SkillItemClickListener implements OnGroupClickListener {
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
			DiabloSkillModel skill = skillAdapter.getGroup(groupPosition);

			skillInfoDisplay.setText(skill.skillName + "\n\n" + skill.skillDesc);

			return false;
		}
	}

	class SkillAdvanceItemClickListener implements OnChildClickListener {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			DiabloSkillModel skill = skillAdapter.getGroup(groupPosition);
			DiabloSkillAdvanceModel skillAdvance = skillAdapter.getChild(groupPosition, childPosition);

			skillInfoDisplay.setText(skill.skillName + "\n" + skill.skillDesc + "\n\n" + skillAdvance.skillAdvanceName + "\n" + skillAdvance.skillAdvanceDesc);

			return false;
		}

	}

	class SkillAdapter extends BaseExpandableListAdapter {

		private LayoutInflater inflater;

		public SkillAdapter() {
			inflater = LayoutInflater.from(getContext());
		}

		@Override
		public int getGroupCount() {
			return skillDatas.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// DiabloSkillModel groupItem = getGroup(groupPosition);
			// return SkillManager.getSkillAdvanceData().get(groupItem.jobName + groupItem.skillName).size();
			return 0;
		}

		@Override
		public DiabloSkillModel getGroup(int groupPosition) {
			return skillDatas.get(groupPosition);
		}

		@Override
		public DiabloSkillAdvanceModel getChild(int groupPosition, int childPosition) {
//			DiabloSkillModel groupItem = getGroup(groupPosition);
//			return SkillManager.getSkillAdvanceData().get(groupItem.jobName + groupItem.skillName).get(childPosition);
			return null;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.item_skill, parent, false);

			DiabloSkillModel item = getGroup(groupPosition);

			ImageView skillIcon = (ImageView) convertView.findViewById(R.id.skill_icon);
			TextView skillName = (TextView) convertView.findViewById(R.id.skill_name);
			ImageView expandIcon = (ImageView) convertView.findViewById(R.id.expandable_icon);

			ImageUtils.displayImage(skillIcon, item.skillIcon, ImageUtils.getDefaultDisplayOptions());
			skillName.setText(item.skillName);
			if (isExpanded) {
				expandIcon.setImageResource(R.drawable.icon_arrow_top);
			} else {
				expandIcon.setImageResource(R.drawable.icon_arrow_bottom);
			}

			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//			convertView = inflater.inflate(R.layout.item_skill_advance, parent, false);
//
//			DiabloSkillAdvanceModel item = getChild(groupPosition, childPosition);
//
//			ImageView skillAdvanceIcon = (ImageView) convertView.findViewById(R.id.skill_advance_icon);
//			TextView skillAdvanceName = (TextView) convertView.findViewById(R.id.skill_advance_name);
//
//			skillAdvanceIcon.setImageResource(SkillManager.SKILL_ADVANCE_ICONS[item.skillAdvanceIcon]);
//			skillAdvanceName.setText(item.skillAdvanceName);

			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}
