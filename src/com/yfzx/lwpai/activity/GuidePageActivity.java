package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.ViewPagerAdapter;
import com.yfzx.lwpai.util.Helper;

/**
 * 引导页
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-1
 */
public class GuidePageActivity extends BaseActivity implements OnClickListener,
		OnPageChangeListener {

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private Button btnGo;

	// 引导图片资源
	private int[] pics = { R.drawable.guide2, R.drawable.guide3,
			R.drawable.guide4 };

	// 底部小店图片
	private ImageView[] dots;

	// 记录当前选中位置
	private int currentIndex;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide_page);
		btnGo = (Button) findViewById(R.id.btn_go);
		btnGo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Helper.starAct(GuidePageActivity.this, MainActivity.class);
				finish();
			}
		});

		views = new ArrayList<View>();

		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// 初始化引导图片列表
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setLayoutParams(mParams);
			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		vp = (ViewPager) findViewById(R.id.viewpager);
		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(views);
		vp.setAdapter(vpAdapter);
		// 绑定回调
		vp.setOnPageChangeListener(this);

		// 初始化底部小点
		// initDots();
	}

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

		dots = new ImageView[pics.length];

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
		layoutParams.leftMargin = 20;
		layoutParams.rightMargin = 20;
		layoutParams.topMargin = 20;
		layoutParams.bottomMargin = 20;

		// 循环取得小点图片
		for (int i = 0; i < pics.length; i++) {
			ImageView dot = new ImageView(this);
			ll.addView(dot, layoutParams);
			dots[i] = dot;
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应

			if (i == 0) {
				// dots[i].setBackgroundResource(R.drawable.bg_green1);
			} else {
				// dots[i].setBackgroundResource(R.drawable.bg_gary1);
			}
		}

		currentIndex = 0;
	}

	/**
	 * 设置当前的引导页
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}

		vp.setCurrentItem(position);
	}

	/**
	 * 这只当前引导小点的选中
	 */
	private void setCurDot(int positon) {
		if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
			return;
		}

		// dots[positon].setBackgroundResource(R.drawable.bg_green1);
		// dots[currentIndex].setBackgroundResource(R.drawable.bg_gary1);

		currentIndex = positon;
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int position) {
		// 设置底部小点选中状态
		setCurDot(position);

		// 显示进入的按钮
		if (position == views.size() - 1) {
			btnGo.setVisibility(View.VISIBLE);
		} else {
			btnGo.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
	}
}