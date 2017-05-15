package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
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
import com.yfzx.lwpai.fragment.AccountRedBagFragment;

/**
 * 我的红包
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
@ContentView(R.layout.activity_account_red_bag)
public class AccountRedBagActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 全部按钮
	@ViewInject(R.id.rdoBtnAll)
	private RadioButton rdoBtnAll;
	// 收入按钮
	@ViewInject(R.id.rdoBtnDelivery)
	private RadioButton rdoBtnDelivery;
	// 支出按钮
	@ViewInject(R.id.rdoBtnReceipt)
	private RadioButton rdoBtnReceipt;

	@ViewInject(R.id.vpMyOrder)
	private ViewPager vpMyOrder;

	private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>(); // fragment数组
	private FragmentManager fragmentManager;
	private int currIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		initWidget();// 初始化界面
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	public void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvCenter.setText("我的红包");
		vpMyOrder.setOffscreenPageLimit(3);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData() {
		// 添加切换的页面 ,全部0，收入1，支出2
		fragmentsList.add(AccountRedBagFragment.newInstance(1));
		fragmentsList.add(AccountRedBagFragment.newInstance(2));
		fragmentsList.add(AccountRedBagFragment.newInstance(3));

		// 设置动画效果
		fragmentManager = getSupportFragmentManager();

		vpMyOrder.setAdapter(new MyFragmentPagerAdapter(fragmentManager,
				fragmentsList));
		vpMyOrder.setOnPageChangeListener(new OnPageChangeshare());
		vpMyOrder.setCurrentItem(currIndex);

		rdoBtnAll.setOnClickListener(new OnClickListener(0));
		rdoBtnDelivery.setOnClickListener(new OnClickListener(1));
		rdoBtnReceipt.setOnClickListener(new OnClickListener(2));
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

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				rdoBtnAll.setChecked(true);
				break;
			case 1:
				rdoBtnDelivery.setChecked(true);
				break;
			case 2:
				rdoBtnReceipt.setChecked(true);
				break;
			}
			currIndex = arg0;
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
