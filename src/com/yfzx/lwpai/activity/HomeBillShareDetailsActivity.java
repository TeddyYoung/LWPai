package com.yfzx.lwpai.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
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
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GetOrderShareById;
import com.yfzx.lwpai.entity.GetOrderShareById.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 晒单详细界面
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-17
 */
@ContentView(R.layout.activity_bill_share)
public class HomeBillShareDetailsActivity extends BaseActivity {
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 主页
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 得奖头像
	@ViewInject(R.id.ivFaceImage)
	private ImageView ivFaceImage;
	// 得奖名
	@ViewInject(R.id.tvUserName)
	private TextView tvUserName;

	// 时间
	@ViewInject(R.id.tvTime)
	private TextView tvTime;
	// 标题
	@ViewInject(R.id.tvTitle)
	private TextView tvTitle;
	// 内容
	@ViewInject(R.id.tvContent)
	private TextView tvContent;
	// 商品图片
	@ViewInject(R.id.ivgoodsImage)
	private ImageView ivgoodsImage;
	// web页面
	@ViewInject(R.id.webView)
	private WebView webView;
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				id = bundle.getString("id");
			}
		}
		initWidget();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		tvCenter.setText("晒单详细");
		ivLeft.setImageResource(R.drawable.top_back_round);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setLoadWithOverviewMode(true);
		GetOrderShareById();

	}

	/**
	 * 获取晒单详细页面
	 * 
	 * @author: songbing.zhou
	 */
	private void GetOrderShareById() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/GetOrderShareById");//
		httpClient.url.append("/" + id);// 商品ID
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetOrderShareById response = JsonUtil.parseObject(act,
						responseInfo.result, GetOrderShareById.class);
				if (response != null) {
					List<ResultEntity> tmpList = response.getResult();
					// 头像
					ImageLoaderUtil.getByUrl(tmpList.get(0).getFaceImage(),
							ivFaceImage);
					// 名称
					tvUserName.setText(tmpList.get(0).getUserName());
					// 时间
					tvTime.setText(tmpList.get(0).getCreateTime());
					// 标题
					tvTitle.setText(tmpList.get(0).getTitle());
					String content = "<html><head><meta content=\'textml\'; "
							+ "charset=utf-8\' http-equiv=\'Content-Type\' /><meta name=\'viewport\' content=\'width=device-width, "
							+ "initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0\' /><meta content=\'yes\' name=\'apple-mobile-web-app-capable\' /><meta content=\'black\' name=\'apple-mobile-web-app-status-bar-style\' /><meta content=\'telephone=no\' name=\'format-detection\' /><style>img{margin: 0 auto;max-width: 640px;width: 100%;}"
							+ "</style></head><body>"
							+ tmpList.get(0).getContent() + "</body><html>";

					// webview内容
					webView.loadDataWithBaseURL(null, content, "text/html",
							"utf-8", null);

				}

			}

		});

	}
	


	@OnClick({ R.id.ivLeft, R.id.ivRight })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		case R.id.ivRight: {// 首页
			// finish();
			// MainActivity.clickMenu(1);// 跳转到对应的页面
			break;
		}
		}
	}

}
