package com.yfzx.lwpai.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕大小有关
 * 
 * @author <a href="http://www.xunhou.me" target="_blank">Kelvin</a>
 *
 */
public class ScreenHelper {
	
	/**
	 * 获取手机屏幕DisplayMetrics属性
	 * 
	 * @param activity
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		if (context == null) {
			return null;
		}
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}
	
	/**
	 * 获取屏幕的大小
	 * 
	 * @param context
	 * @return
	 */
	public static Screen getScreenPix(Context context) {
		DisplayMetrics dm = getDisplayMetrics(context);
		return new Screen(dm.widthPixels, dm.heightPixels);
	}

	/**
	 * 获取屏幕的宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidthPix(Context context) {
		return getScreenPix(context).widthPixels;
	}

	/**
	 * 获取屏幕的高
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeightPix(Context context) {
		return getScreenPix(context).heightPixels;
	}
	
	/**
	 * 获取当前宽和高
	 * 
	 * @param activity
	 * @return
	 */
	public static String getWidthAndHeight(Context context) {
		return getScreenHeightPix(context) + "*" + getScreenWidthPix(context);
	}

	public static class Screen {
		public int widthPixels;
		public int heightPixels;

		public Screen() {
		}

		public Screen(int widthPixels, int heightPixels) {
			this.widthPixels = widthPixels;
			this.heightPixels = heightPixels;
		}

		@Override
		public String toString() {
			return "(" + widthPixels + "," + heightPixels + ")";
		}
	}
}
