package com.amytech.diablo3helper.view;

import java.text.MessageFormat;

import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.FileUtils;
import com.amytech.android.library.utils.download.DownloadUtils;
import com.amytech.android.library.utils.download.DownloadUtils.OnDownloadListener;
import com.amytech.diablo3helper.R;

public class SplashActivity extends BaseActivity implements OnDownloadListener {

	private static final String TEST_URL = "https://github.com/amytech/repos/raw/master/Files/DiabloHelper.conf";

	private Button enterButton;

	private TextView progressText;

	private ProgressBar progress;

	private DownloadUtils downloader;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		enterButton = (Button) findViewById(R.id.splash_enter_btn);

		progressText = (TextView) findViewById(R.id.progress_text);
		progress = (ProgressBar) findViewById(R.id.splash_progress);
		progress.setVisibility(View.VISIBLE);

		downloader = new DownloadUtils(Environment.getExternalStorageState(), "DiabloHelper.conf", TEST_URL, this);
		downloader.setOnDownloadListener(this);
		downloader.start();
	}

	@Override
	public void onBackPressed() {
		downloader.delete();
		AppUtils.exit();
	}

	private int fileSize;

	@Override
	public void downloadStart(int fileSize) {
		this.fileSize = fileSize;
		progress.setVisibility(View.VISIBLE);

		progress.setMax(fileSize);
		progress.setProgress(0);
	}

	@Override
	public void downloadProgress(int downloadedSize) {
		progressText.setText(MessageFormat.format(getString(R.string.update_offline_data), downloadedSize, fileSize));
		progress.setProgress(downloadedSize);
	}

	@Override
	public void downloadEnd() {
		progress.setVisibility(View.GONE);
		progressText.setText(R.string.please_enter);
		enterButton.setVisibility(View.VISIBLE);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
				finish();
			}
		});
	}

	@Override
	public void downloadError() {
		progress.setVisibility(View.GONE);
		progressText.setText(R.string.download_error);

		enterButton.setVisibility(View.VISIBLE);
		enterButton.setText(R.string.retry);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				downloader.reset();
				downloader.start();
			}
		});
	}
}
