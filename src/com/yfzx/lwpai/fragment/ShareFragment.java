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
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MyFragmentPagerAdapter;

/**
 * 晒单
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-28
 */
public class ShareFragment extends BaseFragment {

	public static final String TAG = ShareFragment.class.getSimpleName();

	// 最新按钮
	@ViewInject(R.id.rdoBtnNewest)
	private RadioButton rdoBtnNewest;
	// 精华按钮
	@ViewInject(R.id.rdoBtnEssence)
	private RadioButton rdoBtnEssence;
	// 推荐按钮
	@ViewInject(R.id.rdoBtnRecommend)
	private RadioButton rdoBtnRecommend;
	// 人气按钮
	@ViewInject(R.id.rdoBtnPopular)
	private RadioButton rdoBtnPopular;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	private ViewPager vpShare; // 视图页面
	private ImageView ivBottomLine;// 图片下划线
	private ArrayList<Fragment> fragmentsList; // fragment数组
	private Resources resources; // 文字资源

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one, position_two, position_three;
	public final static int num = 4;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_share, container, false);
		ViewUtils.inject(this, view);
		resources = getResources();
		InitWidth(view);// 下划线
		initWidget(view);// 初始化界面
		InitTextView(view);
		initData();
		return view;
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	public void initWidget(View view) {
		vpShare = (ViewPager) view.findViewById(R.id.vpShare);
		fragmentsList = new ArrayList<Fragment>();
		// frament页面 数组
		Fragment[] collor = { new ShareNewestFragment(0),
				new ShareNewestFragment(2), new ShareNewestFragment(3),
				new ShareNewestFragment(4) };
		for (int i = 0; i < collor.length; i++) {
			fragmentsList.add(collor[i]);
		}
		vpShare.setAdapter(new MyFragmentPagerAdapter(
				getChildFragmentManager(), fragmentsList));
		vpShare.setOnPageChangeListener(new OnPageChangeshare());
		vpShare.setCurrentItem(0);
	}

	/**
	 * 下划线
	 * 
	 * @author: songbing.zhou
	 * @param view
	 */
	private void InitWidth(View view) {
		ivBottomLine = (ImageView) view.findViewById(R.id.ivBottomLine);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
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
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({})
	public void onClick(View v) {

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
			vpShare.setCurrentItem(index);
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
			case 0:

				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
					rdoBtnEssence.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, offset, 0,
							0);
					rdoBtnRecommend.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three, offset,
							0, 0);
					rdoBtnPopular.setTextColor(resources
							.getColor(R.color.black));
				}
				rdoBtnNewest.setTextColor(resources
						.getColor(R.color.btn_orange));
				break;
			case 1:

				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					rdoBtnNewest
							.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_one, 0, 0);
					rdoBtnRecommend.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three,
							position_one, 0, 0);
					rdoBtnPopular.setTextColor(resources
							.getColor(R.color.black));
				}
				rdoBtnEssence.setTextColor(resources
						.getColor(R.color.btn_orange));
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
					rdoBtnNewest
							.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_two, 0, 0);
					rdoBtnEssence.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 3) {
					animation = new TranslateAnimation(position_three,
							position_two, 0, 0);
					rdoBtnPopular.setTextColor(resources
							.getColor(R.color.black));
				}
				rdoBtnRecommend.setTextColor(resources
						.getColor(R.color.btn_orange));
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_three,
							0, 0);
					rdoBtnNewest
							.setTextColor(resources.getColor(R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_three, 0, 0);
					rdoBtnEssence.setTextColor(resources
							.getColor(R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_three, 0, 0);
					rdoBtnRecommend.setTextColor(resources
							.getColor(R.color.black));
				}
				rdoBtnPopular.setTextColor(resources
						.getColor(R.color.btn_orange));
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
	private void InitTextView(View parentView) {
		rdoBtnNewest.setOnClickListener(new OnClickListener(0));
		rdoBtnEssence.setOnClickListener(new OnClickListener(1));
		rdoBtnRecommend.setOnClickListener(new OnClickListener(2));
		rdoBtnPopular.setOnClickListener(new OnClickListener(3));
	}

}
