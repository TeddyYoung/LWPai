package com.yfzx.lwpai.activity;

import java.util.Hashtable;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GetVersionUrlEntity;
import com.yfzx.lwpai.entity.UserInfo;
import com.yfzx.lwpai.entity.UserInfo.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 关于
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 详情
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// 版本
	@ViewInject(R.id.tvVersionName)
	private TextView tvVersionName;
	// 二维码图片
	@ViewInject(R.id.ivQRcode)
	private ImageView ivQRcode;

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
		tvCenter.setText("关于");
		tvCenter.setTextColor(Color.BLACK);
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvVersionName.setText("For Android "
				+ getResources().getString(R.string.versionName));
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		GetVersionUrl();
	}

	private void GetVersionUrl() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Version/GetVersionUrl");// 方法
		httpClient.url.append("/" + 0);// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetVersionUrlEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetVersionUrlEntity.class);
				if (response != null) {

					try {
						Bitmap bm = qr_code(response.getResult()
								.getDownLoadUrl(), BarcodeFormat.QR_CODE);

						ImageView img = (ImageView) findViewById(R.id.ivQRcode);

						img.setImageBitmap(bm);
					} catch (WriterException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	private Bitmap qr_code(String string, BarcodeFormat format)
			throws WriterException {
		MultiFormatWriter writer = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
		hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = writer.encode(string, format, 1000, 1000, hst);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}

			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap,具体参考api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 点击事件
	 * 
	 * @author Gy
	 * @param view
	 */
	@OnClick({ R.id.ivLeft })
	public void OnClick(View view) {
		switch (view.getId()) {
		case R.id.ivLeft:
			finish();
			break;

		default:
			break;
		}
	}
}
