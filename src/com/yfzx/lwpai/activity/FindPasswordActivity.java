package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;

/**
 * 找回密码界面一
 * 
 * @author Gy
 * @date 2015-7-1
 */
@ContentView(R.layout.activity_findpassword)
public class FindPasswordActivity extends BaseActivity {

	@ViewInject(R.id.btnFpwCode)
	private Button codeButton;
	@ViewInject(R.id.btnFpwNext)
	private Button nextButton;
	@ViewInject(R.id.edtTxtFpwCode)
	private EditText codeEditText;
	@ViewInject(R.id.edtTxtFpwTel)
	private EditText teleEditText;
	private TimeCount time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
	}

	/**
	 * 初始化数据
	 * 
	 * @author: Gy
	 */
	private void initDate() {
	}

	/**
	 * 初始化界面
	 * 
	 * @author: Gy
	 */
	private void initWidget() {

	}

	/**
	 * 点击事件
	 * 
	 * @author:Gy
	 * @param v
	 */
	@OnClick({ R.id.btnFpwCode, R.id.btnFpwNext })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFpwCode:
			time.start();
			break;
		case R.id.btnFpwNext:
			String tel = teleEditText.getText().toString();
			String code = codeEditText.getText().toString();
			if ("".equals(tel)) {
				ToolToast.showShort("联系电话不能为空");
				return;
			}
			if ("".equals(code)) {
				ToolToast.showShort("验证码不能为空");
				return;
			}
			ToolToast.showShort("成功跳转");
			break;
		default:
			break;
		}
	}

	/**
	 * @author Gy 倒计时类
	 */
	class TimeCount extends CountDownTimer {

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			codeButton.setText("发送验证码");
			codeButton.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			codeButton.setClickable(false);
			codeButton.setText(millisUntilFinished / 1000 + "秒");
		}

	}
}
