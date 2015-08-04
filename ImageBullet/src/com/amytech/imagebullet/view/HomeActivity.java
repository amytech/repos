package com.amytech.imagebullet.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.StringUtils;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.android.library.views.imagechooser.api.ChooserType;
import com.amytech.android.library.views.imagechooser.api.ChosenImage;
import com.amytech.android.library.views.imagechooser.api.ImageChooserListener;
import com.amytech.android.library.views.imagechooser.api.ImageChooserManager;
import com.amytech.imagebullet.R;
import com.amytech.imagebullet.view.drawer.LeftDrawer;
import com.amytech.imagebullet.view.drawer.LeftDrawer.DrawerItem;
import com.amytech.imagebullet.view.drawer.LeftDrawer.LeftDrawerListener;

/**
 * Title: ImageBullet <br>
 * Description: <br>
 * Copyright: Copyright (c) 2015 <br>
 * Create DateTime: 2015年8月4日 下午5:22:37 <br>
 * SVN create person: marktlzhai <br>
 * SVN create DateTime: 2015年8月4日 <br>
 *
 * @author marktlzhai
 */
public class HomeActivity extends BaseActivity implements LeftDrawerListener, ImageChooserListener {

	private Topbar topbar;

	private DrawerLayout drawer;

	private LeftDrawer leftDrawer;

	private int chooserType;
	private String filePath;
	private ImageChooserManager imageChooserManager;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_home;
	}

	@Override
	protected void initViews() {
		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.setTitle(R.string.drawer_hot);
		topbar.configLeftImgBtn(TopbarIcon.MENU, new OnClickListener() {
			public void onClick(View v) {
				drawer.openDrawer(Gravity.START);
			}
		});
		topbar.configRightImgBtn(TopbarIcon.CAMERA, new OnClickListener() {
			public void onClick(View v) {
				List<Object> menuList = new ArrayList<Object>();
				menuList.add(getString(R.string.take_picture));
				menuList.add(getString(R.string.choose_picture));

				showBottomMenu(menuList, new BottomMenuCallback() {
					@Override
					public void onMenuClicked(int position, Object item) {
						if (item.toString().equals(getString(R.string.take_picture))) {
							takePicture();
						} else {
							chooseImage();
						}
					}

					@Override
					public void onMenuCancel() {

					}
				});
			}
		});

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		leftDrawer = (LeftDrawer) findViewById(R.id.left_drawer);
		leftDrawer.setDrawerListener(this);
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(Gravity.START)) {
			drawer.closeDrawer(Gravity.START);
		} else {
			AppUtils.exit(this);
		}
	}

	@Override
	public void onDrawerItemClick(final DrawerItem item) {
		drawer.closeDrawer(Gravity.START);
		handler.postDelayed(new Runnable() {
			public void run() {
				showToast(getString(item.getTxtRes()) + " clicked.");
			}
		}, 500);
	}

	private void chooseImage() {
		chooserType = ChooserType.REQUEST_PICK_PICTURE;
		imageChooserManager = new ImageChooserManager(this, ChooserType.REQUEST_PICK_PICTURE, true);
		imageChooserManager.setImageChooserListener(this);
		try {
			filePath = imageChooserManager.choose();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void takePicture() {
		chooserType = ChooserType.REQUEST_CAPTURE_PICTURE;
		imageChooserManager = new ImageChooserManager(this, ChooserType.REQUEST_CAPTURE_PICTURE, true);
		imageChooserManager.setImageChooserListener(this);
		try {
			filePath = imageChooserManager.choose();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reinitializeImageChooser() {
		imageChooserManager = new ImageChooserManager(this, chooserType, true);
		imageChooserManager.setImageChooserListener(this);
		imageChooserManager.reinitialize(filePath);
	}

	@Override
	public void onImageChosen(final ChosenImage image) {
		handler.post(new Runnable() {
			public void run() {
				showToast(image.getFilePathOriginal() + "\n" + image.getFileThumbnail() + "\n" + image.getFileThumbnailSmall());
			}
		});
	}

	@Override
	public void onError(final String reason) {
		handler.post(new Runnable() {
			public void run() {
				if (StringUtils.isNotEmpty(reason)) {
					showToast(reason);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && (requestCode == ChooserType.REQUEST_PICK_PICTURE || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)) {
			if (imageChooserManager == null) {
				reinitializeImageChooser();
			}
			imageChooserManager.submit(requestCode, data);
		}
	}
}
