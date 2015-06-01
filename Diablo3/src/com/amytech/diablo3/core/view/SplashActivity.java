package com.amytech.diablo3.core.view;

import com.amytech.diablo3.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Splash 界面
 * 
 * @author AmyTech
 */
public class SplashActivity extends Activity {

	private Button enterButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		enterButton = (Button) findViewById(R.id.splash_enter);
		enterButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			}
		});
	}
}
