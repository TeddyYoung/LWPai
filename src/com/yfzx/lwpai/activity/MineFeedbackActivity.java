package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolPhoto;
import com.yfzx.library.tools.ToolStorage;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.ImageEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 意见反馈
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-19
 */
@ContentView(R.layout.activity_feedback)
public class MineFeedbackActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 反馈内容
	@ViewInject(R.id.edtTxtContent)
	private EditText edtTxtContent;
	// 联系方式
	@ViewInject(R.id.edtTxtContact)
	private EditText edtTxtContact;
	// 图片
	@ViewInject(R.id.ivAddImg)
	private ImageView ivAddImg;

	// 存放图片id
	private String imageUrl = "";
	private Bitmap bitmap;

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
		tvCenter.setText("意见反馈");
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDate() {

	}

	/**
	 * 上传图片
	 * 
	 * @author: bangwei.yang
	 */
	public void upload(String file) {
		xHttpClient client = new xHttpClient(act);
		client.url.append("api/ImageHelper/ImageSrc/4/");
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
							imageUrl = response.getResult().getImgSrc();
							ivAddImg.setImageBitmap(bitmap);
							ToolToast.showShort(response.getMessage());
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						ProgressHelper.getInstance().cancel();
					}

				});
	}

	/**
	 * 意见反馈
	 * 
	 * @author: bangwei.yang
	 */
	private void addJianYi() {
		String contact = edtTxtContact.getText().toString();
		String content = edtTxtContent.getText().toString();
		if (TextUtils.isEmpty(contact)) {
			ToolToast.showShort("请输入您的手机号或邮箱");
			return;
		}
		if (TextUtils.isEmpty(content)) {
			ToolToast.showShort("请输入反馈的意见");
			return;
		}
		if (TextUtils.isEmpty(imageUrl)) {
			ToolToast.showShort("请上传图片");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/AddJianYi");// 方法
		httpClient.url.append("/" + content);// Content：内容，contact：联系方式，imgeurl1：一张图片
		httpClient.url.append("/" + contact);
		httpClient.url.append("/" + imageUrl);
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

	/*
	 * 获取返回的结果 (non-Javadoc)
	 * 
	 * @author: bangwei.yang
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0x100:
				String filePath = ToolPhoto.getResultPhotoPath(this, data,
						ToolStorage.getImageDir(act));
				bitmap = ToolPhoto.extractThumbNail(filePath, 150, 150, true);
				filePath = ToolPhoto.saveThumbNail(act, filePath, 640, 853);
				upload(filePath);
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
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.ivAddImg, R.id.btnSave })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivAddImg:// 图片
			if (TextUtils.isEmpty(imageUrl)) {
				ToolPhoto.openAlbum(this, 0x100);
			} else {
				imageUrl = "";
				ivAddImg.setImageResource(R.drawable.personal_75);
			}
			break;
		case R.id.btnSave:// 保存
			addJianYi();
			break;
		default:
			break;
		}
	}
}
