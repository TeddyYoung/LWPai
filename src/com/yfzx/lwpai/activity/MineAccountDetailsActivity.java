package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
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
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.Account;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 账户中心
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-8
 */
@ContentView(R.layout.activity_mine_account_details)
public class MineAccountDetailsActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 余额
	@ViewInject(R.id.tvBalance)
	private TextView tvBalance;
	// 可提现
	@ViewInject(R.id.tvRemind)
	private TextView tvRemind;
	// 我的红包
	@ViewInject(R.id.tvRedBag)
	private TextView tvRedBag;
	// 我的积分
	@ViewInject(R.id.tvIntegral)
	private TextView tvIntegral;

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
		tvCenter.setText("账户明细");
		ivLeft.setImageResource(R.drawable.top_back_black);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			tvBalance.setText(bundle.getString("balance"));
			tvRemind.setText(bundle.getString("remind"));
			tvRedBag.setText(bundle.getString("redBag"));
			tvIntegral.setText(bundle.getString("integral"));
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDate() {

	}

	@Override
	protected void onResume() {
		super.onResume();
		GetUserAccountList();
	}

	/**
	 * 账目明细
	 * 
	 * @author: bangwei.yang
	 */
	private void GetUserAccountList() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserAccountList");// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Account response = JsonUtil.parseObject(act,
						responseInfo.result, Account.class);
				if (response != null) {
					com.yfzx.lwpai.entity.Account.ResultEntity data = response
							.getResult().get(0);
					tvBalance.setText(data.getUseableBalance());
					tvRemind.setText(data.getRequestableBanlance());
					tvRedBag.setText(data.getHongBao());
					tvIntegral.setText(data.getIntegral());
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
	@OnClick({ R.id.ivLeft, R.id.llytMyPoints, R.id.llytCashWithdrawal,
			R.id.llytAccountBalance, R.id.llytRechargeRecord, R.id.llytRedBag })
	public void onClick(View v) {
		Bundle bundle = new Bundle();
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.llytAccountBalance:// 账户余额
			bundle.putString("balance", tvBalance.getText().toString());
			bundle.putString("remind", tvRemind.getText().toString());
			showActivity(act, AccountBalanceActivity.class, bundle);
			break;
		case R.id.llytRechargeRecord:// 充值记录
			bundle.putString("balance", tvBalance.getText().toString());
			showActivity(act, AccountRechargeRecordActivity.class, bundle);
			break;
		case R.id.llytCashWithdrawal:// 提现金额
			bundle.putString("balance", tvBalance.getText().toString());
			bundle.putString("remind", tvRemind.getText().toString());
			showActivity(act, AccountRemindActivity.class,bundle);
			break;
		case R.id.llytRedBag:// 我的红包
			showActivity(act, AccountRedBagActivity.class);
			break;
		case R.id.llytMyPoints:// 我的积分
			bundle.putString("integral", tvIntegral.getText().toString());
			showActivity(act, AccountIntegralActivity.class, bundle);
			break;
		}
	}

}
