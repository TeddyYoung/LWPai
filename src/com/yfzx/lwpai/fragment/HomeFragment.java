package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.plugins.zxing.MipcaActivityCapture;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.activity.AccountSecurityActivity;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.activity.HomeAfficheActivity;
import com.yfzx.lwpai.activity.HomeShareActivity;
import com.yfzx.lwpai.activity.LoginActivity;
import com.yfzx.lwpai.activity.MainActivity;
import com.yfzx.lwpai.activity.RechargeActivity;
import com.yfzx.lwpai.activity.RegistActivity;
import com.yfzx.lwpai.adapter.HomeLuckAdapter;
import com.yfzx.lwpai.entity.AdInfoEntity;
import com.yfzx.lwpai.entity.AfficheEntity;
import com.yfzx.lwpai.entity.AfficheEntity.ResultEntity;
import com.yfzx.lwpai.entity.DoubleBall;
import com.yfzx.lwpai.entity.HeadAdList;
import com.yfzx.lwpai.entity.HomeLuck;
import com.yfzx.lwpai.entity.HomeLuckResult;
import com.yfzx.lwpai.util.CountDown;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.AutoTextView;
import com.yfzx.lwpai.view.ProgressHelper;
import com.yfzx.lwpai.view.SlideShowView;

/**
 * 首页
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.2
 * @Date: 2015-7-17
 */
public class HomeFragment extends BaseFragment {

	// 悬浮部分内容
	@ViewInject(R.id.llytInvis)
	private LinearLayout llytInvis;
	// listview
	@ViewInject(R.id.listView)
	private XListView listView;
	// 头部左边图片
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 头部中间图片
	@ViewInject(R.id.ivCenter)
	private ImageView ivCenter;
	// 头部右边图片
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// 幸运拍
	@ViewInject(R.id.tvLuckAuction)
	private TextView tvLuckAuction;
	// 红包区
	@ViewInject(R.id.tvRedPacket)
	private TextView tvRedPacket;
	// 一元拍
	@ViewInject(R.id.tvOneYuan)
	private TextView tvOneYuan;
	// 下划线
	@ViewInject(R.id.ivLineLuck)
	private ImageView ivLineLuck;
	// 下划线
	@ViewInject(R.id.ivLineRed)
	private ImageView ivLineRed;
	// 下划线
	@ViewInject(R.id.ivLineOneYuan)
	private ImageView ivLineOneYuan;

	// 头部内容
	private View invisView;
	// 隐藏内容
	private View headerView;
	// 适配器
	private int currPage = 1;
	// 资源
	private HomeLuckAdapter adapter;
	private boolean isSaveCache = true;
	private List<ResultEntity> afficheData;
	private int afficheIndex = 0;

	// 图片容器
	private SlideShowView slideShowView;
	private int homeflag = 55;
	private TextView tvLuckAuctionH;
	private TextView tvRedPacketH;
	private TextView tvOneYuanH;
	private Fragment fragment = new Fragment();
	private ImageView ivLineLuckH;
	private ImageView ivLineRedH;
	private ImageView ivLineOneYuanH;
	private LinearLayout llytAnnounced;
	private ImageView ivIndexAd2;
	private ImageView ivIndexAd3;
	private ImageView ivIndexAd4;
	private TextView tvHour;
	private TextView tvMinute;
	private TextView tvSecond;
	private AutoTextView atvMessage;
	protected List<AdInfoEntity> headAdData;
	/**
	 * 下拉可以才可以刷新
	 */
	private boolean isAffiche = true;
	
	/**
	 * 让滚动广告就执行一次
	 */
	private int afficheNum=0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ViewUtils.inject(this, view);
		initWidget();
		initData(homeflag);
		return view;
	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// handler自带方法实现定时器
			try {
				handler.postDelayed(this, 4000);
				atvMessage.next();
				afficheIndex++;
				atvMessage.setText(afficheData.get(
						afficheIndex % afficheData.size()).getTitle());

				atvMessage.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(
								"afficheId",
								afficheData.get(
										afficheIndex % afficheData.size())
										.getAfficheId());
						showActivity(act, HomeAfficheActivity.class, bundle);
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception...");
			}
		}
	};

	public Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				slideShowView.setFlag("1");
				slideShowView.inits(headAdData);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	public void initWidget() {
		// 获得产品列表数据
		getProduct(homeflag);
		ivLeft.setImageResource(R.drawable.top_sweep);// 左边
		ivCenter.setImageResource(R.drawable.top_logo);// 中间
		ivRight.setImageResource(R.drawable.top_login);// 右边

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem >= 2) {
					llytInvis.setVisibility(View.VISIBLE);
				} else {
					llytInvis.setVisibility(View.INVISIBLE);
				}
			}
		});
		listView.setPullRefreshEnable(true);
		listView.setPullLoadEnable(false);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSaveCache = true;
				GetTimeHelper.getCurrentTime(act);
				afficheNum++;
				isAffiche = true;
				initData(homeflag);
				// getProduct(homeflag);
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getProduct(homeflag);
			}
		});

		// 头部内容
		headerView = View.inflate(act, R.layout.fragment_home_head, null);
		// 隐藏内容
		invisView = View.inflate(act, R.layout.fragment_home_action, null);
		// 添加头部内容
		listView.addHeaderView(headerView);
		// 添加隐藏内容
		listView.addHeaderView(invisView);
		slideShowView = (SlideShowView) headerView
				.findViewById(R.id.slideShowView);
		// 滚动广告
		atvMessage = (AutoTextView) headerView.findViewById(R.id.atvMessage);
		// 广告最新揭晓
		llytAnnounced = (LinearLayout) headerView
				.findViewById(R.id.llytAnnounced);
		// 广告2
		ivIndexAd2 = (ImageView) headerView.findViewById(R.id.ivIndexAd2);
		// 广告3
		ivIndexAd3 = (ImageView) headerView.findViewById(R.id.ivIndexAd3);
		// 广告4
		ivIndexAd4 = (ImageView) headerView.findViewById(R.id.ivIndexAd4);
		// 小时
		tvHour = (TextView) headerView.findViewById(R.id.tvHour);
		// 分钟
		tvMinute = (TextView) headerView.findViewById(R.id.tvMinute);
		// 秒
		tvSecond = (TextView) headerView.findViewById(R.id.tvSecond);

		// 头部幸运拍
		RadioButton btnluck = (RadioButton) headerView
				.findViewById(R.id.rdoBtnlucky);// 获取他的ID
		btnluck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).clickGood(1);
			}
		});
		// 头部红包区
		RadioButton rdoBtnRed = (RadioButton) headerView
				.findViewById(R.id.rdoBtnRed);// 获取他的ID
		rdoBtnRed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).clickGood(2);
			}
		});
		// 头部一元拍
		RadioButton rdoOneYuan = (RadioButton) headerView
				.findViewById(R.id.rdoOneYuan);// 获取他的ID
		rdoOneYuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				((MainActivity) getActivity()).clickGood(3);
			}
		});

		// 晒单
		RadioButton btnSunaLone = (RadioButton) headerView
				.findViewById(R.id.rdoBtnSunaLone);// 获取他的ID
		btnSunaLone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showActivity(act, HomeShareActivity.class);
			}
		});
		// 隐藏幸运文字
		tvLuckAuctionH = (TextView) invisView.findViewById(R.id.tvLuckAuction);
		// 隐藏幸运下划线
		ivLineLuckH = (ImageView) invisView.findViewById(R.id.ivLineLuck);
		// 隐藏红包文字
		tvRedPacketH = (TextView) invisView.findViewById(R.id.tvRedPacket);
		// 隐藏红包下划线
		ivLineRedH = (ImageView) invisView.findViewById(R.id.ivLineRed);
		// 隐藏一元文字
		tvOneYuanH = (TextView) invisView.findViewById(R.id.tvOneYuan);
		// 隐藏一元下划线
		ivLineOneYuanH = (ImageView) invisView.findViewById(R.id.ivLineOneYuan);
		// 隐藏幸运拍
		tvLuckAuctionH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				luckAuction();
			}
		});
		// 隐藏红包区
		tvRedPacketH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				RedPacket();
			}
		});
		// 隐藏一元拍
		tvOneYuanH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				OneYuan();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData(int homeflag) {
		// 设置适配器
		if (adapter == null) {
			adapter = new HomeLuckAdapter(act, new ArrayList<HomeLuckResult>(),
					homeflag);
			listView.setAdapter(adapter);
		} else {
			adapter.setHomeFlag(homeflag);
		}
		adapter.setList(CacheManage.get("homeLuck_list" + homeflag,
				HomeLuckResult.class));
		this.homeflag = homeflag;
		if (isAffiche) {
			if (afficheNum<=1) {
				// 滚动广告
				getAffiche();
			}
			// 头部广告
			HeadAd(tvHour, tvMinute, tvSecond);
			// 本期幸运码
			LuckyCode();
			isAffiche = false;
		}
		// 获得产品列表数据
		getProduct(homeflag);
	}

	/**
	 * 选中幸运拍
	 * 
	 * @author: songbing.zhou
	 */
	private void luckAuction() {
		// 重置
		reset();
		// 选中当前点击的Item
		ivLineLuck.setVisibility(View.VISIBLE);
		ivLineLuckH.setVisibility(View.VISIBLE);
		tvLuckAuction.setTextColor(getResources().getColor(
				R.color.include_title));
		tvLuckAuctionH.setTextColor(getResources().getColor(
				R.color.include_title));
		// 设置适配器
		initData(55);
	}

	/**
	 * 
	 * 选中红包区
	 * 
	 * @author: songbing.zhou
	 */
	private void RedPacket() {
		// 重置
		reset();
		// 选中当前点击的Item
		ivLineRed.setVisibility(View.VISIBLE);
		ivLineRedH.setVisibility(View.VISIBLE);
		tvRedPacket
				.setTextColor(getResources().getColor(R.color.include_title));
		tvRedPacketH.setTextColor(getResources()
				.getColor(R.color.include_title));
		// 设置适配器
		initData(56);
	}

	/**
	 * 选中一元拍
	 * 
	 * @author: bangwei.yang
	 */
	private void OneYuan() {
		// 重置
		reset();
		// 选中当前点击的Item
		ivLineOneYuan.setVisibility(View.VISIBLE);
		ivLineOneYuanH.setVisibility(View.VISIBLE);
		tvOneYuan.setTextColor(getResources().getColor(R.color.include_title));
		tvOneYuanH.setTextColor(getResources().getColor(R.color.include_title));
		// 设置适配器
		initData(3);
	}

	/**
	 * 获得列表数据
	 * 
	 * @author: songbing.zhou
	 */
	private void getProduct(final int flag) {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/product/GetProductList");//
		httpClient.url.append("/" + flag);// 3是一元拍，55是幸运拍，56是红包区
		httpClient.url.append("/" + CommonGlobal.PAGESIZE * currPage);// 每页条数
		httpClient.post(new xAllResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				HomeLuck response = JsonUtil.parseObject(act,
						responseInfo.result, HomeLuck.class);
				if (response != null) {
					List<HomeLuckResult> tmpList = response.getResult();
					if (isSaveCache) {
						CacheManage.remove("homeLuck_list" + flag);
						CacheManage.put("homeLuck_list" + flag, tmpList);
						isSaveCache = false;
					}
					adapter.setList(tmpList);

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				}

				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onStart() {
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				listView.stopLoadMore();
				listView.stopRefresh();
			}
		});

	}

	/**
	 * 滚动文字
	 * 
	 * @author: songbing.zhou
	 */
	private void getAffiche() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Affiche/GetAfficheList");
		httpClient.url.append("/" + 3);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				AfficheEntity response = JsonUtil.parseObject(act,
						responseInfo.result, AfficheEntity.class);
				if (response != null) {
					afficheData = response.getResult();
					if (afficheData.size() != 1) {
						handler.postDelayed(runnable, 6000);
					}
					atvMessage.setText(afficheData.get(0).getTitle());
					atvMessage.setTitleId(afficheData.get(0).getAfficheId());
				}
			}

		});

	}

	/**
	 * 头部广告
	 * 
	 * @author: songbing.zhou
	 */
	public void HeadAd(final TextView tvHour, final TextView tvMinute,
			final TextView tvSecond) {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/ad/GetIndexAdList");
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				HeadAdList response = JsonUtil.parseObject(act,
						responseInfo.result, HeadAdList.class);
				if (response != null) {
					headAdData = response.getResult().getAdInfo();
					for (final AdInfoEntity data : headAdData) {
						if (data.getCategoryId().equals("2")) {// 广告位，即将揭晓
							ImageLoaderUtil.getByUrl(data.getImgsrc(),
									ivIndexAd2);
							// 广告最新揭晓
							llytAnnounced
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											onClickSkip(data);
										}
									});
						}
						if (data.getCategoryId().equals("3")) {// 广告位，爱逛街
							ImageLoaderUtil.getByUrl(data.getImgsrc(),
									ivIndexAd3);
							ivIndexAd3
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											// onClickSkip(data);
											((MainActivity) getActivity())
													.clickGood(1);
										}
									});
						}
						if (data.getCategoryId().equals("4")) {// 广告位，每日首发
							ImageLoaderUtil.getByUrl(data.getImgsrc(),
									ivIndexAd4);
							ivIndexAd4
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											onClickSkip(data);
										}
									});
						}
					}
					// 倒计时
					Date EndDate = ToolDateTime.parseDate(headAdData.get(0)
							.getEndDate());
					long millisInFuture = EndDate.getTime();
					// 获得服务器时间
					Date timeTemp = GetTimeHelper.getTime();
					long timeNow = timeTemp.getTime();
					CountDown countDown = new CountDown(millisInFuture
							- timeNow, tvHour, tvMinute, tvSecond);// 默认一天
					countDown.start();
					// 启动轮播线程
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							Message message = new Message();
							message.what = 1;
							mHandler.sendMessage(message);
						}
					});
					thread.start();
				}
			}
		});
	}

	/**
	 * 广告点击事件
	 * 
	 * @author: bangwei.yang
	 * @param data
	 */
	private void onClickSkip(AdInfoEntity data) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		switch (data.getTarget()) {
		case "1":// 1幸运拍商品跳转id为该商品Id
			intent.setClass(act, GoodsLuckDetailActivity.class);
			bundle.putInt("category", 0);// 0进行 1即将开始 2结束
			bundle.putInt("lucky", 1); // 显示TOP3000
			bundle.putString("goodsId", data.getId());// 商品ID
			bundle.putInt("type", 55);// 0一元拍，2红包区，2幸运拍
			break;
		case "2":// 2为红包区商品跳转id为该商品Id
			intent.setClass(act, GoodsLuckDetailActivity.class);
			bundle.putInt("category", 0);// 0进行 1即将开始 2结束
			bundle.putInt("lucky", 1); // 显示TOP3000
			bundle.putString("goodsId", data.getId());// 商品ID
			bundle.putInt("type", 56);// 0一元拍，2红包区，2幸运拍
			break;
		case "3":// 3为一元拍商品跳转id为该商品Id
			intent.setClass(act, GoodsLuckDetailActivity.class);
			bundle.putInt("category", 0);// 0进行 1即将开始 2结束
			bundle.putInt("lucky", 1); // 显示TOP3000
			bundle.putString("goodsId", data.getId());
			// 红包区参与竞拍ID
			bundle.putInt("type", 3);// 0一元拍，2红包区，2幸运拍
			break;
		case "4":// 4为领取红包活动页面(暂无)

			return;
		case "5":// 5为幸运拍列表页
			((MainActivity) getActivity()).clickGood(1);
			break;
		case "6":// 6为红包区列表页
			((MainActivity) getActivity()).clickGood(2);
			break;
		case "7":// 7为一元拍列表页
			((MainActivity) getActivity()).clickGood(3);
			break;
		case "8":// 8为晒单列表页
			intent.setClass(act, HomeShareActivity.class);
			break;
		case "9":// 9为开启交易密码页面
			if (!UserManage.isLogin()) {
				intent.setClass(act, LoginActivity.class);
				act.startActivity(intent);
				return;
			}
			intent.setClass(act, AccountSecurityActivity.class);
			break;
		case "10":// 10充值页面
			if (!UserManage.isLogin()) {
				intent.setClass(act, LoginActivity.class);
				act.startActivity(intent);
				return;
			}
			intent.setClass(act, RechargeActivity.class);
			break;
		default:
			return;
		}
		intent.putExtras(bundle);
		act.startActivity(intent);
	}

	/**
	 * 本期幸运码
	 * 
	 * @author: songbing.zhou
	 */
	public void LuckyCode() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/lucky/ssqlist");
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				DoubleBall response = JsonUtil.parseObject(act,
						responseInfo.result, DoubleBall.class);
				if (response != null) {
					// 开拍时间
					TextView tvStartTime = (TextView) headerView
							.findViewById(R.id.tvStartTime);
					// 幸运号
					TextView tvNumOne = (TextView) headerView
							.findViewById(R.id.tvNumOne);
					TextView tvNumTwo = (TextView) headerView
							.findViewById(R.id.tvNumTwo);
					TextView tvNumThree = (TextView) headerView
							.findViewById(R.id.tvNumThree);
					TextView tvNumFour = (TextView) headerView
							.findViewById(R.id.tvNumFour);
					TextView tvNumFive = (TextView) headerView
							.findViewById(R.id.tvNumFive);
					TextView tvNumSix = (TextView) headerView
							.findViewById(R.id.tvNumSix);
					TextView tvNumSeven = (TextView) headerView
							.findViewById(R.id.tvNumSeven);

					String code = response.getResult().getSSQ();// 幸运号
					String lycode[] = code.split("-", 7);
					tvNumOne.setText(lycode[0]);
					tvNumTwo.setText(lycode[1]);
					tvNumThree.setText(lycode[2]);
					tvNumFour.setText(lycode[3]);
					tvNumFive.setText(lycode[4]);
					tvNumSix.setText(lycode[5]);
					tvNumSeven.setText(lycode[6]);
					tvStartTime.setText(response.getResult().getOpenDate());// 开拍时间
				}
			}
		});

	}

	/**
	 * 重置
	 * 
	 * @author: bangwei.yang
	 */
	private void reset() {
		currPage = 1;
		isSaveCache = true;
		// 一元拍 下划线 字体
		ivLineOneYuan.setVisibility(View.INVISIBLE);
		ivLineOneYuanH.setVisibility(View.INVISIBLE);
		tvOneYuan.setTextColor(getResources().getColor(android.R.color.black));
		tvOneYuanH.setTextColor(getResources().getColor(android.R.color.black));
		// 幸运拍 下划线 字体
		ivLineLuck.setVisibility(View.INVISIBLE);
		ivLineLuckH.setVisibility(View.INVISIBLE);
		tvLuckAuction.setTextColor(getResources().getColor(
				android.R.color.black));
		tvLuckAuctionH.setTextColor(getResources().getColor(
				android.R.color.black));
		// 红包区 下划线 字体
		ivLineRed.setVisibility(View.INVISIBLE);
		ivLineRedH.setVisibility(View.INVISIBLE);
		tvRedPacket
				.setTextColor(getResources().getColor(android.R.color.black));
		tvRedPacketH.setTextColor(getResources()
				.getColor(android.R.color.black));

	}

	/**
	 * 更改显示的页面
	 * 
	 * @author: bangwei.yang
	 * @param from
	 * @param to
	 * @param tag
	 */
	public void switchContent(Fragment from, Fragment to, String tag) {
		if (fragment != to) {
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			try {
				// 根据Tag查找是否添加
				Fragment tmpFragment = getFragmentManager().findFragmentByTag(
						tag);
				if (tmpFragment != null) { // 先判断是否被add过，如果不为空为true
					to = tmpFragment;// 替换成已添加的界面
					transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
				} else {
					transaction.hide(from).add(R.id.flytContent, to, tag)
							.commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
				}
				// 修改当前选择页面
				fragment = to;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	private int selection = 0;

	@OnClick({ R.id.tvLuckAuction, R.id.tvRedPacket, R.id.tvOneYuan,
			R.id.llytLeft, R.id.llytRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvLuckAuction:// 幸运拍
			selection = listView.getFirstVisiblePosition();
			luckAuction();
			break;
		case R.id.tvRedPacket:// 红包区
			selection = listView.getFirstVisiblePosition();
			RedPacket();
			break;
		case R.id.tvOneYuan:// 一元拍
			selection = listView.getSelectedItemPosition();
			OneYuan();
			break;
		case R.id.llytRight:// 登录
			if (UserManage.isLogin()) {// 判断是否登录
				((MainActivity) getActivity()).clickMenu(4);// 跳转到对应的页面
			} else {
				showActivity(act, RegistActivity.class);
			}
			break;
		case R.id.llytLeft:// 扫一扫
			showActivity(act, MipcaActivityCapture.class);
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}
