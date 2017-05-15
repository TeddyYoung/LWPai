package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;

/**
 * 用户协议
 * 
 * @author Shawn
 */
@ContentView(R.layout.activity_agreement)
public class AgreementActivity extends BaseActivity {
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 确认
	@ViewInject(R.id.confirm)
	private Button confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		tvCenter.setText("用户协议");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AgreementActivity.this.finish();
			}
		});
		ivLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
