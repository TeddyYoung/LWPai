package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.Register;
import com.yfzx.lwpai.entity.Verification;
import com.yfzx.lwpai.util.ValidateHelper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 用户注册
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-1
 */
@ContentView(R.layout.activity_regist)
public class RegistActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 手机号
	@ViewInject(R.id.edtTxtRegTelphone)
	private EditText edtTxtRegTelphone;
	// 验证码
	@ViewInject(R.id.edtTxtRegCode)
	private EditText edtTxtRegCode;
	// 设置密码
	@ViewInject(R.id.edtTxtRegPassword)
	private EditText edtTxtRegPassword;
	// 确认密码
	@ViewInject(R.id.edtTxtRegRepassword)
	private EditText edtTxtRegRepassword;
	// 验证码
	@ViewInject(R.id.btnVerification)
	private Button btnVerification;
	// 协议
	@ViewInject(R.id.tvRegAgree)
	private TextView tvRegAgree;

	private TimeCount time;// 计时器

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
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		tvCenter.setText("用户注册");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		time = new TimeCount(120000, 1000);// 构造CountDownTimer对象
		// 字符串截取方法
		// String temp = "本人已阅读、理解并同意本站的";
		// String regAgree = "《会员注册协议》";
		// SpannableString spannableString = new SpannableString(temp +
		// regAgree);
		// spannableString.setSpan(new ClickableSpan() {
		//
		// @Override
		// public void onClick(View widget) {
		// startActivity(new Intent(RegistActivity.this,
		// AgreementActivity.class));
		// }
		//
		// @Override
		// public void updateDrawState(TextPaint ds) {
		// super.updateDrawState(ds);
		// ds.setColor(getResources().getColor(R.color.btn_orange));
		// ds.setUnderlineText(false);
		// }
		// }, temp.length(), temp.length() + regAgree.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// tvRegAgree.setText(spannableString);
		// tvRegAgree.setMovementMethod(LinkMovementMethod.getInstance());//
		// 实现文本的滚动
		// tvRegAgree.setFocusable(true);
		tvRegAgree.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegistActivity.this,
						AgreementActivity.class));
			}
		});
	}

	/**
	 * 
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {

	}

	public void Regist(String RegTelphone, String RegCode, String RegPassword,
			String RegRepassword) {

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Register/UserAdd");// 方法
		httpClient.url.append("/" + RegTelphone);// 电话号码
		httpClient.url.append("/" + RegCode);// 验证码
		httpClient.url.append("/" + RegPassword);// 设置密码
		httpClient.url.append("/" + RegRepassword);// 确认密码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Register response = JsonUtil.parseObject(act,
						responseInfo.result, Register.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					showActivity(act, LoginActivity.class);
					finish();
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

	/**
	 * 点击事件
	 * 
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.btnRegist, R.id.btnVerification,
			R.id.tvRegUserLogin })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft: // 返回
			finish();
			break;
		case R.id.btnRegist:// 注册按钮
			String RegTelphone = edtTxtRegTelphone.getText().toString();
			String RegCode = edtTxtRegCode.getText().toString();
			String RegPassword = edtTxtRegPassword.getText().toString();
			String RegRepassword = edtTxtRegRepassword.getText().toString();
			if (TextUtils.isEmpty(RegTelphone)
					|| !ValidateHelper.isMobilePhone(RegTelphone)) {
				ToolToast.showShort("请输入正确的电话号码");
				return;
			}
			if (TextUtils.isEmpty(RegCode)) {
				ToolToast.showShort("验证码不能为空");
				return;
			}
			if (TextUtils.isEmpty(RegPassword)) {
				ToolToast.showShort("设置密码不能为空");
				return;
			}
			if (TextUtils.isEmpty(RegRepassword)) {
				ToolToast.showShort("确认密码不能为空");
				return;
			}
			if (!RegPassword.equals(RegRepassword)) {
				ToolToast.showShort("设置密码和确认密码不一样");
				return;
			}
			Regist(RegTelphone, RegCode, RegPassword, RegRepassword);
			break;
		case R.id.btnVerification:// 验证码按钮
			String phone = edtTxtRegTelphone.getText().toString();
			if (TextUtils.isEmpty(phone)
					|| !ValidateHelper.isMobilePhone(phone)) {
				ToolToast.showShort("请输入正确的电话号码");
				return;
			}
			xHttpClient httpClient = new xHttpClient(act, false);
			httpClient.url.append("api/Phone/SendPhoneCode");// 方法
			httpClient.url.append("/" + 1);// 参数
			httpClient.url.append("/" + edtTxtRegTelphone.getText().toString());// 验证码

			httpClient.post(new xResopnse() {
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					ProgressHelper.getInstance().cancel();
					Verification response = JsonUtil.parseObject(act,
							responseInfo.result, Verification.class);
					if (response != null) {
						time.start();
						ToolToast.showShort(response.getMessage());
					}
				}
			});
			break;
		case R.id.tvRegUserLogin: // 已注册用户
			if (UserManage.isLogin()) {// 判断是否登录
				((MainActivity) act).clickMenu(4);// 跳转到对应的页面
			} else {
				showActivity(act, LoginActivity.class);
			}
			finish();
			break;
		default:
			break;
		}

	}
}
