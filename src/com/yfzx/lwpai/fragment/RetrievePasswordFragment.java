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
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MyFragmentPagerAdapter;

/**
 * 找回密码
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月1日
 */
public class RetrievePasswordFragment extends BaseFragment {
	public static final String TAG = HomeClassifyFragment.class.getSimpleName();
	Resources resources;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView tvForgetPassword, tvPaymentCode;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one, position_two;
	public final static int num = 2;
	Fragment home1;
	Fragment home2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_retrieve_password,
				container, false);
		ViewUtils.inject(this, view);
		resources = getResources();
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(0, offset, 0, 0);
		tvForgetPassword
				.setTextColor(resources.getColor(R.color.include_title));
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);
		initViews();
		initDatas();
		return view;
	}

	/**
	 * 文本界面
	 * 
	 * @author: yizhong.xu
	 * @param parentView
	 */
	private void InitTextView(View parentView) {
		tvForgetPassword = (TextView) parentView
				.findViewById(R.id.tvForgetPassword);
		tvPaymentCode = (TextView) parentView.findViewById(R.id.tvPaymentCode);

		tvForgetPassword.setOnClickListener(new MyOnClickListener(0));
		tvPaymentCode.setOnClickListener(new MyOnClickListener(1));
	}

	private void InitViewPager(View parentView) {
		mPager = (ViewPager) parentView.findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		home1 = new ForgetPasswordFragment();
		home2 = new AlternatePasswordFragment();

		fragmentsList.add(home1);
		fragmentsList.add(home2);

		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);

		// home1 = new NewestAnnounceFragment();
		// home2 = new NewestAnnounceFragment();
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
		// 设置标题

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
					tvPaymentCode.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvForgetPassword.setTextColor(resources
						.getColor(R.color.include_title));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					tvForgetPassword.setTextColor(resources
							.getColor(android.R.color.black));
				}
				tvPaymentCode.setTextColor(resources
						.getColor(R.color.include_title));
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

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	public void initViews() {
	}

	/**
	 * 初始化弹窗
	 * 
	 * @author: bangwei.yang
	 */
	public void initPop() {
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDatas() {
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({})
	public void onClick(View v) {

	}
}
