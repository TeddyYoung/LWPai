package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.AddressAdapter;
import com.yfzx.lwpai.entity.Good;

/**
 * 地址管理
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@ContentView(R.layout.activity_address_administration)
public class AddressAdministrationActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// listview
	@ViewInject(R.id.listview)
	private ListView listview;
	// 适配器
	private AddressAdapter adapter;

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
	 * @author: yizhong.xu
	 */
	private void initDate() {
		adapter.removeAll();
		String[] list = { "西门吹雪", "狼藉天涯", "太煎熬" };

		for (int i = 0; i < list.length; i++) {
			Good good = new Good();
			good.setName(list[i]);
			// adapter.add(good);
		}
		adapter.notifyDataSetInvalidated();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("收货地址管理");
		// tvCenter.setText(Html.fromHtml("<font color=black>账号安全</font>"));
		ivLeft.setImageResource(R.drawable.top_back);
		// adapter = new AddressAdapter(act, new ArrayList<Good>());
		// listview.setAdapter(adapter);
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
