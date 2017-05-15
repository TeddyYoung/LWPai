package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
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
import com.yfzx.lwpai.entity.Login;
import com.yfzx.lwpai.entity.Login.ResultEntity;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 用户登录
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-29
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

	// 用户账号
	@ViewInject(R.id.edtTxtUser)
	private EditText edtTxtUser;
	// 用户密码
	@ViewInject(R.id.edtTxtPwd)
	private EditText edtTxtPwd;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;

	// 登录
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		requestWindowFeature(Window.FEATURE_PROGRESS);
		ViewUtils.inject(this);

		initWidget();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		tvCenter.setText("登录");
	}

	/**
	 * 登录操作
	 * 
	 * @author: bangwei.yang
	 */
	private void login(final String username, final String password) {
		xHttpClient httpClient = new xHttpClient(act,false);
		httpClient.url.append("api/Login/UserLogin");// 方法
		httpClient.url.append("/" + username);// 名字
		httpClient.url.append("/" + password);// 密码
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// ProgressHelper.getInstance().cancel();
				Login response = JsonUtil.parseObject(act, responseInfo.result,
						Login.class);
				if (response != null) {
					// 存储到缓存中
					ResultEntity data = response.getResult().get(0);
					User user = new User();
					user.setAccount(username);
					user.setPassword(password);
					user.setCellPhone(data.getCellPhone());
					user.setFaceImage(data.getFaceImage());
					user.setUserId(data.getUserId());
					user.setUserName(data.getUsername());
					user.setRealName(data.getRealName());
					UserManage.update(user);
					// 提示登录成功
					ToolToast.showShort(response.getMessage());
					userClientId();
				}
			}
		});

	}

	/**
	 * 
	 * 更新clientid
	 * 
	 * @author: bangwei.yang
	 */
	private void userClientId() {
		xHttpClient httpClient = new xHttpClient(act,false);
		httpClient.url.append("api/members/UserClientId/0/");// 方法
		httpClient.url.append(PushManager.getInstance().getClientid(act.getApplicationContext()));// clientid
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				// BaseResponse response = JsonUtil.parseObject(act,
				// responseInfo.result, BaseResponse.class);
				finish();
			}
		});

	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvForgetPwd, R.id.tvRegist, R.id.btnLogin })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {// 关闭
			finish();
			break;
		}
		case R.id.btnLogin: {// 用户登录
			String username = edtTxtUser.getText().toString();
			String password = edtTxtPwd.getText().toString();

			if (TextUtils.isEmpty(username)) {
				ToolToast.showShort("账号不能为空");
				return;
			}
			if (TextUtils.isEmpty(password)) {
				ToolToast.showShort("密码不能为空");
				return;
			}
			login(username, password);
			break;
		}
		case R.id.tvRegist: {// 用户注册
			showActivity(act, RegistActivity.class);
			break;
		}
		case R.id.tvForgetPwd: {// 忘记密码
			showActivity(act, RetrievePasswordActivity.class);
			break;
		}
		}
	}
}
