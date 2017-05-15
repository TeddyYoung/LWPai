package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
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
import com.yfzx.lwpai.entity.Phone;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 修改登陆密码
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月18日
 */
@ContentView(R.layout.activity_mylogin_password)
public class MyLoginPasswordActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 用户名
	@ViewInject(R.id.tvUser)
	private TextView tvUser;
	// 旧密码
	@ViewInject(R.id.edPassword)
	private EditText edPassword;
	// 新密码
	@ViewInject(R.id.edNewsPassword)
	private EditText edNewsPassword;

	@ViewInject(R.id.etNewssPassword)
	private EditText etNewssPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("登录密码修改");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		// tvCenter.setText(Html.fromHtml("<font color=black>账号安全</font>"));
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvUser.setText(UserManage.getUser().getCellPhone());

	}

	/**
	 * 重置密码
	 * 
	 * @author: yizhong.xu
	 * @param phonec
	 * @param code
	 */
	private void changePassword(final String old, final String password,
			final String againpassword) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/UpdatePassword");// 方法
		httpClient.url.append("/" + old);// 旧密码
		httpClient.url.append("/" + password);// 密码
		httpClient.url.append("/" + againpassword);// 再次密码

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Phone response = JsonUtil.parseObject(act, responseInfo.result,
						Phone.class);
				if (response != null) {
					User user = new User();
					user.setPassword(againpassword);
					UserManage.update(user);
					ToolToast.showLong("修改成功");
					finish();
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
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.button1 })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.button1:// 修改登陆密码
		{
			String old = edPassword.getText().toString();
			String password = edNewsPassword.getText().toString();
			String againpassword = etNewssPassword.getText().toString();
			if (TextUtils.isEmpty(old)) {
				ToolToast.showShort("请输入旧密码!");
				return;
			}
			if (TextUtils.isEmpty(password)) {
				ToolToast.showShort("密码不能为空!");
				return;
			}
			if (TextUtils.isEmpty(againpassword)) {
				ToolToast.showShort("请在输一遍密码!");
				return;
			}
			changePassword(old, password, againpassword);
			break;
		}

		}
	}
}
