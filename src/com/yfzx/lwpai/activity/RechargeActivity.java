package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.module.pay.PayResult;
import com.yfzx.library.tools.ToolLog;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.RechargeEntity;
import com.yfzx.lwpai.entity.RechargeEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 充值
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
@ContentView(R.layout.activity_recharge)
public class RechargeActivity extends BaseActivity {

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;

	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// back
	@ViewInject(R.id.tvRight)
	private TextView tvRight;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.rdoBtn20)
	private RadioButton rdoBtn20;
	@ViewInject(R.id.rdoBtn50)
	private RadioButton rdoBtn50;
	@ViewInject(R.id.rdoBtn100)
	private RadioButton rdoBtn100;
	@ViewInject(R.id.rdoBtn200)
	private RadioButton rdoBtn200;
	@ViewInject(R.id.rdoBtn500)
	private RadioButton rdoBtn500;
	// 其他金额
	@ViewInject(R.id.edtTxtOtherMoney)
	private EditText edtTxtOtherMoney;

	// 支付的信息
	private String price = "0";

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
		tvCenter.setText("充值");
		ivLeft.setImageResource(R.drawable.top_back_black);

		edtTxtOtherMoney.clearFocus();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {

	}

	/**
	 * 充值
	 * 
	 * @author: bangwei.yang
	 */
	private void recharge() {
		xHttpClient httpClient = new xHttpClient(act, true);
		httpClient.url.append("api/Recharge/Recharge/");// 方法
		httpClient.url.append(price);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				RechargeEntity response = JsonUtil.parseObject(act,
						responseInfo.result, RechargeEntity.class);
				if (response != null) {
					ResultEntity data = response.getResult();
					alipay(data.getUrl() + "&sign=" + data.getUrlSing()
							+ "&sign_type=RSA");
					ToolLog.d(data.getUrl() + data.getUrlSing()
							+ "&sign_type=RSA");
				}
			}
		});
	}

	/**
	 * 支付宝支付
	 * 
	 * @author: bangwei.yang
	 */
	public void alipay(final String payInfo) {

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(RechargeActivity.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);

				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();
				String resultStatus = payResult.getResultStatus();

				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(MApplication.getContext(), "支付成功",
							Toast.LENGTH_SHORT).show();

				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(MApplication.getContext(), "支付结果确认中",
								Toast.LENGTH_SHORT).show();

					} else if (TextUtils.equals(resultStatus, "6001")) {
						Toast.makeText(MApplication.getContext(), "用户取消支付",
								Toast.LENGTH_SHORT).show();
					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(MApplication.getContext(), "支付失败",
								Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(MApplication.getContext(), "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};

	/**
	 * 重置单选按钮
	 * 
	 * @author: bangwei.yang
	 */
	private void resetRdo() {
		rdoBtn20.setChecked(false);
		rdoBtn50.setChecked(false);
		rdoBtn100.setChecked(false);
		rdoBtn200.setChecked(false);
		rdoBtn500.setChecked(false);
	}

	/**
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.rdoBtn10, R.id.rdoBtn20, R.id.rdoBtn50,
			R.id.rdoBtn100, R.id.rdoBtn500, R.id.edtTxtOtherMoney,
			R.id.btnRecharge })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.rdoBtn20:
			resetRdo();
			rdoBtn20.setChecked(true);
			break;
		case R.id.rdoBtn50:
			resetRdo();
			rdoBtn50.setChecked(true);
			break;
		case R.id.rdoBtn100:
			resetRdo();
			rdoBtn100.setChecked(true);
			break;
		case R.id.rdoBtn200:
			resetRdo();
			rdoBtn200.setChecked(true);
			break;
		case R.id.rdoBtn500:
			resetRdo();
			rdoBtn500.setChecked(true);
			break;
		case R.id.edtTxtOtherMoney:
			resetRdo();
			break;
		case R.id.btnRecharge:// 充值
			price = "0";
			if (rdoBtn20.isChecked()) {
				price = "20";
			}
			if (rdoBtn50.isChecked()) {
				price = "50";
			}
			if (rdoBtn100.isChecked()) {
				price = "100";
			}
			if (rdoBtn200.isChecked()) {
				price = "200";
			}
			if (rdoBtn500.isChecked()) {
				price = "500";
			}
			if (price.equals("0")) {
				price = edtTxtOtherMoney.getText().toString().trim();
			}
			if (price.equals("0") || TextUtils.isEmpty(price)
					|| Integer.parseInt(price) < 1) {
				ToolToast.showLong("请输入大于或等于1元金额");
				return;
			}
			recharge();
			break;
		default:
			break;
		}
	}
}
