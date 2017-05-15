package com.yfzx.lwpai.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;

public class Helper {
	private static final String TAG = "Helper";

	private Helper() {
	}

	/**
	 * @param context
	 *            if null, use the default format (Mozilla/5.0 (Linux; U;
	 *            Android %s) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0
	 *            %sSafari/534.30).
	 * @return
	 */
	public static String getUserAgent(Context context) {
		String webUserAgent = null;
		if (context != null) {
			try {
				Class sysResCls = Class
						.forName("com.android.internal.R$string");
				Field webUserAgentField = sysResCls
						.getDeclaredField("web_user_agent");
				Integer resId = (Integer) webUserAgentField.get(null);
				webUserAgent = context.getString(resId);
			} catch (Throwable ignored) {
			}
		}
		if (TextUtils.isEmpty(webUserAgent)) {
			webUserAgent = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1";
		}

		Locale locale = Locale.getDefault();
		StringBuffer buffer = new StringBuffer();
		// Add version
		final String version = Build.VERSION.RELEASE;
		if (version.length() > 0) {
			buffer.append(version);
		} else {
			// default to "1.0"
			buffer.append("1.0");
		}
		buffer.append("; ");
		final String language = locale.getLanguage();
		if (language != null) {
			buffer.append(language.toLowerCase());
			final String country = locale.getCountry();
			if (country != null) {
				buffer.append("-");
				buffer.append(country.toLowerCase());
			}
		} else {
			// default to "en"
			buffer.append("en");
		}
		// add the model for the release build
		if ("REL".equals(Build.VERSION.CODENAME)) {
			final String model = Build.MODEL;
			if (model.length() > 0) {
				buffer.append("; ");
				buffer.append(model);
			}
		}
		final String id = Build.ID;
		if (id.length() > 0) {
			buffer.append(" Build/");
			buffer.append(id);
		}
		return String.format(webUserAgent, buffer, "Mobile ");
	}

	public static boolean isSupportRange(final HttpResponse response) {
		if (response == null)
			return false;
		Header header = response.getFirstHeader("Accept-Ranges");
		if (header != null) {
			return "bytes".equals(header.getValue());
		}
		header = response.getFirstHeader("Content-Range");
		if (header != null) {
			String value = header.getValue();
			return value != null && value.startsWith("bytes");
		}
		return false;
	}

	public static Charset getCharsetFromHttpRequest(
			final HttpRequestBase request) {
		if (request == null)
			return null;
		String charsetName = null;
		Header header = request.getFirstHeader("Content-Type");
		if (header != null) {
			for (HeaderElement element : header.getElements()) {
				NameValuePair charsetPair = element
						.getParameterByName("charset");
				if (charsetPair != null) {
					charsetName = charsetPair.getValue();
					break;
				}
			}
		}

		boolean isSupportedCharset = false;
		if (!TextUtils.isEmpty(charsetName)) {
			try {
				isSupportedCharset = Charset.isSupported(charsetName);
			} catch (Throwable e) {
			}
		}

		return isSupportedCharset ? Charset.forName(charsetName) : null;
	}

	private static final int STRING_BUFFER_LENGTH = 100;

	public static long sizeOfString(final String str, String charset)
			throws UnsupportedEncodingException {
		if (TextUtils.isEmpty(str)) {
			return 0;
		}
		int len = str.length();
		if (len < STRING_BUFFER_LENGTH) {
			return str.getBytes(charset).length;
		}
		long size = 0;
		for (int i = 0; i < len; i += STRING_BUFFER_LENGTH) {
			int end = i + STRING_BUFFER_LENGTH;
			end = end < len ? end : len;
			String temp = getSubString(str, i, end);
			size += temp.getBytes(charset).length;
		}
		return size;
	}

	/**
	 * Get the sub string for large string
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getSubString(final String str, int start, int end) {
		return new String(str.substring(start, end));
	}

	/**
	 * 计算listview的高度,但子ListView每个Item必须是LinearLayout
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight() + 5;
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public static boolean checkNet(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null) {
			return true;
		}
		return false;
	}

	public static String getAPN(Context context) {
		String apn = "";
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();

		if (info != null) {
			if (ConnectivityManager.TYPE_WIFI == info.getType()) {
				apn = info.getTypeName();
				if (apn == null) {
					apn = "wifi";
				}
			} else {
				apn = info.getExtraInfo().toLowerCase();
				if (apn == null) {
					apn = "mobile";
				}
			}
		}
		return apn;
	}

	private static SSLSocketFactory sslSocketFactory;

	public static void changeFragment(FragmentActivity activity, int container,
			Fragment fragment, String TAG, boolean isAnim) {

		FragmentTransaction ft = activity.getSupportFragmentManager()
				.beginTransaction();

		if (isAnim) {
			ft.setCustomAnimations(R.anim.slide_right_in,
					R.anim.slide_left_out, R.anim.slide_left_in,
					R.anim.slide_right_out);
		}

		ft.replace(container, fragment, TAG);
		ft.addToBackStack(null);
		ft.commitAllowingStateLoss();
	}

	public static void performBackFragmet(FragmentActivity activity,
			int container, Fragment fragment, String TAG, boolean isAnim) {

		FragmentTransaction ft = activity.getSupportFragmentManager()
				.beginTransaction();

		if (isAnim) {
			ft.setCustomAnimations(R.anim.slide_left_in,
					R.anim.slide_right_out, R.anim.slide_right_in,
					R.anim.slide_right_in);
		}

		ft.replace(container, fragment, TAG);
		ft.addToBackStack(null);
		ft.commitAllowingStateLoss();
	}

	/**
	 * 提示消息
	 * 
	 * @Title: showMsg
	 * @Description: TODO
	 * @param @param ctx
	 * @param @param msg
	 * @return void
	 * @throws
	 */
	public static void showMsg(Context ctx, String msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 提示消息
	 * 
	 * @Title: showMsg
	 * @Description: TODO
	 * @param @param ctx
	 * @param @param msg
	 * @return void
	 * @throws
	 */
	public static void showMsg(Context ctx, int msg) {
		Toast.makeText(ctx, ctx.getString(msg), Toast.LENGTH_SHORT).show();
	}

	public static int convertDipToPx(Context context, float dip) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, context.getResources().getDisplayMetrics());
	}

	/**
	 * 键盘隐藏或显示
	 * 
	 * @param context
	 */
	public static void controlKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 强制显示
	 * 
	 * @param context
	 * @param view
	 */
	public static void forceShowKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}

	/**
	 * 强制隐藏
	 * 
	 * @param context
	 * @param view
	 */
	public static void forceHideKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static SpannedString getRequestHint(String text, int size) {
		if (TextUtils.isEmpty(text)) {
			return new SpannedString("");
		}
		String source = text + "（必填）";
		SpannableString ss = new SpannableString(source);
		AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
		ss.setSpan(ass, text.length(), ss.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return new SpannedString(ss);
	}

	/**
	 * 启动activity
	 * 
	 * @param context
	 * @param cls
	 */
	public static void starAct(Context context, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		context.startActivity(intent);
	}

	/**
	 * 将值传递给下一个activity
	 * 
	 * @param context
	 * @param cls
	 */
	public static void starAct(Context context, Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	public interface SpanClick {
		public void onClick(View arg0);
	}

	public static <T> List<T> merge(List<T> oldList, List<T> newList) {
		List<T> list = new ArrayList<T>();
		list.addAll(oldList);
		list.addAll(newList);

		Set set = new HashSet();
		List tmp = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				tmp.add(element);
		}
		return tmp;

	}

	public static <T> int contain(List<T> src, List<T> dest) {
		int result = 0;
		for (int i = 0; i < src.size(); i++) {
			for (int j = 0; j < dest.size(); j++) {
				if (src.get(i).equals(dest.get(j))) {
					result++;
					break;
				}
			}
		}
		return result;
	}

	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取总共的页数
	 * 
	 * @author: bangwei.yang
	 * @param total
	 */
	public static int paging(int total) {
		if (total % CommonGlobal.PAGESIZE == 0) {
			return total / CommonGlobal.PAGESIZE;
		} else {
			return total / CommonGlobal.PAGESIZE + 1;
		}
	}

	/**
	 * 获取总共的页数
	 * 
	 * @author: bangwei.yang
	 * @param total
	 */
	public static int paging(int total, int pageSize) {
		if (total % pageSize == 0) {
			return total / pageSize;
		} else {
			return total / pageSize + 1;
		}
	}

	/**
	 * 日期转换（自动补0）
	 * 
	 * @author: songbing.zhou
	 * @return
	 */
	public static String Convert(Long num) {
		String flag;
		if (num < 10) {
			flag = "0" + num;
		} else {
			flag = "" + num;
		}
		return flag;
	}

}
