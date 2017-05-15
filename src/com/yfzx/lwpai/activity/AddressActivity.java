package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.AddressAdapter;
import com.yfzx.lwpai.entity.Address;
import com.yfzx.lwpai.entity.MyAddress;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 收货地址管理
 * 
 * @author: yizhong.xu Gy
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日 2015-7-7
 */
@ContentView(R.layout.activity_address)
public class AddressActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 添加
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;

	@ViewInject(R.id.lvAddress)
	private ListView listview;

	@ViewInject(R.id.tvAddressInfo)
	private TextView textViewAddressInfo;

	@ViewInject(R.id.btn_tab_bottom_square)
	private RadioButton radioButton;

	// 适配器
	private AddressAdapter adapter;
	protected List<Address> addresses = new ArrayList<Address>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("收货地址管理");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		ivRight.setImageResource(R.drawable.top_add);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initDate();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu GY
	 */
	public void initDate() {

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetUserAddressList");// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();

				MyAddress response = JsonUtil.parseObject(act,
						responseInfo.result, MyAddress.class);//
				if (response != null) {
					addresses = response.getResult();
					if (addresses.size() != 0) {// 如果长度为0，隐藏提示框，否贼显示地址数据
						textViewAddressInfo.setVisibility(View.GONE);
						// 保留地址长度，判断是否大于3个
						adapter = new AddressAdapter(act, addresses);
						listview.setAdapter(adapter);
					} else {
						textViewAddressInfo.setVisibility(View.VISIBLE);
					}
				}
			}
		});
	}

	/**
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.ivRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivRight:// 添加收货地址
			if (addresses.size() >= 3) {
				ToolToast.showShort("实物收货地址最多可添加3个");
				return;
			}
			showActivity(act, AddAddressActivity.class);
			break;
		default:
			break;
		}
	}
}
