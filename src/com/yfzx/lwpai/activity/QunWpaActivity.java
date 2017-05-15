package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;

/**
 * 一键加群
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-20
 */
@ContentView(R.layout.activity_qun_wpa)
public class QunWpaActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.webView)
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
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("一键加群");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		// 得到WebSettings对象，设置支持JavaScript的参数
		webView.getSettings().setJavaScriptEnabled(true);
		/**
		 * 一键加群
		 * 
		 * @author: bangwei.yang
		 */
		webView.loadUrl("http://shang.qq.com/wpa/qunwpa?idkey=5e6f84c31dfc3a476c09352e12bb98665a225d2d1833a19db70a4ef09cfe9865");

	}

	/**
	 * 点击事件
	 * 
	 * @author Gy
	 * @param view
	 */
	@OnClick({ R.id.ivLeft })
	public void OnClick(View view) {
		switch (view.getId()) {
		case R.id.ivLeft:
			finish();
			break;

		default:
			break;
		}
	}
}
