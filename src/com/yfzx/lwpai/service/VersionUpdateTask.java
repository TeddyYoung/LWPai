package com.yfzx.lwpai.service;

import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Context;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.config.SysEnv;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolPhone;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.entity.Verification;
import com.yfzx.lwpai.view.ProgressHelper;

public class VersionUpdateTask {

	public static final void checkUpdate(final Context context,
			final OnUpdateCallback onUpdateCallback) {
		xHttpClient client = new xHttpClient(context);
		client.url.append("api/Version/GetVersionList/");
		client.post(new xResopnse() {
			@SuppressLint("DefaultLocale")
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Verification response = JsonUtil.parseObject(context,
						responseInfo.result, Verification.class);
				if (response != null) {
					if (!response.getSuccess().toLowerCase().equals("true")) {
						ToolToast.showShort(response.getMessage());
						return;
					}
					try {
						JSONObject jsonObj = new JSONObject(responseInfo.result)
								.getJSONObject("result");
						String url = jsonObj.getString("DownLoadUrl");
						String content = jsonObj.getString("VersionContent");
						String versionNum = jsonObj.getString("VersionNum")
								.toLowerCase().replaceAll("v", "");
						if (versionNum.equals(SysEnv.getVersionName())) {
							onUpdateCallback.update(false, content, url);
						} else {
							onUpdateCallback.update(true, content, url);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				super.onFailure(error, msg);
				ProgressHelper.getInstance().cancel();
				ToolToast.showShort(msg);
			}
		});
	}

	public interface OnUpdateCallback {
		public void update(boolean update, String content, String url);
	}
}
