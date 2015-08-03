package com.amytech.tot.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.tot.R;

/**
 * Title: TOT <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月3日 下午6:06:33 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月3日 <br>
 *
 * @author marktlzhai
 */
public class PublishTOTActivity extends BaseActivity {

	private Topbar topbar;

	private EditText titleView;
	private EditText descView;

	private EditText item1Context;
	private EditText item2Context;
	private EditText item3Context;
	private EditText item4Context;
	private EditText item5Context;
	private EditText item6Context;

	private View item3Layout;
	private View item4Layout;
	private View item5Layout;
	private View item6Layout;

	private LinearLayout item1ImageLayout;
	private LinearLayout item2ImageLayout;
	private LinearLayout item3ImageLayout;
	private LinearLayout item4ImageLayout;
	private LinearLayout item5ImageLayout;
	private LinearLayout item6ImageLayout;

	private ImageView item1AddImageButton;
	private ImageView item2AddImageButton;
	private ImageView item3AddImageButton;
	private ImageView item4AddImageButton;
	private ImageView item5AddImageButton;
	private ImageView item6AddImageButton;

	private View addItemView;

	private RadioGroup modeGroup;

	private Button submitButton;

	private int itemShowingCount = 2;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_publish_tot;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.configLeftImgBtn(TopbarIcon.BACK, new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		titleView = (EditText) findViewById(R.id.publish_title);
		descView = (EditText) findViewById(R.id.publish_desc);

		item1Context = (EditText) findViewById(R.id.publish_item1_context);
		item2Context = (EditText) findViewById(R.id.publish_item2_context);
		item3Context = (EditText) findViewById(R.id.publish_item3_context);
		item4Context = (EditText) findViewById(R.id.publish_item4_context);
		item5Context = (EditText) findViewById(R.id.publish_item5_context);
		item6Context = (EditText) findViewById(R.id.publish_item6_context);

		item3Layout = findViewById(R.id.publish_item3_layout);
		item4Layout = findViewById(R.id.publish_item4_layout);
		item5Layout = findViewById(R.id.publish_item5_layout);
		item6Layout = findViewById(R.id.publish_item6_layout);

		item1ImageLayout = (LinearLayout) findViewById(R.id.publish_item1_images);
		item2ImageLayout = (LinearLayout) findViewById(R.id.publish_item2_images);
		item3ImageLayout = (LinearLayout) findViewById(R.id.publish_item3_images);
		item4ImageLayout = (LinearLayout) findViewById(R.id.publish_item4_images);
		item5ImageLayout = (LinearLayout) findViewById(R.id.publish_item5_images);
		item6ImageLayout = (LinearLayout) findViewById(R.id.publish_item6_images);

		item1AddImageButton = (ImageView) findViewById(R.id.publish_item1_image_add);
		item2AddImageButton = (ImageView) findViewById(R.id.publish_item2_image_add);
		item3AddImageButton = (ImageView) findViewById(R.id.publish_item3_image_add);
		item4AddImageButton = (ImageView) findViewById(R.id.publish_item4_image_add);
		item5AddImageButton = (ImageView) findViewById(R.id.publish_item5_image_add);
		item6AddImageButton = (ImageView) findViewById(R.id.publish_item6_image_add);

		addItemView = findViewById(R.id.publish_item_add);
		addItemView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (itemShowingCount == 2) {
					item3Layout.setVisibility(View.VISIBLE);
				}
				if (itemShowingCount == 3) {
					item4Layout.setVisibility(View.VISIBLE);
				}
				if (itemShowingCount == 4) {
					item5Layout.setVisibility(View.VISIBLE);
				}
				if (itemShowingCount == 5) {
					item6Layout.setVisibility(View.VISIBLE);
				}
				if (itemShowingCount >= 6) {
					showToast(R.string.publish_item_add_error);
				}
				itemShowingCount++;
			}
		});

		modeGroup = (RadioGroup) findViewById(R.id.publish_mode_group);

		submitButton = (Button) findViewById(R.id.publish_submit);
	}
}
