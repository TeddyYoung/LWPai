package com.yfzx.library.tools;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class ToolStorage {

	public static boolean isExternalStorageExist() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	public static String getExternStorage() {
		if (isExternalStorageExist()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return "";
	}

	public static String getHomeDir(Context context) {
		if(isExternalStorageExist()){
			return getExternStorage() + File.separator + context.getPackageName();
		}else{
			return context.getFilesDir().getAbsolutePath();
		}
	}

	public static String getOtherDir(Context activity, String dir) {
		File file = new File(getHomeDir(activity) + File.separator + dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	public static String getLogDir(Context context) {
		return getOtherDir(context, "Log");
	}

	public static String getMusicDir(Context context) {
		return getOtherDir(context, "music");
	}

	public static String getVideoDir(Context context) {
		return getOtherDir(context, "video");
	}
	
	public static String getVoiceDir(Context context) {
		return getOtherDir(context, "voice");
	}

	public static String getDataDir(Context context) {
		return getOtherDir(context, "data");
	}

	public static String getCacheDir(Context context) {
		return getOtherDir(context, "cache");
	}

	public static String getImageDir(Context context) {
		return getOtherDir(context, "image");
	}

	public static String getThumbDir(Context context) {
		return getOtherDir(context, "thumb");
	}
	
	public static String getAvatorDir(Context context) {
		return getOtherDir(context, "avator");
	}

	/**
	 * @param Activity
	 * @param dirName Only the folder name, not full path.
	 * @return app_cache_path/dirName
	 */
	public static String getDiskCacheDir(Context context, String dirName) {
		String cachePath = null;
		if (isExternalStorageExist()) {
			File externalCacheDir = context.getExternalCacheDir();
			if (externalCacheDir != null) {
				cachePath = externalCacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = context.getCacheDir();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}

		return cachePath + File.separator + dirName;
	}

}
