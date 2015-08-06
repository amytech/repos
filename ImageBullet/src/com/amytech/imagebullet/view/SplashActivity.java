package com.amytech.imagebullet.view;

import java.io.File;
import java.util.List;

import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.imagebullet.R;

public class SplashActivity extends BaseActivity implements OnClickListener {

	private Button loginQQButton;

	private Button enterLookButton;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		loginQQButton = (Button) findViewById(R.id.login_qq);
		enterLookButton = (Button) findViewById(R.id.enter_look);

		loginQQButton.setOnClickListener(this);
		enterLookButton.setOnClickListener(this);

		BmobQuery<test_upload_file> query = new BmobQuery<test_upload_file>();
		query.getObject(this, "63c9cd1df7", new GetListener<test_upload_file>() {

			@Override
			public void onSuccess(test_upload_file obj) {
				showToast(obj.getFile().getFilename() + "\n" + obj.getFile().getUrl() + "\n" + obj.getFile().getFileUrl(SplashActivity.this));
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				showToast("failure, " + arg0 + "\n" + arg1);
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.login_qq:

			break;
		case R.id.enter_look:
			startActivity(HomeActivity.class);
			finish();
			break;
		}
	}

	class test_upload_file extends BmobObject {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7489996398428424665L;

		private BmobFile file;

		public BmobFile getFile() {
			return file;
		}

		public void setFile(BmobFile file) {
			this.file = file;
		}
	}
}
