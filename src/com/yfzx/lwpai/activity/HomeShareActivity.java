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
import com.yfzx.lwpai.fragment.ShareFragment;
import com.yfzx.lwpai.util.Helper;

/**
 * 晒单Activity
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-11
 */
@ContentView(R.layout.activity_home_share)
public class HomeShareActivity extends BaseActivity {
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		ViewUtils.inject(this);
		Helper.changeFragment(this, R.id.shareContent, new ShareFragment(),
				ShareFragment.TAG, true);
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		tvCenter.setText("晒单");
		ivLeft.setImageResource(R.drawable.top_back_round);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {

	}

	/*
	 * 重写，返回关闭页面 (non-Javadoc)
	 * 
	 * @author: bangwei.yang
	 * 
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int,
	 * android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ShareFragment fragment = (ShareFragment) getSupportFragmentManager()
					.findFragmentByTag(ShareFragment.TAG);
			if (fragment != null && fragment.isVisible()) {
				finish();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		}
	}
}
