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
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.ImageEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的订单
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-30
 */
@ContentView(R.layout.activity_mine_order_share)
public class MineOrderShareActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 产品名称
	@ViewInject(R.id.tvProductName)
	private TextView tvProductName;
	// 订单日期
	@ViewInject(R.id.tvOrderDate)
	private TextView tvOrderDate;
	// 订单图片
	@ViewInject(R.id.ivOrderHead)
	private ImageView ivOrderHead;
	// 晒单标题
	@ViewInject(R.id.edtTxtTitle)
	private EditText edtTxtTitle;
	// 晒单
	@ViewInject(R.id.edtTxtShare)
	private EditText edtTxtShare;

	// 图片
	@ViewInject(R.id.ivAddImg)
	private ImageView ivAddImg;

	// 存放图片id
	private String imageUrl = "";
	private Bitmap bitmap;
	private String orderDate;
	private String orderId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	public void initWidget() {
		tvCenter.setText("晒单");
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {
		Bundle bundle = getIntent().getExtras();
		String thumbnailUrl100 = bundle.getString("thumbnailUrl100");
//		String thumbnailUrl440 = bundle.getString("thumbnailUrl440");
		String productName = bundle.getString("productName");
		orderDate = bundle.getString("orderDate");
		orderId = bundle.getString("orderId");
		// 图片
		ImageLoaderUtil.getByUrl(thumbnailUrl100, ivOrderHead);
		// 产品名称
		tvProductName.setText(productName);
		// 订单日期
		tvOrderDate.setText("订单日期: " + orderDate);
	}

	/**
	 * 上传图片
	 * 
	 * @author: songbing.zhou
	 */
	public void upload(String file) {
		xHttpClient client = new xHttpClient(act);
		client.url.append("api/ImageHelper/ImageSrc/2/");
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

	private void AddOrderShare() {

		String title = edtTxtTitle.getText().toString();
		String share = edtTxtShare.getText().toString();
		if (TextUtils.isEmpty(title)) {
			ToolToast.showShort("请输入您的晒单标题");
			return;
		}
		if (TextUtils.isEmpty(share)) {
			ToolToast.showShort("晒单不能为空");
			return;
		}
		if (TextUtils.isEmpty(imageUrl)) {
			ToolToast.showShort("请上传图片");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/AddOrderShare");// 方法
		httpClient.url.append("/" + title);// 标题
		httpClient.url.append("/" + share);// 内容
		httpClient.url.append("/" + orderId);// 订单号
		httpClient.url.append("/" + imageUrl);// 图片
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
	 * @author: songbing.zhou
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
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.btnSubmit, R.id.ivAddImg })
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
		case R.id.btnSubmit:// 提交
			AddOrderShare();
			break;
		}
	}
}
