package com.yfzx.library.http;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public abstract class xAllResopnse extends RequestCallBack<String> {

	public abstract void onSuccess(ResponseInfo<String> responseInfo);

	public abstract void onFailure(HttpException error, String msg);

	public abstract void onStart();
}
