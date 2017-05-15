package com.yfzx.lwpai.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yfzx.library.tools.ToolLog;
import com.yfzx.lwpai.R;

/**
 * 加载进度条工具类 单例
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-29
 */
public class ProgressHelper {

	private static ProgressHelper sInstance = null;

	public static ProgressHelper getInstance() {
		if (sInstance == null) {
			synchronized (ProgressHelper.class) {
				if (sInstance == null) {
					sInstance = new ProgressHelper();
				}
			}
		}
		return sInstance;
	}

	private ProgressDialog mDialog = null;

	private ProgressHelper() {
	}

	public void show(Context context, boolean isCancel) {
		cancel();
		mDialog = new ProgressDialog(context, R.style.Dialog);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setCancelable(isCancel);
		mDialog.show();
		View v = LayoutInflater.from(context).inflate(R.layout.dialog_progress,
				null);
		mDialog.setContentView(v);
	}

	public void show(Context context, String message, boolean isCancel) {
		cancel();
		mDialog = new ProgressDialog(context, R.style.Dialog);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setCancelable(isCancel);
		if (context == null || mDialog == null) {
			return;
		}
		try {
			mDialog.show();
		} catch (Exception e) {
			ToolLog.e("报错：" + e.getMessage());
		}

		View v = LayoutInflater.from(context).inflate(R.layout.dialog_progress,
				null);
		TextView txvMessage = (TextView) v
				.findViewById(R.id.txv_dialog_progress_content);
		mDialog.setContentView(v);
		txvMessage.setText(message);
	}

	public void cancel() {
		if (mDialog != null) {
			mDialog.dismiss();
		}
	}

}
