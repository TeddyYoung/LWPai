package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.os.Handler;

import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.util.Helper;

public class SplashActivity extends BaseActivity {
	// 启动页停滞事件
	private final int SPLASH_DISPLAY_LENGHT = 1500;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (CacheManage.getAccessor().getBoolean("guide_page", true)) {
					CacheManage.getAccessor().put("guide_page", false);
					Helper.starAct(SplashActivity.this, GuidePageActivity.class);
				} else {
					Helper.starAct(SplashActivity.this, MainActivity.class);
				}
				finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
	}
}
