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
 * 幸运拍商品详情
 * 
 * @author Gy
 * @date 2015-7-2
 * 
 */
@ContentView(R.layout.activity_gooddetail)
public class GoodsDescriptionActivity extends BaseActivity {

	@ViewInject(R.id.wvGoodDetail)
	private WebView webView;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	private String goodsId;
	// 类型
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				goodsId = bundle.getString("goodsId");
				// 类型
				type = bundle.getInt("type");
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
		switch (type) {
		case 3:// 一元拍
			httpClient.url.append("api/One/GetOneProductContent");// 方法
			break;
		case 55:// 幸运拍
			httpClient.url.append("api/Lucky/GetLuckyProductContent");// 方法
			break;
		case 56:// 红包区
			httpClient.url.append("api/TryArea/GetTryAreaProductContent");// 方法
			break;
		case 77:// 趣味拍
			httpClient.url.append("api/One/GetOneProductContent");// 方法
			break;
		}
		httpClient.url.append("/" + goodsId);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LogUtils.d(responseInfo.result);
				GoodDetail response = JsonUtil.parseObject(act,
						responseInfo.result, GoodDetail.class);

				if (response != null) {
					String content = "<html><head><meta content=\"textml\"; charset=utf-8\" http-equiv=\"Content-Type\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0\" /><meta content=\"yes\" name=\"apple-mobile-web-app-capable\" /><meta content=\"black\" name=\"apple-mobile-web-app-status-bar-style\" /><meta content=\"telephone=no\" name=\"format-detection\" /><style>img{margin: 0 auto;max-width: 640px;width: 100%;}</style></head><body>"
							+ response.getResult().getContent()
							+ "</body><html>";

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
		tvCenter.setText("商品描述");
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
