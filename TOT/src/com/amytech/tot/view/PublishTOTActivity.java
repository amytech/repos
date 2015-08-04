package com.amytech.tot.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import cn.bmob.v3.listener.SaveListener;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.StringUtils;
import com.amytech.android.library.utils.tietuku.TieTuKuManager;
import com.amytech.android.library.utils.tietuku.TieTuKuManager.UploadListener;
import com.amytech.android.library.utils.tietuku.UploadResponse;
import com.amytech.android.library.views.Topbar;
import com.amytech.android.library.views.Topbar.TopbarIcon;
import com.amytech.android.library.views.imagechooser.api.ChooserType;
import com.amytech.android.library.views.imagechooser.api.ChosenImage;
import com.amytech.android.library.views.imagechooser.api.ImageChooserListener;
import com.amytech.android.library.views.imagechooser.api.ImageChooserManager;
import com.amytech.tot.R;
import com.amytech.tot.TOTApp;
import com.amytech.tot.model.TOTModel;

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
public class PublishTOTActivity extends BaseActivity implements ImageChooserListener {

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

	private ImageAddClick imageAddClick;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_publish_tot;
	}

	@Override
	protected void initViews() {
		imageAddClick = new ImageAddClick();

		topbar = (Topbar) findViewById(R.id.topbar);
		topbar.configLeftImgBtn(TopbarIcon.BACK, new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		topbar.setTitle(R.string.publish);

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

		item1AddImageButton = (ImageView) findViewById(R.id.publish_item1_image_add);
		item2AddImageButton = (ImageView) findViewById(R.id.publish_item2_image_add);
		item3AddImageButton = (ImageView) findViewById(R.id.publish_item3_image_add);
		item4AddImageButton = (ImageView) findViewById(R.id.publish_item4_image_add);
		item5AddImageButton = (ImageView) findViewById(R.id.publish_item5_image_add);
		item6AddImageButton = (ImageView) findViewById(R.id.publish_item6_image_add);
		item1AddImageButton.setOnClickListener(imageAddClick);
		item2AddImageButton.setOnClickListener(imageAddClick);
		item3AddImageButton.setOnClickListener(imageAddClick);
		item4AddImageButton.setOnClickListener(imageAddClick);
		item5AddImageButton.setOnClickListener(imageAddClick);
		item6AddImageButton.setOnClickListener(imageAddClick);

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
		submitButton.setOnClickListener(new SubmitClick());
	}

	class ImageAddClick implements OnClickListener {
		@Override
		public void onClick(final View v) {
			List<Object> menuList = new ArrayList<Object>();
			menuList.add(getString(R.string.take_picture));
			menuList.add(getString(R.string.choose_picture));

			showBottomMenu(menuList, new BottomMenuCallback() {
				@Override
				public void onMenuClicked(int position, Object item) {

					int id = v.getId();
					switch (id) {
					case R.id.publish_item1_image_add:
						imageLayout = item1AddImageButton;
						break;
					case R.id.publish_item2_image_add:
						imageLayout = item2AddImageButton;
						break;
					case R.id.publish_item3_image_add:
						imageLayout = item3AddImageButton;
						break;
					case R.id.publish_item4_image_add:
						imageLayout = item4AddImageButton;
						break;
					case R.id.publish_item5_image_add:
						imageLayout = item5AddImageButton;
						break;
					case R.id.publish_item6_image_add:
						imageLayout = item6AddImageButton;
						break;
					}

					if (item.toString().equals(getString(R.string.take_picture))) {
						takePicture();
					} else {
						chooseImage();
					}
				}

				@Override
				public void onMenuCancel() {
					imageLayout = null;
				}
			});
		}
	}

	private boolean item1Uploaded = false;
	private boolean item2Uploaded = false;
	private boolean item3Uploaded = false;
	private boolean item4Uploaded = false;
	private boolean item5Uploaded = false;
	private boolean item6Uploaded = false;
	private boolean hasError = false;

	private void checkImageAllUpload(boolean isError, final TOTModel postData) {
		if (isError) {
			hasError = true;
			showToast(R.string.publish_err_upload);
			hideLoadingDialog();
			return;
		}

		if (item1Uploaded && item2Uploaded && item3Uploaded && item4Uploaded && item5Uploaded && item6Uploaded) {
			saveData(postData);
		}
	}

	private void saveData(TOTModel postData) {
		postData.save(this, new SaveListener() {
			public void onSuccess() {
				showToast(R.string.publish_success);
				finish();
			}

			public void onFailure(int arg0, String arg1) {
				showToast(arg1);
			}
		});
	}

	private int itemCount = 0;

	class SubmitClick implements OnClickListener {
		public void onClick(View v) {
			hasError = false;
			itemCount = 0;

			if (StringUtils.isEmpty(titleView.getText())) {
				showToast(R.string.publish_err_title);
				return;
			}

			itemCount += (StringUtils.isNotEmpty(item1Context.getText().toString()) || item1AddImageButton.getTag() != null) ? 1 : 0;
			itemCount += (StringUtils.isNotEmpty(item2Context.getText().toString()) || item2AddImageButton.getTag() != null) ? 1 : 0;
			itemCount += (StringUtils.isNotEmpty(item3Context.getText().toString()) || item3AddImageButton.getTag() != null) ? 1 : 0;
			itemCount += (StringUtils.isNotEmpty(item4Context.getText().toString()) || item4AddImageButton.getTag() != null) ? 1 : 0;
			itemCount += (StringUtils.isNotEmpty(item5Context.getText().toString()) || item5AddImageButton.getTag() != null) ? 1 : 0;
			itemCount += (StringUtils.isNotEmpty(item6Context.getText().toString()) || item6AddImageButton.getTag() != null) ? 1 : 0;
			if (itemCount < 2) {
				showToast(R.string.publish_err_item);
				return;
			}

			showLoadingDialog(R.string.waitting, false);

			final TOTModel postData = new TOTModel();
			postData.setTitle(titleView.getText().toString());
			postData.setDesc(descView.getText().toString());
			postData.setItem1(item1Context.getText().toString());
			postData.setItem2(item2Context.getText().toString());
			postData.setItem3(item3Context.getText().toString());
			postData.setItem4(item4Context.getText().toString());
			postData.setItem5(item5Context.getText().toString());
			postData.setItem6(item6Context.getText().toString());
			postData.setIsSingle(modeGroup.getCheckedRadioButtonId() == R.id.publish_mode_s);

			Object item1Img = item1AddImageButton.getTag();
			Object item2Img = item2AddImageButton.getTag();
			Object item3Img = item3AddImageButton.getTag();
			Object item4Img = item4AddImageButton.getTag();
			Object item5Img = item5AddImageButton.getTag();
			Object item6Img = item6AddImageButton.getTag();

			if (item1Img == null && item2Img == null && item3Img == null && item4Img == null && item5Img == null && item6Img == null) {
				saveData(postData);
				return;
			}

			// 上传item1图片
			if (!hasError && item1Img != null && item1Img instanceof ChosenImage) {
				File item1ImageFile = new File(((ChosenImage) item1Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item1ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item1Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem1Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item1Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item1Uploaded = true;
			}

			// 上传item2图片
			if (!hasError && item2Img != null && item2Img instanceof ChosenImage) {
				File item2ImageFile = new File(((ChosenImage) item2Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item2ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item2Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem2Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item2Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item2Uploaded = true;
			}

			// 上传item3图片
			if (!hasError && item3Img != null && item3Img instanceof ChosenImage) {
				File item3ImageFile = new File(((ChosenImage) item3Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item3ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item3Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem3Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item3Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item3Uploaded = true;
			}

			// 上传item4图片
			if (!hasError && item4Img != null && item4Img instanceof ChosenImage) {
				File item4ImageFile = new File(((ChosenImage) item4Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item4ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item4Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem4Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item4Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item4Uploaded = true;
			}

			// 上传item5图片
			if (!hasError && item5Img != null && item5Img instanceof ChosenImage) {
				File item5ImageFile = new File(((ChosenImage) item5Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item5ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item5Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem5Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item5Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item5Uploaded = true;
			}

			// 上传item6图片
			if (!hasError && item6Img != null && item6Img instanceof ChosenImage) {
				File item6ImageFile = new File(((ChosenImage) item6Img).getFilePathOriginal());
				TieTuKuManager.getInstance(PublishTOTActivity.this, TOTApp.IMAGE_TOKEN).uploadFile(item6ImageFile, new UploadListener() {
					public void onSuccess(UploadResponse response) {
						item6Uploaded = true;
						checkImageAllUpload(false, postData);
						postData.setItem6Image(response.imageURL);
					}

					public void onStart() {
					}

					public void onProgressUpdate(long uploadedSize, long totalSize) {
					}

					public void onFailure() {
						item6Uploaded = false;
						checkImageAllUpload(true, postData);
					}
				});
			} else {
				item6Uploaded = true;
			}
		}
	}

	private ImageView imageLayout = null;
	private int chooserType;
	private String filePath;
	private ImageChooserManager imageChooserManager;

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
				imageLayout.setImageBitmap(BitmapFactory.decodeFile(image.getFileThumbnailSmall()));
				imageLayout.setTag(image);
				imageLayout.setOnLongClickListener(new OnLongClickListener() {
					public boolean onLongClick(View v) {
						imageLayout.setImageResource(R.drawable.image_add);
						imageLayout.setTag(null);
						return true;
					}
				});
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
		imageLayout = null;
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
