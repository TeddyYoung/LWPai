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
 * 最新揭晓
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
public class LatestFragment extends BaseFragment {

	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 中奖码
	@ViewInject(R.id.tvWinningCode)
	private TextView tvWinningCode;
	// 时间
	@ViewInject(R.id.tvTime)
	private TextView tvTime;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_goods_category,
				container, false);
		ViewUtils.inject(this, view);
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
		mPager.setOffscreenPageLimit(3);
		fragmentsList = new ArrayList<Fragment>();

		home1 = new NewestAnnounceFragment();
		home2 = new NewestRedPacketAnnounceFragment();
		home3 = new NewestOneYuanAnnounceFragment();

		fragmentsList.add(home1);
		fragmentsList.add(home2);
		fragmentsList.add(home3);

		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),
				fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);

	}

	private void InitWidth(View parentView) {
		View view = (View) parentView.findViewById(R.id.ildItt);
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
		view.setVisibility(View.VISIBLE);
		// 设置标题
		tvCenter.setText("最新揭晓");

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

	// private void LastOpen() {
	// xHttpClient httpClient = new xHttpClient(act);
	// httpClient.url.append("api/LastOpen/GetProductList/55");// 方法
	// httpClient.url.append("/" + 3);// 結束公佈的中拍者
	// httpClient.url.append("/" + 30);// 页数
	// httpClient.url.append("/" + 1);// 第几页
	// httpClient.post(new xResopnse() {
	// @Override
	// public void onSuccess(ResponseInfo<String> responseInfo) {
	// ProgressHelper.getInstance().cancel();
	// LastOpen response = JsonUtil.parseObject(act,
	// responseInfo.result, LastOpen.class);
	// if (response != null) {
	// List<ResultEntity> date = response.getResult();
	// tvWinningCode.setText(date.get(1).getp);
	// }
	// }
	// });
	//
	// }
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
