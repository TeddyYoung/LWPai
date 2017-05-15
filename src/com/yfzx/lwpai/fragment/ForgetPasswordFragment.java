package com.yfzx.lwpai.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.activity.LoginActivity;
import com.yfzx.lwpai.entity.Phone;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.util.ValidateHelper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 忘记登录密码界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月1日
 */
@SuppressLint({ "InlinedApi", "ResourceAsColor" })
public class ForgetPasswordFragment extends BaseFragment {

	// 步骤
	private final int STEP_ONE = 1;
	private final int STEP_TWO = 2;
	private final int STEP_ThREE = 3;
	// 布局
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;
	@ViewInject(R.id.llTow)
	private LinearLayout llTow;
	@ViewInject(R.id.llThree)
	private LinearLayout llThree;
	// 手机号码
	@ViewInject(R.id.etPhone)
	private EditText etPhone;
	// 验证码
	@ViewInject(R.id.etCode)
	private EditText etCode;
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
	@ViewInject(R.id.ivSucceed)
	private ImageView ivSucceed;
	// 字
	@ViewInject(R.id.tvAuthentication)
	private TextView tvAuthentication;
	@ViewInject(R.id.tvAlter)
	private TextView tvAlter;
	@ViewInject(R.id.tvSucceed)
	private TextView tvSucceed;
	// view
	@ViewInject(R.id.view1)
	private View view1;
	@ViewInject(R.id.view2)
	private View view2;
	@ViewInject(R.id.view3)
	private View view3;
	@ViewInject(R.id.view4)
	private View view4;
	// 验证码
	@ViewInject(R.id.btnCode)
	private Button btnCode;
	// 下一步
	@ViewInject(R.id.btnNext)
	private Button btnNext;
	private int init = 1;

	// 计时器
	Handler handlerTime = new Handler();
	private int time = 120;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forget_password,
				container, false);
		// 绑定界面UI
		ViewUtils.inject(this, view);
		initWidget();
		initDate();
		one();
		return view;
	}

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
		String phones = etPhone.getText().toString();
		if (TextUtils.isEmpty(phones) || !ValidateHelper.isMobilePhone(phones)) {
			Helper.showMsg(getActivity(), "请输入正确的电话号码");
			return;
		}
		/**
		 * 
		 * 向手机号码发送验证码
		 * 
		 * @author: yizhong.xu
		 * @param phones
		 */
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Phone/SendPhoneCode/7");// 方法
		httpClient.url.append("/" + phones);// 电话号码

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
					} else {
						ToolToast.showShort(response.getMessage());
					}
				} else {
					ToolToast.showShort("访问超时");
				}
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				ToolToast.showShort("访问超时");
			}
		});

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
	private void phonecode(final String phonec, final String code) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Phone/CheckUserVerifyCode/7");// 方法
		httpClient.url.append("/" + phonec);// 名字
		httpClient.url.append("/" + code);// 密码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						two();
						init++;
					}
				}
			}
		});

	}

	/**
	 * 重置密码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void ForgetPassword(final String password,
			final String againpassword) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/ForgetToChange/ForgetPassword");// 方法
		httpClient.url.append("/" + etPhone.getText().toString());// 名字
		httpClient.url.append("/" + password);// 密码
		httpClient.url.append("/" + againpassword);// 再次密码
		httpClient.url.append("/" + etCode.getText().toString());// 名字

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						User user = new User();
						user.setPassword(againpassword);
						UserManage.update(user);
						three();
						init++;
					} else {
						ToolToast.showShort(response.getMessage());
					}
				} else {
					ToolToast.showShort("验证超时");
				}
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				ToolToast.showShort("验证超时");
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
		llTow.setVisibility(View.VISIBLE);

	}

	/**
	 * 第三步
	 * 
	 * @author: yizhong.xu
	 */
	private void three() {
		ivSucceed.setImageResource(R.drawable.login_blue3);
		tvSucceed.setTextColor(Color.parseColor("#00FFFF"));
		view3.setBackgroundResource(R.color.blue);
		view4.setBackgroundResource(R.color.blue);
		llTow.setVisibility(View.GONE);
		llThree.setVisibility(View.VISIBLE);
		btnNext.setText("返回首页");
	}

	/**
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
	}

	@OnClick({ R.id.btnNext, R.id.btnCode })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnNext: {// 下一步
			if (init == 0) {
				one();

			}
			if (init == STEP_ONE) {

				String phonec = etPhone.getText().toString();
				String code = etCode.getText().toString();
				if (TextUtils.isEmpty(phonec)) {
					ToolToast.showShort("手机号码不能为空");
					return;
				}
				if (TextUtils.isEmpty(code)) {
					ToolToast.showShort("密码不能为空");
					return;
				}
				phonecode(phonec, code);
			}
			if (init == STEP_TWO) {
				String password = etNewPassword.getText().toString();
				String againpassword = etConfirmPassword.getText().toString();
				if (TextUtils.isEmpty(password)) {
					ToolToast.showShort("密码不能为空");
					return;
				}
				if (TextUtils.isEmpty(againpassword)) {
					ToolToast.showShort("密码不能为空");
					return;
				}
				ForgetPassword(password, againpassword);
			}
			if (init == STEP_ThREE) {
				// 返回首页
				skipActivity(act, LoginActivity.class);
			}
			// init++;
			break;
		}
		case R.id.btnCode: {
			getCode();
			break;
		}

		}
	}
}
