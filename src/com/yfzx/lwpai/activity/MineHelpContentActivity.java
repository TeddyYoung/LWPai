package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.HelperContentEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 帮助详情页面
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
@ContentView(R.layout.activity_gooddetail)
public class MineHelpContentActivity extends BaseActivity {

	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.wvGoodDetail)
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author Christy
	 */
	private void initWidget() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				tvCenter.setText(bundle.getString("title"));
				getHelperContent(bundle.getString("id"));
			}
		}

		WebSettings webSettings = webView.getSettings(); // webView: 类WebView的实例
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 就是webview图片适应
	}

	/**
	 * 初始化数据
	 * 
	 * @author Christy
	 */
	public void initDate() {

	}

	/**
	 * 获取详情页面
	 * 
	 * @author: bangwei.yang
	 */
	private void getHelperContent(String id) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Helper/GetHelperContent/");// 方法
		httpClient.url.append(id);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				HelperContentEntity response = JsonUtil.parseObject(act,
						responseInfo.result, HelperContentEntity.class);
				if (response != null) {
					webView.loadDataWithBaseURL(null,
							response.getResult().get(0).getContent(),
							"text/html", "utf-8", null);
				}
			}
		});

	}

	/**
	 * 点击事件
	 * 
	 * @author Christy
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
