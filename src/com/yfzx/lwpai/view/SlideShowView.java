package com.yfzx.lwpai.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.activity.AccountSecurityActivity;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.activity.HomeShareActivity;
import com.yfzx.lwpai.activity.LoginActivity;
import com.yfzx.lwpai.activity.MainActivity;
import com.yfzx.lwpai.activity.RechargeActivity;
import com.yfzx.lwpai.entity.AdInfoEntity;

/**
 * 轮播广告
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-11
 */
public class SlideShowView extends FrameLayout {

	// 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
	private ImageLoader imageLoader = ImageLoader.getInstance();

	// 自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5;

	// 自动轮播启用开关
	private final static boolean isAutoPlay = true;

	// 自定义轮播图的资源
	private ArrayList<String> imageUrls;

	// 放圆点的View的list
	private List<View> dotViewsList;

	private MyViewPager viewPager;

	// 当前轮播页
	private int currentItem = 0;

	// 定时任务
	private ScheduledExecutorService scheduledExecutorService;
	private Context mContext;

	// 轮播广告的数据
	private List<AdInfoEntity> mData;
	// 标志 1首页轮播 2拍品轮播
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	// Handler
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			viewPager.setCurrentItem(currentItem);
		}

	};

	public SlideShowView(Context context) {
		this(context, null);
	}

	public SlideShowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;

		mData = new ArrayList<AdInfoEntity>();
		dotViewsList = new ArrayList<View>();
		imageUrls = new ArrayList<String>();

	}

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 7,
				TimeUnit.SECONDS);
	}

	/**
	 * 初始化相关Data
	 */
	public void init(List<AdInfoEntity> data) {

		if (data == null || data.size() == 0)
			return;
		for (AdInfoEntity indexData : data) {
			imageUrls.add(indexData.getImgsrc());
		}

		initUI(mContext);
		if (isAutoPlay) {
			startPlay();
		}

	}

	/**
	 * 初始化相关Data
	 */
	public void inits(List<AdInfoEntity> data) {

		if (data == null || data.size() == 0)
			return;

		for (AdInfoEntity indexData : data) {
			if (indexData.getCategoryId().equals("1")) {
				mData.add(indexData);
				imageUrls.add(indexData.getImgsrc());
			}
		}

		initUI(mContext);
		if (isAutoPlay) {
			startPlay();
		}

	}

	/**
	 * 初始化Views等UI
	 */
	private void initUI(final Context context) {
		if (imageUrls == null || imageUrls.size() == 0)
			return;

		LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this,
				true);

		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
		dotLayout.removeAllViews();

		// 热点个数与图片特殊相等
		for (int i = 0; i < imageUrls.size(); i++) {
			ImageView dotView = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
			dotViewsList.add(dotView);
		}

		viewPager = (MyViewPager) findViewById(R.id.viewPager);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

		// 设置最大循环
		viewPager.setCurrentItem(imageUrls.size() * 20);
		currentItem = imageUrls.size() * 20 / 2;

	}

	/**
	 * 填充ViewPager的页面适配器
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			// ((ViewPager) container).removeView(imageViewsList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			position = position % imageUrls.size();
			ImageView imageView = new ImageView(mContext);
			// 1 首页轮播广告
			if (getFlag().equals("1")) {
				imageView.setScaleType(ScaleType.FIT_XY);
			} else {
				imageView.setScaleType(ScaleType.CENTER_INSIDE);
			}

			imageLoader.displayImage(imageUrls.get(position) + "", imageView,
					ImageLoaderUtil.imageOptions);

			// 点击广告，根据不同类型，进行不同操作
			// Target：1幸运拍商品跳转id为该商品Id，2为红包区商品跳转id为该商品Id,3为一元拍商品跳转id为该商品Id，
			// 4为领取红包活动页面，5为幸运拍列表页，6为红包区列表页，7为一元拍列表页，
			// 8为晒单列表页，9为开启交易密码页面，10充值页面
			if (!mData.isEmpty()) {
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						AdInfoEntity data = mData.get(currentItem
								% imageUrls.size());
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						switch (data.getTarget()) {
						case "1":// 1幸运拍商品跳转id为该商品Id
							intent.setClass(mContext,
									GoodsLuckDetailActivity.class);
							bundle.putInt("category", 0);// 0正在进行 1即将开始 2结束
							bundle.putInt("type", 55); // 3一元拍 55幸运拍 56红包区
							bundle.putInt("lucky", 1); // 显示TOP3000
							bundle.putString("goodsId", data.getId());
							intent.putExtras(bundle);
							break;
						case "2":// 2为红包区商品跳转id为该商品Id
							intent.setClass(mContext,
									GoodsLuckDetailActivity.class);
							bundle.putInt("category", 0);// 0正在进行 1即将开始 2结束
							bundle.putInt("type", 56); // 3一元拍 55幸运拍 56红包区
							bundle.putInt("lucky", 1); // 显示TOP3000
							bundle.putString("goodsId", data.getId());
							intent.putExtras(bundle);
							break;
						case "3":// 3为一元拍商品跳转id为该商品Id
							intent.setClass(mContext,
									GoodsLuckDetailActivity.class);
							bundle.putInt("category", 0);// 0正在进行 1即将开始 2结束
							bundle.putInt("type", 3); // 3一元拍 55幸运拍 56红包区
							bundle.putInt("lucky", 1); // 显示TOP3000
							bundle.putString("goodsId", data.getId());
							intent.putExtras(bundle);
							break;
						case "4":// 4为领取红包活动页面

							break;
						case "5":// 5为幸运拍列表页
							((MainActivity) mContext).clickGood(1);
							break;
						case "6":// 6为红包区列表页
							((MainActivity) mContext).clickGood(2);
							break;
						case "7":// 7为一元拍列表页
							((MainActivity) mContext).clickGood(3);
							break;
						case "8":// 8为晒单列表页
							intent.setClass(mContext, HomeShareActivity.class);
							break;
						case "9":// 9为开启交易密码页面
							if (!UserManage.isLogin()) {
								intent.setClass(mContext, LoginActivity.class);
								mContext.startActivity(intent);
								return;
							}
							intent.setClass(mContext,
									AccountSecurityActivity.class);
							break;
						case "10":// 10充值页面
							if (!UserManage.isLogin()) {
								intent.setClass(mContext, LoginActivity.class);
								mContext.startActivity(intent);
								return;
							}
							intent.setClass(mContext, RechargeActivity.class);
							break;
						default:
							return;
						}
						intent.putExtras(bundle);
						mContext.startActivity(intent);
					}
				});
			}

			((ViewPager) container).addView(imageView);

			return imageView;
		}

		@Override
		public int getCount() {
			// return imageUrls.size() == 1 ? 1 : Integer.MAX_VALUE;
			return imageUrls.size() * 20;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}

	}

	/**
	 * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (viewPager.getCurrentItem() == viewPager.getAdapter()
						.getCount() - 1 && !isAutoPlay) {
					viewPager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
					viewPager
							.setCurrentItem(viewPager.getAdapter().getCount() - 1);
				}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int pos) {
			currentItem = pos;
			pos = pos % dotViewsList.size();
			for (int i = 0; i < dotViewsList.size(); i++) {
				if (i == pos) {
					((View) dotViewsList.get(pos))
							.setBackgroundResource(R.drawable.banenr_list_on);
				} else {
					((View) dotViewsList.get(i))
							.setBackgroundResource(R.drawable.banenr_list);
				}
			}
		}

	}

	/**
	 * 执行轮播图切换任务
	 * 
	 */
	private class SlideShowTask implements Runnable {

		@Override
		public void run() {
			synchronized (viewPager) {
				currentItem = currentItem + 1;
				handler.obtainMessage().sendToTarget();
			}
		}

	}

}