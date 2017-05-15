package com.yfzx.lwpai.fragment;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MyFragmentPagerAdapter;
import com.yfzx.lwpai.view.SlideShowView;

/**
 * 首页分类下部
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月26日
 */
public class HomeClassifyFragment extends BaseFragment {

	public static final String TAG = HomeClassifyFragment.class.getSimpleName();
	Resources resources;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView tvLuckAuction, tvRedPacket, tvOneYuan;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one, position_two, position_three;
	public final static int num = 3;
	Fragment home1;
	Fragment home2;
	Fragment home3;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initDates();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_classify,
				container, false);
		// 绑定界面UI
		ViewUtils.inject(this, view);
		resources = getResources();

		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);

		TranslateAnimation animation = new TranslateAnimation(position_one,
				offset, 0, 0);
		tvRedPacket.setTextColor(resources.getColor(android.R.color.black));
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
		return view;
	}

	/**
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initView() {

	}

	/**
	 * 文本界面
	 * 
	 * @author: yizhong.xu
	 * @param parentView
	 */
	private void InitTextView(View parentView) {
		tvLuckAuction = (TextView) parentView.findViewById(R.id.tvLuckAuction);
		tvRedPacket = (TextView) parentView.findViewById(R.id.tvRedPacket);
		tvOneYuan = (TextView) parentView.findViewById(R.id.tvOneYuan);

		tvLuckAuction.setOnClickListener(new MyOnClickListener(0));
		tvRedPacket.setOnClickListener(new MyOnClickListener(1));
		tvOneYuan.setOnClickListener(new MyOnClickListener(2));
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDates() {

	}

	private void InitViewPager(View parentView) {
		mPager = (ViewPager) parentView.findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		home1 = new LuckAuctionFragment();
		home2 = new LuckAuctionFragment();
		home3 = new LuckAuctionFragment();

		fragmentsList.add(home1);
		fragmentsList.add(home2);
		fragmentsList.add(home3);

		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);

	}

	private void InitWidth(View parentView) {
		ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 2);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
		position_two = 2 * avg + offset;
		position_three = 3 * avg + offset;

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
					tvRedPacket.setTextColor(resources
							.getColor(android.R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, offset, 0,
							0);
					tvOneYuan.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvLuckAuction.setTextColor(resources
						.getColor(android.R.color.holo_blue_dark));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					tvLuckAuction.setTextColor(resources
							.getColor(android.R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_one, 0, 0);
					tvOneYuan.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvRedPacket.setTextColor(resources
						.getColor(android.R.color.holo_blue_dark));
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
					tvLuckAuction.setTextColor(resources
							.getColor(android.R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_two, 0, 0);
					tvRedPacket.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvOneYuan.setTextColor(resources
						.getColor(android.R.color.holo_blue_dark));
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

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};
}
