package com.yfzx.lwpai.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.yfzx.lwpai.entity.GetUserSecurityVerify;
import com.yfzx.lwpai.entity.GetUserSecurityVerify.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 添加银行卡
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-22
 */
@ContentView(R.layout.activity_blank_add)
public class BlankAddActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 添加
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;

	// 开户人
	@ViewInject(R.id.edtTxtName)
	private TextView edtTxtName;
	// 开户银行
	@ViewInject(R.id.edtTxtCard)
	private TextView edtTxtCard;
	// 银行卡卡号
	@ViewInject(R.id.edtTxtCardNum)
	private TextView edtTxtCardNum;
	// 是否实名认证
	private String ok = "0";

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
		tvCenter.setText("添加银行卡");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	public void initDate() {
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
					ok = date.get(0).getIsVerifyDentity();
					if (!ok.equals("0")) {
						edtTxtName.setText(UserManage.getUser().getRealName());
					}
				}
			}
		});
	}

	private void addUserBank() {
		String card = edtTxtCard.getText().toString();
		String cardNum = edtTxtCardNum.getText().toString();
		if (ok.equals("0")) {
			startActivity(new Intent(BlankAddActivity.this,
					AccountSecurityActivity.class));
			finish();
		}
		if (TextUtils.isEmpty(card)) {
			ToolToast.showShort("请输入开户银行");
			return;
		}
		if (TextUtils.isEmpty(cardNum)) {
			ToolToast.showShort("请输入银行卡开号");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/AddUserBank");// 方法 银行名，银行卡号
		// httpClient.url.append("/" + name);
		httpClient.url.append("/" + card);
		httpClient.url.append("/" + cardNum);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);//
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
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.btnSave })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.btnSave:// 添加银行卡信息
			addUserBank();
			break;
		default:
			break;
		}
	}
	
}
