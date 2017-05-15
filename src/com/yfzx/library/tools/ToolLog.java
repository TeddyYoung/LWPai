package com.yfzx.library.tools;

import android.util.Log;

/**
 * 日志工具类
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-4
 */
public class ToolLog {

	private static final String TAG = "yfzx";

	/**
	 * 上线后关闭log
	 */
	private static final Boolean DEBUG = false;

	public static void d(String tag, String msg) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.d(TAG, tag + " : " + msg);
		}
	}

	public static void d(String tag, String msg, Throwable error) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.d(TAG, tag + " : " + msg, error);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.i(TAG, tag + " : " + msg);
		}
	}

	public static void i(String tag, String msg, Throwable error) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.i(TAG, tag + " : " + msg, error);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.w(TAG, tag + " : " + msg);
		}
	}

	public static void w(String tag, String msg, Throwable error) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.w(TAG, tag + " : " + msg, error);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.e(TAG, tag + " : " + msg);
		}
	}

	public static void e(String tag, String msg, Throwable error) {
		if (DEBUG) {
			tag = Thread.currentThread().getName() + ":" + tag;
			Log.e(TAG, tag + " : " + msg, error);
		}
	}

	/************************************ 默认输出 ****************************/
	public static void d(String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
		}
	}

	public static void d(String msg, Throwable error) {
		if (DEBUG) {
			Log.d(TAG, msg, error);
		}
	}

	public static void i(String msg) {
		if (DEBUG) {
			Log.i(TAG, msg);
		}
	}

	public static void i(String msg, Throwable error) {
		if (DEBUG) {
			Log.i(TAG, msg, error);
		}
	}

	public static void w(String msg) {
		if (DEBUG) {
			Log.w(TAG, msg);
		}
	}

	public static void w(String msg, Throwable error) {
		if (DEBUG) {
			Log.w(TAG, msg, error);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			Log.e(TAG, msg);
		}
	}

	public static void e(String msg, Throwable error) {
		if (DEBUG) {
			Log.e(TAG, msg, error);
		}
	}
}
