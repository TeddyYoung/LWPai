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
 * 身份验证
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月18日
 */
@ContentView(R.layout.activity_phone_verification)
public class PhoneVerificationActivity extends BaseActivity {

	// 步骤
	private final int STEP_ONE = 1;
	private final int STEP_TWO = 2;

	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 布局
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;
	@ViewInject(R.id.llTwo)
	private LinearLayout llTwo;
	// 手机号码
	@ViewInject(R.id.etPhoneCode)
	private EditText etPhoneCode;
	// 手机号码
	@ViewInject(R.id.tvNewPhone)
	private TextView tvNewPhone;
	// 新手机号码
	@ViewInject(R.id.etNewPhone)
	private EditText etNewPhone;

	@ViewInject(R.id.llqiehuan)
	private LinearLayout llqiehuan;
	// 验证码
	@ViewInject(R.id.etCode)
	private EditText etCode;
	// 显示
	@ViewInject(R.id.tvNews)
	private TextView tvNews;

	// 重置密码
	@ViewInject(R.id.etNewPassword)
	private EditText etNewPassword;
	// 确认密码
	@ViewInject(R.id.etConfirmPassword)
	private EditText etConfirmPassword;
	// 图片
	@ViewInject(R.id.ivAuthentication)
	private ImageView ivAuthentication;
	@ViewInject(R.id.ivAlter)
	private ImageView ivAlter;
	// 字
	@ViewInject(R.id.tvAuthentication)
	private TextView tvAuthentication;
	@ViewInject(R.id.tvAlter)
	private TextView tvAlter;
	// view
	@ViewInject(R.id.view1)
	private View view1;
	@ViewInject(R.id.view2)
	private View view2;
	// 验证码
	@ViewInject(R.id.btnCode)
	private Button btnCode;
	// 输入验证码
	@ViewInject(R.id.etCode2)
	private EditText etCode2;
	// 下一步
	@ViewInject(R.id.btnRegist)
	private Button btnRegist;
	private int init = 1;

	private String phone = "";
	private int phonecode;
	private String api;
	private String etPhones;

	// 计时器
	Handler handlerTime = new Handler();
	private int time = 120;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {

				phone = bundle.getString("phone");
			}
		}
		initWidget();
		initDate();

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
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("身份验证");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		llqiehuan.setVisibility(View.GONE);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		ToolLog.d(phone);
		if (phone.equals("0")) {
			tvNews.setText("输入新手机号码");
			etNewPhone.setVisibility(View.VISIBLE);
			tvNewPhone.setVisibility(View.GONE);
			phonecode = 2;
			api = "api/Phone/SendPhoneCode";
		} else if (phone.equals("1")) {

			User user = new User();
			tvNews.setText("解绑手机号码");
			etNewPhone.setVisibility(View.GONE);
			tvNewPhone.setVisibility(View.VISIBLE);
			etPhones = UserManage.getUser().getCellPhone();
			tvNewPhone.setText(etPhones);
			phonecode = 3;
			api = "api/members/PhoneVerification";
		}

	}

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
		httpClient.url.append("api/Phone/SendPhoneCode");
		httpClient.url.append("/" + phonecode);// // 2手機綁定3解綁
		httpClient.url.append("/" + etPhones);// 电话号码
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
	 * 绑定 解除
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void phonecode(final String code, final String etPhones) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/PhoneVerification");
		httpClient.url.append("/" + phonecode);// 2是绑定3是解除
		httpClient.url.append("/" + code);// 名字
		httpClient.url.append("/" + etPhones);

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					// if (phonecode == 2) {
					// User user = new User();
					// user.setCellPhone(etPhones);
					// UserManage.update(user);
					ToolToast.showShort(response.getMessage());
					finish();
					// }
					// if (phonecode == 3) {
					// User user = new User();
					// user.setCellPhone("");
					// UserManage.update(user);
					// ToolToast.showShort(response.getMessage());
					// }

				}
				finish();
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

	}

	@OnClick({ R.id.ivLeft, R.id.btnRegist, R.id.btnCode, R.id.btnCode1 })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		case R.id.btnRegist: { // 下一步

			String code = etCode2.getText().toString();
			if (phonecode == 3) {
				phonecode(code, etPhones);
				break;
			} else {
				if (TextUtils.isEmpty(etPhones)) {

					ToolToast.showShort("请输入手机号码");
					return;
				}
				if (TextUtils.isEmpty(code)) {
					ToolToast.showShort("请输入验证码");
					return;
				}
			}
			phonecode(code, etPhones);
			break;
		}
		case R.id.btnCode: {
			// getCode();
			break;
		}
		case R.id.btnCode1: {

			if (phonecode == 3) {
				etPhones = UserManage.getUser().getCellPhone();
				getCode();
				return;
			} else {
				etPhones = etNewPhone.getText().toString();
				if (TextUtils.isEmpty(etPhones)) {
					ToolToast.showShort("请输入手机号码");
					return;
				} else {
					getCode();
				}

			}
			break;
		}

		}
	}
}
