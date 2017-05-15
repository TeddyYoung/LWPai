package com.yfzx.library.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.yfzx.lwpai.MApplication;

/**
 * 单例模式获取HttpUtils对象
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-5-27
 */
public class xUtilsHttpClient {

	private static HttpUtils client;

	/**
	 * 单例模式获取实例对象
	 * 
	 * @param context
	 *            应用程序上下文
	 * @return HttpUtils对象实例
	 */
	public synchronized static HttpUtils getInstence() {
		if (client == null) {
			// 设置请求超时时间为10秒
			client = new HttpUtils(1000 * 20);
			client.configSoTimeout(1000 * 20);
			client.configResponseTextCharset("UTF-8");
			// 保存服务器端(Session)的Cookie
			PreferencesCookieStore cookieStore = MApplication.self.getCookieStore();
			cookieStore.clear(); // 清除原来的cookie
			client.configCookieStore(cookieStore);
		}
		return client;
	}
}
