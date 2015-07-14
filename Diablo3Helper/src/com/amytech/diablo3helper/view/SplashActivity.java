package com.amytech.diablo3helper.view;

import java.text.MessageFormat;
import java.util.Properties;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amytech.android.library.base.BaseActivity;
import com.amytech.android.library.utils.AppUtils;
import com.amytech.android.library.utils.FileUtils;
import com.amytech.android.library.utils.download.DownloadRequest;
import com.amytech.android.library.utils.download.DownloadStatusListener;
import com.amytech.android.library.utils.download.ThinDownloadManager;
import com.amytech.diablo3helper.R;
import com.amytech.diablo3helper.manager.Constants;

public class SplashActivity extends BaseActivity implements DownloadStatusListener {

	private DownloadRequest confFileRequest;
	private int confFileRequestID;

	private DownloadRequest dbFileRequest;
	private int dbFileRequestID;

	private int serverDBVersion;

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

		downloader = new ThinDownloadManager();
		confFileRequest = new DownloadRequest(Constants.URL_CONF_FILE);
		confFileRequest.setDownloadListener(this);
		confFileRequest.setDestinationFile(Constants.CONF_FILE);
		confFileRequestID = downloader.add(confFileRequest);

		dbFileRequest = new DownloadRequest(Constants.URL_SKILL_DB_FILE);
		dbFileRequest.setDownloadListener(this);
		dbFileRequest.setDestinationFile(Constants.DB_FILE_SKILL);
	}

	@Override
	public void onBackPressed() {
		if (confFileRequestID > 0) {
			downloader.cancel(confFileRequestID);
		}

		if (dbFileRequestID > 0) {
			downloader.cancel(dbFileRequestID);
		}
		AppUtils.exit();
	}

	@Override
	public void onDownloadComplete(int id) {
		if (id == confFileRequestID) {

			Properties conf = FileUtils.readFileToProp(Constants.CONF_FILE);

			serverDBVersion = Integer.parseInt(conf.getProperty(Constants.SP.SKILL_DB_VERSION));
			int localDBVersion = spUtils.getInt(Constants.SP.SKILL_DB_VERSION, 0);
			if (serverDBVersion > localDBVersion) {
				dbFileRequestID = downloader.add(dbFileRequest);
			} else {
				showNormalEnter();
			}
		}

		if (id == dbFileRequestID) {
			showNormalEnter();
			spUtils.putInt(Constants.SP.SKILL_DB_VERSION, serverDBVersion);
		}
	}

	@Override
	public void onDownloadFailed(int id, int errorCode, String errorMessage) {
		showErrorEnter();
	}

	@Override
	public void onProgress(int id, long totalBytes, long downloadedBytes, int progress) {
		if (id == dbFileRequestID) {
			updateProgress(progress, totalBytes, downloadedBytes);
		}
	}

	private void updateProgress(int progress, long totalBytes, long downloadedBytes) {
		enterButton.setVisibility(View.GONE);
		progressText.setText(MessageFormat.format(getString(R.string.update_offline_data), downloadedBytes, totalBytes));
		progressView.setVisibility(View.VISIBLE);
		progressView.setProgress(progress);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
			}
		});
	}

	private void showErrorEnter() {
		enterButton.setVisibility(View.VISIBLE);
		progressText.setText(R.string.server_error);
		progressView.setVisibility(View.GONE);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
			}
		});
	}

	private void showNormalEnter() {
		enterButton.setVisibility(View.VISIBLE);
		progressText.setText(R.string.please_enter);
		progressView.setVisibility(View.GONE);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(HomeActivity.class);
			}
		});
	}
}
