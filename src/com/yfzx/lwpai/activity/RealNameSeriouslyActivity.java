package com.yfzx.lwpai.activity;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolImage;
import com.yfzx.library.tools.ToolPhoto;
import com.yfzx.library.tools.ToolStorage;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.ImageEntity;
import com.yfzx.lwpai.entity.Verification;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 实名认证
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月19日
 */
@ContentView(R.layout.activity_real_name_seriously)
public class RealNameSeriouslyActivity extends BaseActivity {

	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 点击添加图片
	@ViewInject(R.id.ivPicture)
	private ImageView ivPicture;
	// 证件号码
	@ViewInject(R.id.etNumber)
	private EditText etNumber;
	// 输入真是名字
	@ViewInject(R.id.etRealname)
	private EditText etRealname;
	// 输入短信验证码
	@ViewInject(R.id.etSmsCode)
	private EditText etSmsCode;
	// 短信验证码按钮
	@ViewInject(R.id.sendSmsBtn)
	private Button sendSmsBtn;
	// 图片1
	@ViewInject(R.id.ivPricture1)
	private ImageView ivPricture1;
	// 图片2
	@ViewInject(R.id.ivPricture2)
	private ImageView ivPricture2;

	private TimeCount time;// 计时器
	private int i = 0;

	private String[] imgs = new String[2];

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
		tvCenter.setText("实名认证");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		time = new TimeCount(120000, 1000);// 构造CountDownTimer对象
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	// 显示图片
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0x100:// 拍照
				String filePath = ToolPhoto.getResultPhotoPath(this, data,
						ToolStorage.getImageDir(this));
				ToolPhoto.startCorpImage(this, filePath, 200, 200);
				break;
			case ToolPhoto.CROP_REQUEST_CODE:// 截图图片
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras != null) {
						Bitmap cropBm = extras.getParcelable("data");
						if (cropBm != null) {
							String file = ToolStorage
									.getAvatorDir(RealNameSeriouslyActivity.this)
									+ File.separator
									+ "Avator_"
									+ System.currentTimeMillis() + ".jpg";
							ToolImage.saveBitmap(file, cropBm, true);
							upload(file, cropBm);
						}
					}
				}
				break;

			default:
				break;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 点击事件
	 * 
	 * @param view
	 */
	private long lastClickTime = 0;

	@OnClick({ R.id.ivLeft, R.id.ivPicture, R.id.ivPricture1, R.id.ivPricture2,
			R.id.button1, R.id.sendSmsBtn })
	public void OnClick(View view) {
		if (System.currentTimeMillis() - lastClickTime < 500) {
			return;
		}
		lastClickTime = System.currentTimeMillis();
		switch (view.getId()) {
		case R.id.ivLeft:
			finish();
			break;
		case R.id.ivPicture: {
			ToolPhoto.selectPicture(act, 0x100);
			break;
		}
		case R.id.ivPricture1:
			ivPricture1.setImageResource(R.drawable.picture_card1);
			break;
		case R.id.ivPricture2:
			ivPricture2.setImageResource(R.drawable.picture_card2);
			break;
		case R.id.button1:
			// 保存
			realNameSeriously();
			break;
		case R.id.sendSmsBtn:
			xHttpClient httpClient = new xHttpClient(act);
			httpClient.url.append("api/Phone/SendPhoneCode");// 方法
			httpClient.url.append("/" + 5);// 参数
			httpClient.url.append("/" + UserManage.getUser().getCellPhone());// 验证码
			httpClient.post(new xResopnse() {
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					ProgressHelper.getInstance().cancel();
					Verification response = JsonUtil.parseObject(act,
							responseInfo.result, Verification.class);
					if (response != null) {
						ToolToast.showShort(response.getMessage());
						if (!response.getSuccess().toLowerCase()
								.equals("true")) {
							return;
						}
						time.start();
					}
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					super.onFailure(error, msg);
					ToolToast.showShort(msg);
				}
			});
			break;
		default:
			break;
		}
	}

	private void realNameSeriously() {
		String cardNum = etNumber.getText().toString().trim();
		String realName = etRealname.getText().toString().trim();
		String smsCode = etSmsCode.getText().toString().trim();
		if (TextUtils.isEmpty(cardNum)) {
			ToolToast.showShort("请输入证件号码");
			return;
		}
		if (TextUtils.isEmpty(realName)) {
			ToolToast.showShort("请输入证件姓名");
			return;
		}
		if (TextUtils.isEmpty(smsCode)) {
			ToolToast.showShort("请输入短信验证码");
			return;
		}
		if (imgs[0] == null || imgs[1] == null || TextUtils.isEmpty(imgs[0])
				|| TextUtils.isEmpty(imgs[1])) {
			ToolToast.showShort("请上传身份证照片");
			return;
		}
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/Dentity");// 方法 实名认证
		// httpClient.url.append("/" + name);
		httpClient.url.append("/" + cardNum);
		httpClient.url.append("/" + realName);
		httpClient.url.append("/" + imgs[0]);
		httpClient.url.append("/" + imgs[1]);
		httpClient.url.append("/" + smsCode);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);//
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					if (response.getSuccess().toLowerCase().equals("true")) {
						setResult(RESULT_OK);
						RealNameSeriouslyActivity.this.finish();
					}
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				super.onFailure(error, msg);
				ToolToast.showShort(msg);
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
			sendSmsBtn.setText("发送验证码");
			sendSmsBtn.setClickable(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			sendSmsBtn.setClickable(false);
			sendSmsBtn.setText("重新获取(" + millisUntilFinished / 1000 + ")");
		}

	}

	public void upload(String file, final Bitmap cropBm) {
		xHttpClient client = new xHttpClient(act);
		client.url.append("api/ImageHelper/ImageSrc/3/");
		client.upload(file, new xResopnse() {// 图片的地址
					@Override
					public void onStart() {
						ProgressHelper.getInstance().show(act, "正在上传照片...",
								false);
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						ProgressHelper.getInstance().cancel();
						ImageEntity response = JsonUtil.parseObject(act,
								responseInfo.result, ImageEntity.class);
						if (response != null) {
							if (!response.getSuccess().toLowerCase()
									.equals("true")) {
								ToolToast.showShort(response.getMessage());
								return;
							}
							int index = i;
							if (index > 1) {
								index = 1;
							}
							if (index > 0) {
								ivPricture2.setImageBitmap(cropBm);
							}
							if (index == 0) {
								ivPricture1.setImageBitmap(cropBm);
								i++;
							}
							imgs[index] = response.getResult().getImgSrc();
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						ProgressHelper.getInstance().cancel();
						ToolToast.showShort(msg);
					}

				});
	}
}
