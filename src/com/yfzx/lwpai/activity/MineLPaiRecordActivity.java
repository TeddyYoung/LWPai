package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;

/**
 * 乐拍记录
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-8
 */
@ContentView(R.layout.activity_mine_lpai_record)
public class MineLPaiRecordActivity extends BaseActivity {
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
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		tvCenter.setText("乐拍记录");
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDate() {

	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.llLuck, R.id.llRed, R.id.llOne, R.id.llSecurity })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.llLuck:// 幸运拍
			Bundle bundle1 = new Bundle();
			Intent intent1 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle1.putInt("type", 55);
			intent1.putExtras(bundle1);
			startActivity(intent1);
			break;
		case R.id.llRed:// 红包区
			Bundle bundle2 = new Bundle();
			Intent intent2 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle2.putInt("type", 56);
			intent2.putExtras(bundle2);
			startActivity(intent2);
			break;
		case R.id.llOne:// 一元拍
			Bundle bundle3 = new Bundle();
			Intent intent3 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle3.putInt("type", 3);
			intent3.putExtras(bundle3);
			startActivity(intent3);
			break;
		case R.id.llSecurity:// 趣味拍
			Bundle bundle4 = new Bundle();
			Intent intent4 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle4.putInt("type", 3);
			intent4.putExtras(bundle4);
			startActivity(intent4);
			break;
		default:
			break;
		}
	}

}
