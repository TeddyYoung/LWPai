package com.yfzx.lwpai.activity;

import android.os.Bundle;
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
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 修改昵称界面
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.2
 * @Date: 2015-7-17
 * 
 */
@ContentView(R.layout.activity_change_nickname)
public class ChangeNicknameActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 确定
	@ViewInject(R.id.tvRight)
	private TextView tvRight;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 清空
	@ViewInject(R.id.ivClose)
	private ImageView ivClose;
	// 昵称
	@ViewInject(R.id.etNickname)
	private EditText etNickname;

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
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvRight.setText("确定");
		tvCenter.setText("修改昵称");
		ivLeft.setImageResource(R.drawable.top_back_black);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			etNickname.setText(bundle.getString("userName"));
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
	 * 修改昵称
	 * 
	 * @author: bangwei.yang
	 */
	private void updateUserName() {
		xHttpClient httpClient = new xHttpClient(act, true);
		httpClient.url.append("api/members/UpdateUserNickName/");// 方法
		httpClient.url.append(etNickname.getText().toString());//
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					finish();
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
	@OnClick({ R.id.ivLeft, R.id.ivClose, R.id.tvRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivClose:// 清空
			etNickname.setText("");
			break;
		case R.id.tvRight:// 确认
			updateUserName();
			break;
		default:
			break;
		}
	}
}
