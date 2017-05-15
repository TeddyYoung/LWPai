package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.fragment.RetrievePasswordFragment;
import com.yfzx.lwpai.util.Helper;

/**
 * 找回密码界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月1日
 */
@ContentView(R.layout.activity_retrieve_password)
public class RetrievePasswordActivity extends BaseActivity {

	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		Helper.changeFragment(this, R.id.flytContent,
				new RetrievePasswordFragment(), RetrievePasswordFragment.TAG,
				true);
		initWidget();
		initDate();

	}

	/**
	 * back
	 * @author: yizhong.xu
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}
	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("找回密码");
		// tvCenter.setText(Html.fromHtml("<font color=black>账号安全</font>"));
		ivLeft.setImageResource(R.drawable.top_back);
	}

	/**
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		default:
			break;
		}
	}
}
