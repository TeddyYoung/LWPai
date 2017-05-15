package com.yfzx.lwpai;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.yfzx.library.core.SimpleXmlAccessor;
import com.yfzx.lwpai.entity.User;

/**
 * 用户管理
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-29
 */
public class UserManage {

	private static final String NAME = "USER_INFO";
	private static final String KEY = "user";
	private static SimpleXmlAccessor accessor;

	private static SimpleXmlAccessor getAccessor() {
		if (accessor == null) {
			accessor = new SimpleXmlAccessor(MApplication.self, NAME,
					Context.MODE_APPEND);
		}
		return accessor;
	}

	/**
	 * 获取用户信息
	 * 
	 * @author: bangwei.yang
	 * @return
	 */
	public static User getUser() {
		String tmp = getAccessor().getString(KEY);
		if (tmp == null || tmp.equals("")) {
			return new User();
		}
		User user = JSON.parseObject(tmp, User.class);
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * 更新用户信息
	 * 
	 * @author: bangwei.yang
	 * @param user
	 */
	public static void update(User user) {
		if (user == null) {
			return;
		}
		getAccessor().putString("user", JSON.toJSONString(user));
	}

	/**
	 * 判断是否登录
	 * 
	 * @author: bangwei.yang
	 * @return 成功，返回true
	 */
	public static boolean isLogin() {
		User user = getUser();
		if (!TextUtils.isEmpty(user.getAccount())) {
			return true;
		}
		return false;
	}

	/**
	 * 用户登出
	 * 
	 */
	public static void clear() {
		accessor.remove(KEY);
	}

}
