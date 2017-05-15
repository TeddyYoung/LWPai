package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MyFragmentPagerAdapter;
import com.yfzx.lwpai.fragment.OrderAllFragment;

/**
 * 我的订单
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-30
 */
@ContentView(R.layout.activity_mine_order)
public class MineOrderActivity extends BaseActivity {

	// 全部按钮
	@ViewInject(R.id.rdoBtnAll)
	private RadioButton rdoBtnAll;
	// 待发货按钮
	@ViewInject(R.id.rdoBtnDelivery)
	private RadioButton rdoBtnDelivery;
	// 待收货按钮
	@ViewInject(R.id.rdoBtnReceipt)
	private RadioButton rdoBtnReceipt;
	// 待晒单按钮
	@ViewInject(R.id.rdoBtnShare)
	private RadioButton rdoBtnShare;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	private ViewPager vpMyOrder; // v4类视图页面
	private ImageView ivBottomLine;// 图片下划线
	private ArrayList<Fragment> fragmentsList; // fragment数组
	private Resources resources; // 文字资源

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one, position_two, position_three;
	private FragmentManager fragmentManager;
	private int curragePage = 0;
	public final static int num = 4;
	public static int orderAllFlag = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		resources = getResources();
		initWidth();// 下划线
		initWidget();// 初始化界面
		initTextView();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	public void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvCenter.setText("我的订单");
		fragmentManager = getSupportFragmentManager();
		TranslateAnimation animation = new TranslateAnimation(0, offset, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
		vpMyOrder = (ViewPager) findViewById(R.id.vpMyOrder);
		vpMyOrder.setOffscreenPageLimit(4);
		fragmentsList = new ArrayList<Fragment>();
		// frament页面
		Fragment[] collor = { new OrderAllFragment(1), new OrderAllFragment(2),
				new OrderAllFragment(3), new OrderAllFragment(4) };
		// 数组
		for (int i = 0; i < collor.length; i++) {
			fragmentsList.add(collor[i]);
		}
		vpMyOrder.setAdapter(new MyFragmentPagerAdapter(fragmentManager,
				fragmentsList));
		vpMyOrder.setOnPageChangeListener(new OnPageChangeshare());
		if (getIntent().getExtras() != null) {
			curragePage = getIntent().getExtras().getInt("curragePage");
			if (curragePage == 1) {
				animation = new TranslateAnimation(position_one, position_one,
						position_one, position_one);
			} else if (curragePage == 2) {
				animation = new TranslateAnimation(position_two, position_two,
						position_two, position_two);
			} else {
				animation = new TranslateAnimation(position_three,
						position_three, position_three, 0);
			}
			animation.setFillAfter(false);
			animation.setDuration(300);
			ivBottomLine.startAnimation(animation);
		}
		vpMyOrder.setCurrentItem(curragePage);
	}

	/**
	 * 下划线
	 * 
	 * @author: songbing.zhou
	 * @param view
	 */
	private void initWidth() {
		ivBottomLine = (ImageView) findViewById(R.id.ivBottomShare);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 4);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
		position_two = 2 * avg + offset;
		position_three = 3 * avg + offset;

	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {
	}

	/**
	 * 点击监听
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-6-28
	 */
	public class OnClickListener implements View.OnClickListener {
		private int index = 0;

		public OnClickListener(int i) {
			index = i;
		}

		public OnClickListener() {

		}

		@Override
		public void onClick(View v) {
			vpMyOrder.setCurrentItem(index);
		}
	};

	/**
	 * viewpager适配器
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-5-21
	 */
	public class OnPageChangeshare implements OnPageChangeListener {
		Animation animation = null;

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:// 全部
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
					rdoBtnDelivery.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, offset, 0,
							0);
					rdoBtnReceipt.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three, offset,
							0, 0);
					rdoBtnShare.setTextColor(resources.getColor(R.color.black));
				}
				rdoBtnAll.setTextColor(resources.getColor(R.color.btn_orange));
				orderAllFlag = 1;
				break;
			case 1:// 待发货
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					rdoBtnAll.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_one, 0, 0);
					rdoBtnReceipt.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three,
							position_one, 0, 0);
					rdoBtnShare.setTextColor(resources.getColor(R.color.black));
				}
				rdoBtnDelivery.setTextColor(resources
						.getColor(R.color.btn_orange));
				break;
			case 2:// 待收货
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
					rdoBtnAll.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_two, 0, 0);
					rdoBtnDelivery.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three,
							position_two, 0, 0);
					rdoBtnShare.setTextColor(resources.getColor(R.color.black));
				}
				rdoBtnReceipt.setTextColor(resources
						.getColor(R.color.btn_orange));
				break;
			case 3:// 待晒单
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_three,
							0, 0);
					rdoBtnAll.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_three, 0, 0);
					rdoBtnDelivery.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_three, 0, 0);
					rdoBtnReceipt.setTextColor(resources
							.getColor(R.color.black));
				}
				rdoBtnShare
						.setTextColor(resources.getColor(R.color.btn_orange));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			ivBottomLine.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param parentView
	 */
	private void initTextView() {
		rdoBtnAll.setOnClickListener(new OnClickListener(0));
		rdoBtnDelivery.setOnClickListener(new OnClickListener(1));
		rdoBtnReceipt.setOnClickListener(new OnClickListener(2));
		rdoBtnShare.setOnClickListener(new OnClickListener(3));
	}

	/**
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:
			finish();
			break;
		default:
			break;
		}
	}
}
