package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import com.yfzx.lwpai.fragment.AccountIntegralFragment;
import com.yfzx.lwpai.util.ScreenHelper;

/**
 * 我的积分
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-20
 */
@ContentView(R.layout.activity_account_balance)
public class AccountIntegralActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 标题
	@ViewInject(R.id.tvRight)
	private TextView tvRight;

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

	@ViewInject(R.id.ivBottomShare)
	private ImageView ivBottomLine;
	// 余额
	@ViewInject(R.id.tvBalance)
	private TextView tvBalance;
	// 余额
	@ViewInject(R.id.tvTotal)
	private TextView tvTotal;
	// 余额
	@ViewInject(R.id.tvTotal1)
	private TextView tvTotal1;
	// 可提现
	@ViewInject(R.id.tvRemind)
	private TextView tvRemind;
	// 可提现
	@ViewInject(R.id.tvRemindTitle)
	private TextView tvRemindTitle;
	// 可提现
	@ViewInject(R.id.tvRemindTitle1)
	private TextView tvRemindTitle1;

	private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>(); // fragment数组
	private FragmentManager fragmentManager;
	private int currIndex = 0;
	private int offset = 0;
	private int position_one, position_two;

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
		tvCenter.setText("我的积分");
		tvRight.setText("积分兑换");
		tvRight.setTextColor(getResources().getColor(R.color.darkblue));
		tvRemindTitle.setVisibility(View.GONE);
		tvRemindTitle1.setVisibility(View.GONE);
		tvTotal.setText("总积分：");
		tvTotal1.setVisibility(View.GONE);
		tvBalance.setVisibility(View.GONE);
		vpMyOrder.setOffscreenPageLimit(3);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			tvRemind.setText(bundle.getString("integral"));
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	@SuppressWarnings("deprecation")
	private void initData() {
		// 添加切换的页面 ,全部0，收入1，支出2
		fragmentsList.add(AccountIntegralFragment.newInstance(0));
		fragmentsList.add(AccountIntegralFragment.newInstance(1));
		fragmentsList.add(AccountIntegralFragment.newInstance(2));

		// 计算动画切换效果
		int screenW = ScreenHelper.getScreenWidthPix(act);
		offset = (int) ((screenW / fragmentsList.size() - ivBottomLine
				.getLayoutParams().width) / 2);
		int avg = (int) (screenW / fragmentsList.size());
		position_one = avg + offset;
		position_two = 2 * avg + offset;

		// 设置动画效果
		fragmentManager = getSupportFragmentManager();
		TranslateAnimation animation = new TranslateAnimation(0, offset, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(300);
		ivBottomLine.startAnimation(animation);

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
		Animation animation = null;

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one, offset, 0,
							0);
					rdoBtnDelivery.setTextColor(getResources().getColor(
							R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two, offset, 0,
							0);
					rdoBtnReceipt.setTextColor(getResources().getColor(
							R.color.black));
				}
				rdoBtnAll.setTextColor(getResources().getColor(
						R.color.btn_orange));
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_one, 0,
							0);
					rdoBtnAll.setTextColor(getResources().getColor(
							R.color.black));
				}
				if (currIndex == 2) {
					animation = new TranslateAnimation(position_two,
							position_one, 0, 0);
					rdoBtnReceipt.setTextColor(getResources().getColor(
							R.color.black));
				}
				rdoBtnDelivery.setTextColor(getResources().getColor(
						R.color.btn_orange));
				break;
			case 2:

				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, position_two, 0,
							0);
					rdoBtnAll.setTextColor(getResources().getColor(
							R.color.black));
				}
				if (currIndex == 1) {
					animation = new TranslateAnimation(position_one,
							position_two, 0, 0);
					rdoBtnDelivery.setTextColor(getResources().getColor(
							R.color.black));
				}
				rdoBtnReceipt.setTextColor(getResources().getColor(
						R.color.btn_orange));
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
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.tvRight:// 积分兑换
			Bundle bundle = new Bundle();
			bundle.putString("Total", tvRemind.getText().toString());
			showActivity(act, IntegralExchangeActivity.class, bundle);
			break;
		default:
			break;
		}
	}

}
