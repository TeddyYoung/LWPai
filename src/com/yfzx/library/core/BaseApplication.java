package com.yfzx.library.core;

import android.app.Application;
import android.content.Context;

public abstract class BaseApplication extends Application {

	public static BaseApplication self;
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		self = this;
	}

	public static Context getContext() {
		return context;
	}
}
