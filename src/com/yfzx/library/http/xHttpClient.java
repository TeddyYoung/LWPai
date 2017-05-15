package com.yfzx.library.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.view.ProgressHelper;

public class xHttpClient {
	private final String TAG = xHttpClient.class.getSimpleName();

	private Map<String, String> params = new HashMap<String, String>();
	private HttpUtils http = xUtilsHttpClient.getInstence();
	private Context context;
	private boolean isShow = true;
	public StringBuilder url = new StringBuilder();

	public xHttpClient(Context context) {
		this.context = context;
		url.append(CommonGlobal.SERVER);
	}

	public xHttpClient(Context context, boolean isShow) {
		this.context = context;
		this.isShow = isShow;
		url.append(CommonGlobal.SERVER);
	}

	/**
	 * 以get的方式访问网络
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param response
	 */
	public void get(xResopnse response) {
		send(HttpRequest.HttpMethod.GET, response);
	}

	/**
	 * 以post的方式访问网络
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param response
	 */
	public void post(xResopnse response) {
		send(HttpRequest.HttpMethod.POST, response);
	}

	private void send(HttpMethod method, final xResopnse response) {
		RequestParams params = new RequestParams();
		try {
			params.addQueryStringParameter(getNameValuePairList());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Log(url.toString());
		http.send(method, url.toString(), params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Log.d(TAG, "result = " + responseInfo.result);
						response.onSuccess(responseInfo);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.d(TAG, "error = " + error.getExceptionCode()
								+ ",msg = " + msg);
						// 连接超时
						ProgressHelper.getInstance().cancel();
						if (error.getExceptionCode() == 0) {// 无网络访问时
							ToolToast.showShort("网络不给力，请稍后再试");
						} else {
							ToolToast.showShort("error = "
									+ error.getExceptionCode() + ",msg = "
									+ msg);
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						// 加载动画
						if (isShow) {
							ProgressHelper.getInstance().show(context, true);
						}
					}

				});
	}

	/**
	 * 以post的方式访问网络
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param response
	 */
	public void post(xAllResopnse response) {
		send(HttpRequest.HttpMethod.POST, response);
	}

	private void send(HttpMethod method, final xAllResopnse response) {
		RequestParams params = new RequestParams();
		try {
			params.addQueryStringParameter(getNameValuePairList());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Log(url.toString());
		http.send(method, url.toString(), params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Log.d(TAG, "result = " + responseInfo.result);
						response.onSuccess(responseInfo);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.d(TAG, "error = " + error.getExceptionCode()
								+ ",msg = " + msg);
						response.onFailure(error, msg);
					}

					@Override
					public void onStart() {
						super.onStart();
						response.onStart();
					}

				});
	}

	/**
	 * 上载文件
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param filePath
	 * @param response
	 */
	public void upload(String filePath, final xResopnse response) {
		Log.d(TAG, "url = " + url);
		RequestParams params = new RequestParams();
		try {
			params.addBodyParameter("/", new File(filePath));
			params.addQueryStringParameter(getNameValuePairList());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		http.send(HttpRequest.HttpMethod.POST, url.toString(), params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Log.d(TAG, "result = " + responseInfo.result);
						response.onSuccess(responseInfo);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Log.d(TAG, "error = " + error.getExceptionCode()
								+ ",msg = " + msg);
						// 连接超时
						ProgressHelper.getInstance().cancel();
						if (error.getExceptionCode() == 0) {// 无网络访问时
							ToolToast.showShort("网络不给力，请稍后再试");
						} else {
							ToolToast.showShort("error = "
									+ error.getExceptionCode() + ",msg = "
									+ msg);
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						// 加载动画
						if (isShow) {
							ProgressHelper.getInstance().show(context, true);
						}
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						Log.d(TAG, "==============================");
						Log.d(TAG, "total = " + total);
						Log.d(TAG, "current = " + current);
						Log.d(TAG, "isUploading = " + isUploading);
						Log.d(TAG, "==============================");
						response.onLoading(total, current, isUploading);
					}

				});
	}

	/**
	 * 下载文件
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param filePath
	 * @param fileDesc
	 * @param baseUrl
	 * @param response
	 */
	public void uploadFile(String filePath, String fileDesc, String baseUrl,
			final xResopnse response) {
		this.setParam("fileDesc", fileDesc);
		this.setParam("baseUrl", baseUrl);
		// this.upload(filePath, response);
	}

	/**
	 * 设置参数
	 * 
	 * @author: bangwei.yang
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value) {
		this.params.put(key, value);
	}

	/**
	 * 设置参数
	 * 
	 * @author: bangwei.yang
	 * @param params
	 */
	public void setParam(HashMap<String, String> params) {
		this.params = params;
	}

	private List<NameValuePair> getNameValuePairList()
			throws UnsupportedEncodingException {

		/*
		 * 创建NameValuePair数组
		 */
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : this.params.entrySet()) {
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return params;
	}

	/**
	 * Url的输出
	 * 
	 * @author: bangwei.yang
	 * @param url
	 */
	private void Log(String url) {
		String pStr = "";

		for (String key : params.keySet()) {
			pStr = pStr + key + "=" + params.get(key) + "&";
		}
		if (pStr.contains("&")) {
			pStr = pStr.substring(0, (pStr.length() - 1));
		}
		Log.d(TAG, "" + url + "?" + pStr);
	}

}
