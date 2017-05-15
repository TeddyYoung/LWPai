package com.yfzx.library.plugins.zxing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.tools.ToolLog;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.util.ValidateHelper;

/**
 * 查询二维码调整结果
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-15
 */
@ContentView(R.layout.activity_gooddetail)
public class ZxingResultActivity extends BaseActivity {

	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.tvContent)
	private TextView tvContent;

	@ViewInject(R.id.wvGoodDetail)
	private WebView webView;

	// 扫描到的url
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				url = bundle.getString("url");
			}
		}
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author bangwei.yang
	 */
	private void initWidget() {
		tvCenter.setText("乐网拍");

		// 类WebView的实例
		WebSettings webSettings = webView.getSettings(); // webView:
		// 就是webview图片适应
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setSupportZoom(true);

	}

	/**
	 * 初始化数据
	 * 
	 * @author bangwei.yang
	 */
	public void initDate() {
		ToolLog.d(url);
		// if (!ValidateHelper.isUrl(url)) {
		// tvContent.setText(url);
		// tvContent.setVisibility(View.VISIBLE);
		// // webView.setDownloadListener(new MyWebViewDownLoadListener());
		// } else {
		// webView.setDownloadListener(new MyWebViewDownLoadListener());
		webView.loadUrl(url);
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		// webView.setWebViewClient(new WebViewClient() {
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// // TODO Auto-generated method stub
		// // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
		// view.loadUrl(url);
		// return false;
		// }
		// });
		// }
		finish();

	}

	/**
	 * 设置webView 下载
	 * 
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-8-25
	 */
	private class MyWebViewDownLoadListener implements DownloadListener {

		@Override
		public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype, long contentLength) {
			Uri uri = Uri.parse(url);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}

	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:
			finish();
			break;
		default:
			break;
		}

	}
}
