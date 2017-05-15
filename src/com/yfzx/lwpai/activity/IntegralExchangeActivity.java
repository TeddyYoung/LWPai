package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.ChangeHongBaoEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 积分兑换
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-26
 */
@ContentView(R.layout.activity_integral_exchange)
public class IntegralExchangeActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 总积分
	@ViewInject(R.id.tvTotal)
	private TextView tvTotal;

	/**
	 * 兑换积分输入框
	 */
	@ViewInject(R.id.edTxtIntegral)
	private EditText edTxtIntegral;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		initWidget();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		tvCenter.setText("积分兑换");
		ivLeft.setImageResource(R.drawable.top_back_black);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			tvTotal.setText(bundle.getString("Total"));
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {

	}

	/**
	 * 获取积分兑换数据
	 * 
	 * @author: songbing.zhou
	 */
	private void ChangeHongBao() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/ChangeHongBao");
		httpClient.url.append("/" + edTxtIntegral.getText().toString());
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				ChangeHongBaoEntity response = JsonUtil.parseObject(act,
						responseInfo.result, ChangeHongBaoEntity.class, "");
				if (response != null) {
					ToolToast.showShort(response.getMessage());
				} else {
					ToolToast.showShort(response.getMessage());
				}
			}

		});

	}

	/**
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.btnIntegral })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.btnIntegral:// 兑换积分
			String integral = edTxtIntegral.getText().toString();
			if (TextUtils.isEmpty(integral)) {
				ToolToast.showShort("请输入兑换积分");
				return;
			} else if (Integer.parseInt(integral) % 10 != 0) {
				ToolToast.showShort("请输入10的整数倍");
				return;
			}
			ChangeHongBao();
			break;
		}
	}

}