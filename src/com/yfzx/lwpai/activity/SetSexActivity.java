package com.yfzx.lwpai.activity;

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
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 设置性别
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.2
 * @Date: 2015-7-17
 */
@ContentView(R.layout.activity_set_sex)
public class SetSexActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 确定
	@ViewInject(R.id.tvRight)
	private TextView tvRight;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 男
	@ViewInject(R.id.ivHorn)
	private ImageView ivHorn;
	// 女
	@ViewInject(R.id.ivHorn1)
	private ImageView ivHorn1;
	// 保密
	@ViewInject(R.id.ivHorn2)
	private ImageView ivHorn2;

	// 0保密，1男，2女
	private int sexId = 0;

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
		tvCenter.setText("修改性别");
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvRight.setText("确定");

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String sex = bundle.getString("sex");
			if (sex.equals("男")) {
				ivHorn.setVisibility(View.VISIBLE);
				ivHorn1.setVisibility(View.INVISIBLE);
				ivHorn2.setVisibility(View.INVISIBLE);
				sexId = 1;
			} else if (sex.equals("女")) {
				ivHorn.setVisibility(View.INVISIBLE);
				ivHorn1.setVisibility(View.VISIBLE);
				ivHorn2.setVisibility(View.INVISIBLE);
				sexId = 2;
			} else {
				ivHorn.setVisibility(View.INVISIBLE);
				ivHorn1.setVisibility(View.INVISIBLE);
				ivHorn2.setVisibility(View.VISIBLE);
				sexId = 0;
			}
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
	 * 修改性别
	 * 
	 * @author: bangwei.yang
	 */
	private void updateUserSex() {
		xHttpClient httpClient = new xHttpClient(act, true);
		httpClient.url.append("api/members/UpdateUserSex");// 方法
		httpClient.url.append("/" + sexId);//
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
	@OnClick({ R.id.ivLeft, R.id.llAgree, R.id.llAgree1, R.id.llAgree2,
			R.id.tvRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.tvRight:// 确认
			updateUserSex();
			break;
		case R.id.llAgree:// 男 0保密，1男，2女
			ivHorn.setVisibility(View.VISIBLE);
			ivHorn1.setVisibility(View.INVISIBLE);
			ivHorn2.setVisibility(View.INVISIBLE);
			sexId = 1;
			break;
		case R.id.llAgree1:// 女 0保密，1男，2女
			ivHorn.setVisibility(View.INVISIBLE);
			ivHorn1.setVisibility(View.VISIBLE);
			ivHorn2.setVisibility(View.INVISIBLE);
			sexId = 2;
			break;
		case R.id.llAgree2:// 保密 0保密，1男，2女
			ivHorn.setVisibility(View.INVISIBLE);
			ivHorn1.setVisibility(View.INVISIBLE);
			ivHorn2.setVisibility(View.VISIBLE);
			sexId = 0;
			break;
		default:
			break;
		}
	}
}
