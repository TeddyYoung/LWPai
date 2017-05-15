package com.yfzx.library.http;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.tools.ToolLog;
import com.yfzx.library.tools.ToolToast;

public class JsonUtil {

	/**
	 * Json格式转对象使用,默认提示
	 * 
	 * @param result
	 * @param cls
	 * @return
	 */
	public static <T> T parseObject(Context content, String result, Class<T> cls) {
		if (TextUtils.isEmpty(result))
			return null;

		BaseResponse response = null;
		try {
			response = JSON.parseObject(result, BaseResponse.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (response != null) {
			if (response.getSuccess().equals("True")) {// 成功时
				try {
					return JSON.parseObject(result, cls);
				} catch (Exception e) {
					ToolLog.e(e.getMessage());
				}
			} else {
				try {
					return JSON.parseObject(result, cls);
				} catch (Exception e) {
					ToolLog.e(e.getMessage());
				}
				// 失败的提示
				ToolToast.showShort(response.getMessage());
			}
		}
		return null;
	}

	/**
	 * Json格式转对象使用,自己设置
	 * 
	 * @param result
	 * @param cls
	 * @return
	 */
	public static <T> T parseObject(Context content, String result,
			Class<T> cls, String tip) {
		if (TextUtils.isEmpty(result))
			return null;

		BaseResponse response = null;
		try {
			response = JSON.parseObject(result, BaseResponse.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (response != null) {
			if (response.getSuccess().equals("True")) {// 成功时
				try {
					return JSON.parseObject(result, cls);
				} catch (Exception e) {
					ToolLog.e(e.getMessage());
				}
			} else {
				try {
					return JSON.parseObject(result, cls);
				} catch (Exception e) {
					ToolLog.e(e.getMessage());
				}
				// 失败的提示
				ToolToast.showShort(content, tip);
			}
		}
		return null;
	}
}
