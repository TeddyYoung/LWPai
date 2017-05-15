package com.yfzx.lwpai.activity;

import java.io.File;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolDateTimePicker;
import com.yfzx.library.tools.ToolDateTimePicker.DateCallBack;
import com.yfzx.library.tools.ToolImage;
import com.yfzx.library.tools.ToolPhoto;
import com.yfzx.library.tools.ToolStorage;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.ImageEntity;
import com.yfzx.lwpai.entity.UserInformationEntity;
import com.yfzx.lwpai.entity.UserInformationEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的账户界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
@ContentView(R.layout.activity_my_account)
public class MyAccountActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 用户名
	@ViewInject(R.id.tvUsername)
	private TextView tvUsername;
	// 昵称
	@ViewInject(R.id.tvNickname)
	private TextView tvNickname;
	// 性别
	@ViewInject(R.id.tvSex)
	private TextView tvSex;
	// 生日
	@ViewInject(R.id.tvDatebirth)
	private TextView tvDatebirth;
	// 头像
	@ViewInject(R.id.ivHeadPortrait)
	private ImageView ivHeadPortrait;

	// 图片id
	private String imageUrl;

	/**
	 * 实体
	 */
	private ResultEntity userInfo;

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
		tvCenter.setText("我的账户");
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		loadLazy();
	}

	/**
	 * 加载缓存数据
	 * 
	 * @author: yizhong.xu
	 */
	private void loadLazy() {
		getUserInformation();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getUserInformation();
	}

	/**
	 * 获取用户详情
	 * 
	 * @author: bangwei.yang
	 */
	private void getUserInformation() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserInformation");// 方法
		httpClient.url.append("/" + UserManage.getUser().getUserId());//
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				UserInformationEntity response = JsonUtil.parseObject(act,
						responseInfo.result, UserInformationEntity.class);
				if (response != null) {
					List<ResultEntity> data = response.getResult();
					userInfo = data.get(0);
					// 存储到缓存中
					CacheManage.put("user_information", data);

					// 头像
					ImageLoaderUtil.getRoundImage(userInfo.getFaceImage(),
							ivHeadPortrait);

					tvUsername.setText(userInfo.getUsername());
					tvNickname.setText(userInfo.getNickname());
					// 生日不为空时
					if (!TextUtils.isEmpty(userInfo.getBirthDate())) {
						tvDatebirth.setText(userInfo.getBirthDate().replace(
								"/", "-"));
					}
					// 设置男女，默认为保密
					if (userInfo.getSex().equals("Male")) {
						tvSex.setText("男");
					}
					if (userInfo.getSex().equals("Female")) {
						tvSex.setText("女");
					}

				}
			}
		});

	}

	/**
	 * 获取用户详情
	 * 
	 * @author: bangwei.yang
	 */
	private void updateUserBirthDate(String birthdate) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/UpdateUserBirthDate/");// 方法
		httpClient.url.append(birthdate);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
				}
			}
		});

	}

	/**
	 * 上传图片
	 * 
	 * @author: bangwei.yang
	 */
	public void upload(String file, final Bitmap cropBm) {
		xHttpClient client = new xHttpClient(act);
		client.url.append("api/ImageHelper/ImageSrc/1/");
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
						ImageEntity response = JsonUtil.parseObject(act,
								responseInfo.result, ImageEntity.class);
						if (response != null) {
							if (!response.getSuccess().toLowerCase()
									.equals("true")) {
								ToolToast.showShort(response.getMessage());
								return;
							}
							imageUrl = response.getResult().getImgSrc();
							ImageLoaderUtil.getRoundImage(imageUrl,
									ivHeadPortrait);
							UserManage.getUser().setFaceImage(imageUrl);
						}
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						ProgressHelper.getInstance().cancel();
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
	protected void onActivityResult(int requestCode, int resultCode,
			final Intent data) {
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
							String file = ToolStorage.getAvatorDir(act)
									+ File.separator + "Avator_"
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
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.llNickname, R.id.llSecurity, R.id.llSex,
			R.id.llSite, R.id.llCode, R.id.llDatebirth, R.id.ivHeadPortrait })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivHeadPortrait:// 头像
			ToolPhoto.selectPicture(act, 0x100);
			break;
		case R.id.llNickname:// 昵称
			Bundle bundle = new Bundle();
			bundle.putString("userName", tvNickname.getText().toString());
			showActivity(act, ChangeNicknameActivity.class, bundle);
			break;
		case R.id.llSecurity:// 账号安全
			Bundle bundle2 = new Bundle();
			bundle2.putString("userName", tvUsername.getText().toString());
			showActivity(act, AccountSecurityActivity.class, bundle2);
			break;
		case R.id.llSex:// 性别
			Bundle bundle1 = new Bundle();
			bundle1.putString("sex", tvSex.getText().toString());
			showActivity(act, SetSexActivity.class, bundle1);
			break;
		case R.id.llSite:// 地址管理
			showActivity(act, AddressActivity.class);
			break;
		case R.id.llDatebirth:// 修改生日
			new ToolDateTimePicker(act, tvDatebirth, 1)
					.show(new DateCallBack() {
						@Override
						public void onDate(String string) {
							updateUserBirthDate(string);
						}
					});
			break;
		case R.id.llCode:// 二维码
			if (userInfo != null) {
				Bundle bundle3 = new Bundle();
				bundle3.putString("faceImage", userInfo.getFaceImage());
				bundle3.putString("nickname", userInfo.getNickname());
				showActivity(act, TwoCodeActivity.class, bundle3);
			}
			break;
		default:
			break;
		}
	}
}
