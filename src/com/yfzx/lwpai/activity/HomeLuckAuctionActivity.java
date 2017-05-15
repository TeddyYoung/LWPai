package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.fragment.HomeLuckAuctionFragment;
import com.yfzx.lwpai.util.Helper;

/**
 * home幸运拍（报废）
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
@ContentView(R.layout.activity_retrieve_password)
public class HomeLuckAuctionActivity extends BaseActivity {
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		ViewUtils.inject(this);
		Helper.changeFragment(this, R.id.flytContent,
				new HomeLuckAuctionFragment(), HomeLuckAuctionFragment.TAG,
				true);
		initWidget();
		initDate();

	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {

	}

	@OnClick({ R.id.ivLeft})
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		}
	}
}
