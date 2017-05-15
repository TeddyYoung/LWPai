package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yfzx.library.tools.ToolLog;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.Phone;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 修改交易密码界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月19日
 */
@ContentView(R.layout.activity_trade_password)
public class MyTradePasswordActivity extends BaseActivity {

	// 步骤
	private final int STEP_ONE = 1;
	private final int STEP_TWO = 2;
	private final int STEP_ThREE = 3;
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 布局
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;
	@ViewInject(R.id.llTwo)
	private LinearLayout llTwo;
	@ViewInject(R.id.llthree)
	private LinearLayout llthree;
	@ViewInject(R.id.llqiehuan)
	private LinearLayout llqiehuan;
	// 手机号码
	@ViewInject(R.id.etPhones)
	private TextView etPhones;
	// 验证码
	@ViewInject(R.id.etCode)
	private EditText etCode;
	// 重置密码
	@ViewInject(R.id.etPassWord)
	private EditText etPassWord;
	// 确认密码
	@ViewInject(R.id.etNewPassword)
	private EditText etNewPassword;
	// 旧密码
	@ViewInject(R.id.etOldpassword)
	private EditText etOldpassword;
	// 图片
	@ViewInject(R.id.ivAuthentication)
	private ImageView ivAuthentication;
	@ViewInject(R.id.ivAlter)
	private ImageView ivAlter;
	@ViewInject(R.id.ivSucceed)
	private ImageView ivSucceed;
	// 字
	@ViewInject(R.id.tvAuthentication)
	private TextView tvAuthentication;
	@ViewInject(R.id.tvAlter)
	private TextView tvAlter;
	@ViewInject(R.id.tvSucceed)
	private TextView tvSucceed;
	// 设置交易密码
	@ViewInject(R.id.textView2)
	private TextView textView2;
	// view
	@ViewInject(R.id.view1)
	private View view1;
	@ViewInject(R.id.view2)
	private View view2;
	// 验证码
	@ViewInject(R.id.btnCode)
	private Button btnCode;
	// 下一步
	@ViewInject(R.id.btnRegist)
	private Button btnRegist;
	private int init = 1;
	// 计时器
	Handler handlerTime = new Handler();
	private int time = 120;
	private String key = "";
	private String code = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {

				key = bundle.getString("key");
				ToolLog.d(key);
			}
		}
		initWidget();
		initDate();
		one();

	};

	Runnable runnable = new Runnable() {
		/*
		 * (non-Javadoc) 验证码
		 * 
		 * @author: yizhong.xu
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {

			if (time > 1) {
				time--;
				btnCode.setText("重新获取(" + time + ")");
				btnCode.setClickable(false);
			} else {
				btnCode.setText("重新获取");
				btnCode.setClickable(true);
			}
			handlerTime.postDelayed(this, 1000);
		}
	};

	/**
	 * 获取验证码
	 * 
	 * @author: yizhong.xu
	 */
	private void getCode() {
		/**
		 * 
		 * 向手机号码发送验证码
		 * 
		 * @author: yizhong.xu
		 * @param phones
		 */

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Phone/SendPhoneCode/4");// 方法
		httpClient.url.append("/" + UserManage.getUser().getCellPhone());// 电话号码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						handlerTime.postDelayed(runnable, 1000);
						time = 120;
						ToolToast.showShort(response.getMessage());
					}
				}
			}
		});

	}

	/**
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("身份验证");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		etPhones.setText(UserManage.getUser().getCellPhone());
		if (key.equals("False")) {
			llOne.setVisibility(View.GONE);// 隐藏第一步
			llqiehuan.setVisibility(View.GONE);
			llTwo.setVisibility(View.VISIBLE);
			llthree.setVisibility(View.GONE);
			textView2.setText("设置交易密码");
		}

	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	/**
	 * 验证手机 验证码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	// private void phonecode(final String code) {
	// xHttpClient httpClient = new xHttpClient(act);
	// httpClient.url.append("api/Phone/CheckUserVerifyCode/8");// 方法
	// httpClient.url.append("/" + UserManage.getUser().getCellPhone());// 名字
	// httpClient.url.append("/" + code);// 密码
	//
	// httpClient.post(new xResopnse() {
	// @Override
	// public void onSuccess(ResponseInfo<String> responseInfo) {
	// ProgressHelper.getInstance().cancel();
	// Phone response = JsonUtil.parseObject(act, responseInfo.result,
	// Phone.class);
	// if (response != null) {
	// if (response.getSuccess().equals("True")) {
	// two();
	// init++;
	// }
	// }
	// }
	//
	// });
	//
	// }

	/**
	 * 设置交易密码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void ForgetPassword(final String password,
			final String againpassword) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/OpenTrandPassword");// 方法
		httpClient.url.append("/" + password);// 密码
		httpClient.url.append("/" + againpassword);// 再次密码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						User user = new User();
						user.setPaymentpassword(againpassword);
						UserManage.update(user);
						ToolToast.showShort(response.getMessage());
						btnRegist.setText("重新登录");
						init = 1;
					}
				}
			}

		});
	}

	/**
	 * 修改交易密码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void setPassword(final String oldpassword, final String password,
			final String againpassword) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/UpdateTrandPassword");// 方法
		httpClient.url.append("/" + oldpassword);// 密码
		httpClient.url.append("/" + password);// 密码
		httpClient.url.append("/" + againpassword);// 再次密码
		httpClient.url.append("/" + code);// 再次密码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						User user = new User();
						user.setPaymentpassword(againpassword);
						UserManage.update(user);
						ToolToast.showShort(response.getMessage());
						btnRegist.setText("重新登录");
						init++;
					}
				}
			}

		});
	}

	/**
	 * 第一步
	 * 
	 * @author: yizhong.xu
	 */
	private void one() {
		ivAuthentication.setImageResource(R.drawable.login_blue1);
		tvAuthentication.setTextColor(Color.parseColor("#00FFFF"));

	}

	/**
	 * 第二步
	 * 
	 * @author: yizhong.xu
	 */
	private void two() {
		ivAlter.setImageResource(R.drawable.login_blue2);
		tvAlter.setTextColor(Color.parseColor("#00FFFF"));
		view1.setBackgroundResource(R.color.blue);
		view2.setBackgroundResource(R.color.blue);
		llOne.setVisibility(View.GONE);
		llTwo.setVisibility(View.VISIBLE);
		init++;

	}

	@OnClick({ R.id.ivLeft, R.id.btnRegist, R.id.btnCode })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		case R.id.btnRegist: {// 下一步

			if (key.equals("True")) {
				ToolLog.d(key);
				if (init == 0) {
					one();
				}
				if (init == STEP_ONE) {

					code = etCode.getText().toString();
					if (TextUtils.isEmpty(code)) {
						ToolToast.showShort("请填写验证码");
						return;
					}
					// phonecode(code);
					two();

				}
				if (init == STEP_TWO) {
					String password = etPassWord.getText().toString();
					String againpassword = etNewPassword.getText().toString();
					String oldpassword = etOldpassword.getText().toString();
					if (TextUtils.isEmpty(password)) {
						ToolToast.showShort("密码不能为空");
						return;
					}
					if (TextUtils.isEmpty(againpassword)) {
						ToolToast.showShort("密码不能为空");
						return;
					}
					setPassword(oldpassword, password, againpassword);

				}
				if (init == STEP_ThREE) {
					finish();
					showActivity(act, LoginActivity.class);
				}
			}
			if (key.equals("False")) {
				String password = etPassWord.getText().toString();
				String againpassword = etNewPassword.getText().toString();
				ForgetPassword(password, againpassword);
				if (init == 1) {
					finish();
					showActivity(act, LoginActivity.class);
				}

			}
			break;
		}
		case R.id.btnCode: {
			getCode();
			break;
		}

		}
	}

}
