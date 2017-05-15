package com.yfzx.library.widget.dialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.yfzx.library.tools.ToolImage;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.contants.Constants;
import com.yfzx.lwpai.contants.FileContants;

public class ShareDialog implements OnClickListener {
	private static ShareDialog sInstance = null;
	private static String url = "";
	private static String title = "";
	private static String description = "";
	private static Bitmap bitmap = null;
	private static IWXAPI weixinApi;

	public static ShareDialog getInstance() {
		if (sInstance == null) {
			synchronized (ShareDialog.class) {
				if (sInstance == null) {
					sInstance = new ShareDialog();
				}
			}
		}
		return sInstance;
	}

	private CustomDialog mDialog = null;

	private ShareDialog() {
	}

	public void show(Context context, String urlString, String titleString,
			String descriptionString, Bitmap shareBitmap) {
		cancel();
		if (weixinApi == null) {

			weixinApi = WXAPIFactory.createWXAPI(context, Constants.APP_ID);
			weixinApi.registerApp(Constants.APP_ID);
		}
		url = urlString;
		title = titleString;
		description = descriptionString;
		bitmap = shareBitmap;
		mDialog = new CustomDialog(context, R.layout.dialog_share,
				MApplication.self.getWidth(),
				ViewGroup.LayoutParams.WRAP_CONTENT);

		View cancel = mDialog.findViewById(R.id.cancel);
		mDialog.findViewById(R.id.weixin).setOnClickListener(this);
		mDialog.findViewById(R.id.friend_circle).setOnClickListener(this);
		mDialog.findViewById(R.id.mail).setOnClickListener(this);
		mDialog.findViewById(R.id.sms).setOnClickListener(this);
		// mDialog.findViewById(R.id.more).setOnClickListener(this);
		mDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				if (bitmap != null) {
					bitmap.recycle();
					bitmap = null;
				}
			}
		});
		mDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				if (bitmap != null) {
					bitmap.recycle();
					bitmap = null;
				}
			}
		});

		// 取消
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				// okClickListener1.onClick(v);
			}
		});

		mDialog.show();
	}

	public void cancel() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		ToolImage.saveBitmap(FileContants.DIR_BASE + "share_img.png", bitmap,
				true);
		File file = new File(FileContants.DIR_BASE + "share_img.png");
		switch (v.getId()) {
		case R.id.weixin:
			shareToWeiXin(true);
			break;
		case R.id.friend_circle:
			shareToWeiXin(false);
			break;
		case R.id.mail:
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_TEXT, url + "\n" + description);
			intent.putExtra(Intent.EXTRA_SUBJECT, title);
			intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
			intent.setType("image/*");
			intent.setType("message/rfc882");
			mDialog.getContext().startActivity(intent);
			break;
		case R.id.sms:
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));//
			// uri为你的附件的uri
			intent.putExtra("subject", title); // 彩信的主题
			intent.putExtra("sms_body", url + "\n" + description); // 彩信中文字内容
			intent.putExtra(Intent.EXTRA_TEXT, title);
			intent.setType("image/*");// 彩信附件类型
//			intent.setClassName("com.yfzx.lwpai",
//					"com.android.mms.ui.ComposeMessageActivity");
			mDialog.getContext().startActivity(intent);
			break;
		case R.id.more:
			break;
		default:
			break;
		}
		cancel();
	}

	private void shareToWeiXin(boolean isSendFriend) {
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = url;
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = title;
		msg.description = description;
		// 微信分享图片不能大于32kb
		bitmap = compressImage(bitmap);
		if (bitmap == null) {
			return;
		}
		msg.thumbData = Util.bmpToByteArray(bitmap, true);
		System.out.println("图片大小" + msg.thumbData.length + "");

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene = isSendFriend ? SendMessageToWX.Req.WXSceneSession
				: SendMessageToWX.Req.WXSceneTimeline;
		weixinApi.sendReq(req);
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	private Bitmap compressImage(Bitmap bitmap) {
		try {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			// 设置想要的大小
			int newWidth = 99;
			int newHeight = 99;
			// 计算缩放比例
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			// 取得想要缩放的matrix参数
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			// 得到新的图片
			Bitmap image = Bitmap.createBitmap(bitmap, 0, 0, width, height,
					matrix, true);
			bitmap.recycle();
			bitmap = null;

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			int options = 100;
			while (baos.toByteArray().length / 1024 > 32) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				options -= 10;// 每次都减少10
			}
			image.recycle();
			image = null;
			ByteArrayInputStream isBm = new ByteArrayInputStream(
					baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
			isBm.reset();
			// 把压缩后的数据baos存放到ByteArrayInputStream中
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					baos.toByteArray());
			try {
				baos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			baos.reset();
			// 把ByteArrayInputStream数据生成图片;
			return BitmapFactory.decodeStream(isBm, null, null);
		} catch (Exception e) {
			return null;
		} catch (OutOfMemoryError e) {
			return null;
		}
	}
}
