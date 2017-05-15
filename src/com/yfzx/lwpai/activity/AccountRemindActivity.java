package com.yfzx.lwpai.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.BalanceEntity;
import com.yfzx.lwpai.entity.GetUserSecurityVerify;
import com.yfzx.lwpai.entity.GetUserSecurityVerify.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 申请提现
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-20
 */
@ContentView(R.layout.activity_account_remind)
public class AccountRemindActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 标题
	@ViewInject(R.id.tvRight)
	private TextView tvRight;

	// 余额
	@ViewInject(R.id.tvBalance)
	private TextView tvBalance;
	// 可提现
	@ViewInject(R.id.tvRemind)
	private TextView tvRemind;
	// 用户名
	@ViewInject(R.id.tvName)
	private TextView tvName;
	// 发送验证码
	@ViewInject(R.id.btnVerification)
	private Button btnVerification;
	// 用户名
	@ViewInject(R.id.tvBankId)
	private TextView tvBankId;
	// 提现金额
	@ViewInject(R.id.edtTxtRequestAmout)
	private EditText edtTxtRequestAmout;
	// 备注
	@ViewInject(R.id.edtTxtRemark)
	private EditText edtTxtRemark;
	// 交易密码
	@ViewInject(R.id.edtTxtTrandPassword)
	private EditText edtTxtTrandPassword;
	// 短信验证码
	@ViewInject(R.id.edtTxtPhoneCode)
	private EditText edtTxtPhoneCode;

	private TimeCount time = new TimeCount(120000, 1000);// 计时器
	private String bankId;
	// 是否实名认证
	private String ok = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		initWidget();// 初始化界面
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	public void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvCenter.setText("申请提现");
		tvRight.setText("充值");
		tvRight.setTextColor(getResources().getColor(R.color.blue));

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			tvName.setText(UserManage.getUser().getCellPhone());
		}

	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {
		getUserBalance();
	}

	/**
	 * 账户金额
	 * 
	 * @author: bangwei.yang
	 */
	private void getUserBalance() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserBalance");// 账目明细
		httpClient.url.append("/" + 1);// 1表示金额，2表示充值
		httpClient.url.append("/" + 1);// 每页条数
		httpClient.url.append("/" + 1);// 第几页
		httpClient.url.append("/" + 0);// status全部0，收入1，支出2
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BalanceEntity response = JsonUtil.parseObject(act,
						responseInfo.result, BalanceEntity.class);
				if (response != null) {
					// 总金额
					tvBalance.setText(response.getUserBalance() + "");
					// 提现
					tvRemind.setText(response.getUserableBalance() + "");
				}

			}
		});

	}

	/**
	 * 倒计时
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-7-3
	 */
	class TimeCount extends CountDownTimer {

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			btnVerification.setText("发送验证码");
			btnVerification.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			btnVerification.setClickable(false);
			btnVerification.setText("重新获取(" + millisUntilFinished / 1000 + ")");
		}

	}

	private void addUserRequestBalance() {
		final String requestAmout = edtTxtRequestAmout.getText().toString()
				.trim();
		final String remark = edtTxtRemark.getText().toString();
		final String phoneCode = edtTxtPhoneCode.getText().toString().trim();
		final String trandPassword = edtTxtTrandPassword.getText().toString()
				.trim();

		if (TextUtils.isEmpty(bankId)) {
			ToolToast.showShort("请选择银行卡");
			return;
		}
		if (TextUtils.isEmpty(requestAmout)) {
			ToolToast.showShort("请输入提现金额");
			return;
		}
		if (TextUtils.isEmpty(remark)) {
			ToolToast.showShort("请输入备注信息");
			return;
		}
		if (TextUtils.isEmpty(trandPassword)) {
			ToolToast.showShort("请输入交易密码");
			return;
		}
		if (TextUtils.isEmpty(phoneCode)) {
			ToolToast.showShort("请输入短信验证码");
			return;
		}
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetUserSecurityVerify");// 方法

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				GetUserSecurityVerify response = JsonUtil.parseObject(act,
						responseInfo.result, GetUserSecurityVerify.class);
				if (response != null) {
					List<ResultEntity> date = response.getResult();
					ok = date.get(0).getIsVerifyDentity();
					if (ok.equals("0")) {
						startActivity(new Intent(AccountRemindActivity.this,
								AccountSecurityActivity.class));
					} else {
						xHttpClient httpClient1 = new xHttpClient(act);
						httpClient1.url
								.append("api/members/AddUserRequestBalance");
						httpClient1.url.append("/" + bankId);// bankId：银行信息Uid
						httpClient1.url.append("/" + trandPassword);// TrandPassword：交易密码
						httpClient1.url.append("/" + requestAmout);// RequestAmout：提现金额
						httpClient1.url.append("/" + remark);// Remark：备注
						httpClient1.url.append("/" + phoneCode);// phoneCode：短信验证码
						httpClient1.post(new xResopnse() {
							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								ProgressHelper.getInstance().cancel();
								BaseResponse response = JsonUtil.parseObject(
										act, responseInfo.result,
										BaseResponse.class);
								if (response != null) {
									time.start();
									ToolToast.showShort(response.getMessage());
								}
							}
						});
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:// 返回省市区
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras != null) {
						bankId = extras.getString("uid");
						tvBankId.setText(extras.getString("num"));
					}
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvRight, R.id.llytSelectBlank,
			R.id.btnVerification, R.id.btnSubimt })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:
			finish();
			break;
		case R.id.tvRight:
			showActivity(act, RechargeActivity.class);
			break;
		case R.id.llytSelectBlank:// 选择银行卡
			Intent intent = new Intent();
			intent.setClass(act, BlankActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.btnSubimt:// 提交
			addUserRequestBalance();
			break;
		case R.id.btnVerification:// 验证码按钮
			xHttpClient httpClient = new xHttpClient(act);
			httpClient.url.append("api/Phone/SendPhoneCode");// 方法
			httpClient.url.append("/" + 6);// 提醒申请
			httpClient.url.append("/" + UserManage.getUser().getCellPhone());
			httpClient.post(new xResopnse() {
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					ProgressHelper.getInstance().cancel();
					BaseResponse response = JsonUtil.parseObject(act,
							responseInfo.result, BaseResponse.class);
					if (response != null) {
						time.start();
						ToolToast.showShort(response.getMessage());
						finish();
					}
				}
			});
			break;
		default:
			break;
		}
	}

}
