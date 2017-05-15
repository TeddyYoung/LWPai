package com.yfzx.lwpai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.LinearInterpolator;

import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.tools.ToolLog;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.entity.GoodsIdEntity;
import com.yfzx.lwpai.service.ShakeListener.OnShakeListener;
import com.yfzx.lwpai.sqlite.GoodsIDHelper;
import com.yfzx.lwpai.sqlite.SQLiteHelper;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.view.GifView;

public class AnimService extends Service {
	private WindowManager mWm;// 窗口管理
	private WindowManager.LayoutParams mLayoutParams;// 布局参数
	private List<View> animViews = new ArrayList<View>();
	/**
	 * 传感器监听器
	 */
	private ShakeListener mShakeListener;

	public static void startAnim(Context context, String animType,
			String goodsID, int type) {
		Intent intent = new Intent(context, AnimService.class);
		intent.putExtra("animType", animType);
		intent.putExtra("category", 0);// 0进行 1即将开始 2结束
		intent.putExtra("lucky", 1); // 显示TOP3000
		intent.putExtra("goodsId", goodsID);// 商品ID
		intent.putExtra("type", type);// 0一元拍，2红包区，2幸运拍
		context.startService(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mWm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		createLayout();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			if (intent.hasExtra("animType") && intent.hasExtra("category")
					&& intent.hasExtra("lucky") && intent.hasExtra("goodsId")
					&& intent.hasExtra("type")) {
				String animType = intent.getStringExtra("animType");
				int category = intent.getIntExtra("category", 0);
				int lucky = intent.getIntExtra("lucky", 1);
				String goodsID = intent.getStringExtra("goodsId");
				int type = intent.getIntExtra("type", 1);
				startAnimView(animType, goodsID, type, category, lucky);
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		for (View view : animViews) {
			try {
				mWm.removeView(view);
				if (mShakeListener != null) {
					mShakeListener.stop();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onDestroy();

	}

	private void startAnimView(final String animType, final String goodsID,
			final int type, final int category, final int lucky) {
		final View animView = View.inflate(this, R.layout.layout_gif, null);
		final GifView gitView = (GifView) animView.findViewById(R.id.gif_view);
		if (animType.equals("1")) {
			gitView.setMovieResource(R.raw.df1);
		} else {
			gitView.setMovieResource(R.raw.df2);
		}
		final ValueAnimator anim = ValueAnimator.ofFloat(1f, -0.9f);

		// 实例化加速度传感器检测类
		mShakeListener = new ShakeListener(this);
		// 传感器监听器
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			public void onShake() {
				anim.cancel();
				Intent intent = new Intent(AnimService.this,
						GoodsLuckDetailActivity.class);
				intent.putExtra("category", category);// 0进行 1即将开始 2结束
				intent.putExtra("lucky", lucky); // 显示TOP3000
				intent.putExtra("goodsId", goodsID);// 商品ID
				intent.putExtra("type", type);// 0一元拍，2红包区，2幸运拍
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				mShakeListener.stop();
			}
		});
		gitView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				anim.cancel();
				Intent intent = new Intent(AnimService.this,
						GoodsLuckDetailActivity.class);
				intent.putExtra("category", category);// 0进行 1即将开始 2结束
				intent.putExtra("lucky", lucky); // 显示TOP3000
				intent.putExtra("goodsId", goodsID);// 商品ID
				intent.putExtra("type", type);// 0一元拍，2红包区，2幸运拍
				Log.i("zzgoodsId", goodsID+"");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				mShakeListener.stop();
			}
		});
		if (animType.equals("1")) {
			gitView.setTranslationX(MApplication.self.getWidth());
			anim.setDuration(10000);
		} else {
			anim.setDuration(gitView.getMovie().duration());
		}
		anim.setInterpolator(new LinearInterpolator());
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (animType.equals("1")) {
					float value = (Float) animation.getAnimatedValue();
					gitView.setTranslationX(MApplication.self.getWidth()
							* value);
				}
			}
		});
		anim.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (animViews.contains(animView)) {
					mWm.removeView(animView);
					animViews.remove(animView);
					mShakeListener.stop();
					if (animViews.size() == 0) {
						stopSelf();
					}
				}

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				if (animViews.contains(animView)) {
					mWm.removeView(animView);
					animViews.remove(animView);
					mShakeListener.stop();
					if (animViews.size() == 0) {
						stopSelf();
					}
				}

			}
		});
		mLayoutParams.y = new Random().nextInt(MApplication.self.getHeight());
		mWm.addView(animView, mLayoutParams);
		animViews.add(animView);
		anim.start();
	}

	private WindowManager.LayoutParams createLayout() {
		mLayoutParams = new WindowManager.LayoutParams();
		mLayoutParams.x = 0;
		mLayoutParams.y = MApplication.self.getHeight() / 2;
		mLayoutParams.gravity = Gravity.LEFT | Gravity.CENTER;
		mLayoutParams.width = MApplication.self.getWidth();
		mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mLayoutParams.format = PixelFormat.RGBA_8888;
		mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		mLayoutParams.type = LayoutParams.TYPE_PHONE;
		mLayoutParams.token = null;
		mLayoutParams.alpha = 1.0f;
		mLayoutParams.setTitle("anim");
		mLayoutParams.packageName = this.getPackageName();
		return mLayoutParams;
	}
}
