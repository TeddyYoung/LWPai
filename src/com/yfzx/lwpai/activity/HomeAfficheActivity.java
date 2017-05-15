package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GoodDetail;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 首页公告详细页面
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-12
 */
@ContentView(R.layout.activity_home_affiche)
public class HomeAfficheActivity extends BaseActivity {

	/**
	 * 标题
	 */
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	/**
	 * 内容标题
	 */
	@ViewInject(R.id.tvTitle)
	private TextView tvTitle;
	/**
	 * 时间
	 */
	@ViewInject(R.id.tvTime)
	private TextView tvTime;
	@ViewInject(R.id.wvGoodDetail)
	private WebView webView;
	// 广告ID
	private String afficheId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		tvCenter.setText("公告");
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				afficheId = bundle.getString("afficheId");
			}
		}
		initWidget();
		initDate();
	}

	/**
	 * 初始化数据
	 * 
	 * @author Christy
	 */
	public void initDate() {

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Affiche/GetAfficheListById");// 方法
		httpClient.url.append("/" + afficheId);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LogUtils.d(responseInfo.result);
				GoodDetail response = JsonUtil.parseObject(act,
						responseInfo.result, GoodDetail.class);

				if (response != null) {
					tvTitle.setText(response.getResult().getTitle());
					tvTime.setText(response.getResult().getAddedDate());
					String content = response.getResult().getContent();
					webView.loadDataWithBaseURL(null, content, "text/html",
							"utf-8", null);
				}
			}
		});

	}

	/**
	 * 初始化界面
	 * 
	 * @author Christy
	 */
	private void initWidget() {
		// webView.setInitialScale(200);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setLoadWithOverviewMode(true);
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
