package com.amytech.diablo3helper.view;

import java.text.MessageFormat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.download.DownloadRequest;
import com.amytech.android.library.utils.download.DownloadStatusListener;
import com.amytech.android.library.utils.download.ThinDownloadManager;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.Constants;

public class SplashActivity extends BaseActivity implements DownloadStatusListener {

	private int downloadID = -1;

	private DownloadRequest downloadRequest;

	private Button enterButton;

	private TextView progressText;

	private ProgressBar progressView;

	private ThinDownloadManager downloader;

	@Override
	protected int getLayoutID() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		enterButton = (Button) findViewById(R.id.splash_enter_btn);

		progressText = (TextView) findViewById(R.id.progress_text);
		progressView = (ProgressBar) findViewById(R.id.splash_progress);
		progressView.setVisibility(View.VISIBLE);

		if (Constants.DB_FILE_SKILL.exists()) {
			onDownloadComplete(0);
		} else {
			downloader = new ThinDownloadManager();
			downloadRequest = new DownloadRequest(Constants.URL_SKILL_DB_FILE);
			downloadRequest.setDownloadListener(this);
			downloadRequest.setDestinationFile(Constants.DB_FILE_SKILL);
			downloadID = downloader.add(downloadRequest);
		}
	}

	@Override
	public void onBackPressed() {
		if (downloadID > 0) {
			downloader.cancel(downloadID);
		}
		AppUtils.exit();
	}

	@Override
	public void onDownloadComplete(int id) {
		progressView.setVisibility(View.GONE);
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
	public void onDownloadFailed(int id, int errorCode, String errorMessage) {
		progressView.setVisibility(View.GONE);
		progressText.setText(R.string.download_error);

		enterButton.setVisibility(View.VISIBLE);
		enterButton.setText(R.string.retry);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				downloadID = downloader.add(downloadRequest);
			}
		});
	}

	@Override
	public void onProgress(int id, long totalBytes, long downloadedBytes, int progress) {
		progressText.setText(MessageFormat.format(getString(R.string.update_offline_data), downloadedBytes, totalBytes));
		progressView.setProgress(progress);
	}
}
