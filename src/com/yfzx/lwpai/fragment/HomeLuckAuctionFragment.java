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
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MyFragmentPagerAdapter;

/**
 * 最新揭晓 中拍记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
public class HomeLuckAuctionFragment extends BaseFragment {

	public static final String TAG = HomeLuckAuctionFragment.class
			.getSimpleName();
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;

	Resources resources;
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private ImageView ivBottomLine;
	private TextView tvLuckAuction, tvRedPacket, tvOneYuan;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;

	private int position_one, position_two, position_three;
	public static int num = 0;
	private int lucky;// 显示
	private String goodsid;// 商品ID
	private int type;// 区分是红包区幸运拍一元拍

	Fragment home1;
	Fragment home2;
	Fragment home3;

	public static HomeLuckAuctionFragment newInstance(String goodsId,
			int lucky, int type) {
		Bundle bundle = new Bundle();
		bundle.putString("goodsPTId", goodsId);
		bundle.putInt("lucky", lucky);
		bundle.putInt("type", type);
		HomeLuckAuctionFragment fragment = new HomeLuckAuctionFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_classify,
				container, false);
		ViewUtils.inject(this, view);

		Bundle bundle = getArguments();
		if (bundle != null) {
			goodsid = getArguments().getString("goodsPTId");
			lucky = getArguments().getInt("lucky");
			type = getArguments().getInt("type");
		}
		resources = getResources();
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(0, offset, 0, 0);
		tvLuckAuction.setTextColor(resources.getColor(R.color.include_title));
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
		tvLuckAuction = (TextView) parentView.findViewById(R.id.tvLuckAuction);
		tvRedPacket = (TextView) parentView.findViewById(R.id.tvRedPacket);
		tvOneYuan = (TextView) parentView.findViewById(R.id.tvOneYuan);

		tvLuckAuction.setOnClickListener(new MyOnClickListener(0));
		tvRedPacket.setOnClickListener(new MyOnClickListener(1));
		tvOneYuan.setOnClickListener(new MyOnClickListener(2));
	}

	private void InitViewPager(View parentView) {
		mPager = (ViewPager) parentView.findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();

		home1 = new DetailsPriceRecordFragment().newInstance(goodsid, type);
		home2 = new DetailsPriceWinFragment().newInstance(goodsid,type);

		fragmentsList.add(home1);
		fragmentsList.add(home2);

		if (lucky == 1) {
			tvOneYuan.setVisibility(View.VISIBLE);
			home3 = new DetailsTop3000Fragment().newInstance(goodsid);
			fragmentsList.add(home3);
		} else {

			tvOneYuan.setVisibility(View.GONE);
		}

		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);

	}

	private void InitWidth(View parentView) {
		if (lucky == 1) {
			num = 3;
			ivBottomLine = (ImageView) parentView
					.findViewById(R.id.ivBottomShare);
			ivBottomLine.setVisibility(View.VISIBLE);
		} else {
			num = 2;
			ivBottomLine = (ImageView) parentView
					.findViewById(R.id.ivBottomShare1);
			ivBottomLine.setVisibility(View.VISIBLE);
		}
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
						.getColor(R.color.include_title));
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
						.getColor(R.color.include_title));
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
		tvLuckAuction.setText("出价记录");
		tvRedPacket.setText("中拍记录");
		tvOneYuan.setText("top3000");
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
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:

			break;

		default:
			break;
		}
	}

}
