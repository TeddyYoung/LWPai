package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.BlankAdapter;
import com.yfzx.lwpai.entity.BlankEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 银行卡列表
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-22
 */
@ContentView(R.layout.activity_address)
public class BlankActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 添加
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;

	// list
	@ViewInject(R.id.lvAddress)
	private ListView listview;
	// 空白信息
	@ViewInject(R.id.tvAddressInfo)
	private TextView tvAddressInfo;

	// 适配器
	private BlankAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		tvCenter.setText("选择银行卡");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		ivRight.setImageResource(R.drawable.top_add);

		tvAddressInfo.setText("您尚未添加任何银行卡，请及时添加");
		tvAddressInfo.setVisibility(View.GONE);
		listview.setDivider(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initDate();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	public void initDate() {
		adapter = new BlankAdapter(act,
				new ArrayList<BlankEntity.ResultEntity>());
		listview.setAdapter(adapter);

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetUserBankDetails");// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BlankEntity response = JsonUtil.parseObject(act,
						responseInfo.result, BlankEntity.class);
				if (response != null) {
					if (!response.getResult().isEmpty()) {
						tvAddressInfo.setVisibility(View.GONE);
						adapter.addAll(response.getResult());
					} else {
						tvAddressInfo.setVisibility(View.VISIBLE);
					}
				} else {
					tvAddressInfo.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.ivRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivRight:// 添加银行卡信息
			showActivity(act, BlankAddActivity.class);
			break;
		default:
			break;
		}
	}

	/**
	 * 点击跳转
	 * 
	 * @author: songbing.zhou
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick(R.id.lvAddress)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		Bundle extras = new Bundle();
		extras.putString("uid", adapter.getList().get(position).getUid());
		extras.putString("num", adapter.getList().get(position)
				.getBankCardNumber());
		intent.putExtras(extras);
		act.setResult(RESULT_OK, intent);
		finish();
	}
}
