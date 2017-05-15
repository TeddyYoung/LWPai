package com.yfzx.lwpai.activity;

import java.util.List;

import android.content.Intent;
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
import com.yfzx.library.tools.ToolLog;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GetUserSecurityVerify;
import com.yfzx.lwpai.entity.GetUserSecurityVerify.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 账号安全
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@ContentView(R.layout.activity_account_security)
public class AccountSecurityActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.tvPhone)
	private TextView tvPhone;
	// 显示有没有交易密码
	@ViewInject(R.id.tvGrade)
	private TextView tvGrade;
	// 实名认证
	@ViewInject(R.id.tvVerify)
	private TextView tvVerify;
	private String key = "";
	private String phone = "";
	private String ok = "";

	// 手机号码
	private String userPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		Bundle bundle = getIntent().getExtras();
		// 用户名=手机号码
		String userName = bundle.getString("userName");
		try {
			String temp1 = userName.substring(0, 3);
			String temp2 = userName.substring(7, userName.length());
			// 将用户名转换成 159****5373
			userPhone = temp1 + "****" + temp2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		initWidget();
		initDate();

	}

	@Override
	protected void onResume() {
		gain();
		super.onResume();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("账号安全");
		// tvCenter.setText(Html.fromHtml("<font color=black>账号安全</font>"));
		ivLeft.setImageResource(R.drawable.top_back_black);

		// tvPhone.setText(UserManage.getUser().getCellPhone());

	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	/**
	 * 修改交易密码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void gain() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetUserSecurityVerify");// 方法

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetUserSecurityVerify response = JsonUtil.parseObject(act,
						responseInfo.result, GetUserSecurityVerify.class);
				if (response != null) {
					List<ResultEntity> date = response.getResult();
					key = date.get(0).getIsOpenBalance();
					phone = date.get(0).getIsVerifyPhone();
					ok = date.get(0).getIsVerifyDentity();
					if (date.get(0).getIsVerifyPhone().equals("1")) {
						tvPhone.setText(userPhone);
					}
					ToolLog.d(date.get(0).getIsOpenBalance());
					ToolLog.d(date.get(0).getIsVerifyPhone());
					ToolLog.d(date.get(0).getIsVerifyDentity());
					if (date.get(0).getIsOpenBalance().equals("True")) {
						tvGrade.setText("修改交易密码");

					} else if (date.get(0).getIsOpenBalance().equals("False")) {
						tvGrade.setText("设置交易密码");
					}
					if (date.get(0).getIsVerifyDentity().equals("0")) {
						tvVerify.setText("立即验证");
					} else if (date.get(0).getIsVerifyDentity().equals("1")) {
						tvVerify.setText("已通过");
					} else if (date.get(0).getIsVerifyDentity().equals("2")) {
						tvVerify.setText("认证审核中");
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
	@OnClick({ R.id.ivLeft, R.id.llLoginPassword, R.id.llMessageIdentification,
			R.id.llTradersPassword, R.id.llVerify })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.llLoginPassword:// 修改登陆密码
			showActivity(act, MyLoginPasswordActivity.class);
			break;
		case R.id.llMessageIdentification:// 手机验证
			Bundle bundle = new Bundle();
			Intent intent = new Intent(act, PhoneVerificationActivity.class);
			bundle.putString("phone", phone);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.llTradersPassword:// 交易密码
			Bundle bundle1 = new Bundle();
			Intent intent1 = new Intent(act, MyTradePasswordActivity.class);
			bundle1.putString("key", key);
			intent1.putExtras(bundle1);
			startActivity(intent1);
			break;
		case R.id.llVerify:// 实名认证
			if (ok.equals("0")) {
				startActivityForResult(new Intent(act,
						RealNameSeriouslyActivity.class), 0x16);
				break;
			} else {
				ToolToast.showShort(R.string.shiming);
				break;
			}
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int result_code, int request_code,
			Intent arg2) {
		super.onActivityResult(result_code, request_code, arg2);
		if (result_code == RESULT_OK) {
			if (request_code == 0x16) {
				gain();
			}
		}
	}
}
