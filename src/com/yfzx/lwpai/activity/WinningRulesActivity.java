package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GoodDetail;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 竞拍规则详细页
 * @author Gy
 * date:2015-7-7
 */

@ContentView(R.layout.activity_gooddetail)
public class WinningRulesActivity extends BaseActivity{

	
	
	
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
	 * 初始化数据
	 * 
	 * @author Gy
	 */
	public void initDate() {

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/LwpaiRules/GetWinningRulesList/");// 方法
		httpClient.url.append("73");

		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LogUtils.d(responseInfo.result);
				GoodDetail response = JsonUtil.parseObject(act,
						responseInfo.result, GoodDetail.class);

				if (response != null) {
					if (response.getSuccess().equals("True")) {
						String content = response.getResult().getContent();
						webView.loadDataWithBaseURL(null, content, "text/html",
								"utf-8", null);
					} else {
						ToolToast.showShort(response.getMessage());
					}
				} else {
					ToolToast.showShort("加载失败");
				}
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				ToolToast.showShort("加载超时");
			}
		});

	}

	/**
	 * 初始化界面
	 * 
	 * @author Christy
	 */
	private void initWidget() {

		WebSettings webSettings = webView.getSettings(); // webView: 类WebView的实例
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 就是webview图片适应
	}

	/**
	 * 点击事件
	 * 
	 * @author Christy
	 * @param v
	 */
	@OnClick({})
	public void onClick(View v) {

	}
}
