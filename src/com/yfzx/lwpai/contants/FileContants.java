package com.yfzx.lwpai.contants;

import java.io.File;

import android.os.Environment;

public class FileContants {

	public static final String DIR_BASE = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "LWPai"
			+ File.separator;
	public static final String DIR_LOG = DIR_BASE + "log" + File.separator;
	public static final String DIR_APK = DIR_BASE + "apk" + File.separator;
}
