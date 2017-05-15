package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.adapter.BidRecordAdapter;
import com.yfzx.lwpai.adapter.GoodsLuckRedOneDetailAdapter;
import com.yfzx.lwpai.adapter.Top3000RecordAdapter;
import com.yfzx.lwpai.adapter.WinRecordAdapter;
import com.yfzx.lwpai.entity.AdInfoEntity;
import com.yfzx.lwpai.entity.DetailsPriceResult;
import com.yfzx.lwpai.entity.DetailsPriceWinEntity;
import com.yfzx.lwpai.entity.DidResultEntity;
import com.yfzx.lwpai.entity.LuckyGetLuckyOfferEntity;
import com.yfzx.lwpai.entity.LuckyProductByIdEntity;
import com.yfzx.lwpai.entity.LuckyProductByIdResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;
import com.yfzx.lwpai.view.SlideShowView;

/**
 * 红包区商品详情界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年8月9日
 */
@ContentView(R.layout.activity_auction_begins)
public class GoodsRedDetailActivity extends BaseActivity {
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 按钮
	@ViewInject(R.id.btn_Bidders)
	private Button btn_Bidders;
	// 显示布局一
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;
	// 头部列表
	private ListView lvHead;
	// 轮播
	private SlideShowView slideShowView;

	// 悬浮部分内容
	@ViewInject(R.id.llytInvis)
	private LinearLayout llytInvis;
	// 中拍记录列表
	@ViewInject(R.id.listView)
	private XListView listView;

	// 幸运拍
	@ViewInject(R.id.tvLuckAuction)
	private TextView tvLuckAuction;
	// 红包区
	@ViewInject(R.id.tvRedPacket)
	private TextView tvRedPacket;
	// 一元拍
	@ViewInject(R.id.tvOneYuan)
	private TextView tvOneYuan;
	// 3个标签的下划线
	@ViewInject(R.id.ivLineLuck)
	private ImageView ivLineLuck;
	@ViewInject(R.id.ivLineRed)
	private ImageView ivLineRed;
	// 一元拍的下划线
	@ViewInject(R.id.ivLineOneYuan)
	private ImageView ivLineOneYuan;

	private TextView tvLuckAuctionH;// 出价记录
	private TextView tvRedPacketH;// 中拍记录
	private TextView tvOneYuanH;// top3000
	private ImageView ivLineLuckH;
	private ImageView ivLineRedH;
	private ImageView ivLineOneYuanH;

	private LinearLayout llGoodsShare;
	private LinearLayout llBygoneDays;
	private LinearLayout llProductDescription;

	// 头部内容
	private View invisView;
	// 隐藏内容
	private View headerView;

	private int currPage = 1;
	protected boolean isSaveCache = true;
	// 传值
	private String goodsflag;
	private String goodsId;
	private double percentage;
	private int lucky;// 显示top3000
	private int flag = 0;// 传结束
	protected boolean isSave = true;
	// 详细页面适配器
	private GoodsLuckRedOneDetailAdapter adapter;
	// 出价
	private BidRecordAdapter adapterbid;
	// 中拍
	private WinRecordAdapter adapterwin;
	// top3000
	private Top3000RecordAdapter adaptertop;
	// 55幸运拍56红包区3一元拍
	private int type = 56;
	private LinearLayout llOneH;
	private String productId;

	// 幸运拍56红包区3一元拍

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// 0进行 1即将开始 2结束
				flag = bundle.getInt("category");
				// 商品ID
				goodsId = bundle.getString("goodsId");
			}
		}

		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		// 标题
		tvCenter.setText("拍品详情");
		ivLeft.setImageResource(R.drawable.top_back_round);
		tvLuckAuction.setText("出价记录");
		tvRedPacket.setText("中拍记录");

		// 头部内容
		headerView = View.inflate(act, R.layout.activity_auction_begin_head,
				null);
		// 隐藏内容
		invisView = View.inflate(act, R.layout.fragment_home_action, null);
		// 添加头部内容
		listView.addHeaderView(headerView);
		// 添加隐藏内容
		listView.addHeaderView(invisView);
		// 添加底部内容
		slideShowView = (SlideShowView) headerView
				.findViewById(R.id.slideShowView);

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

		llOneH = (LinearLayout) invisView.findViewById(R.id.llOne);
		llGoodsShare = (LinearLayout) headerView
				.findViewById(R.id.llGoodsShare);
		llBygoneDays = (LinearLayout) headerView
				.findViewById(R.id.llBygoneDays);
		llProductDescription = (LinearLayout) headerView
				.findViewById(R.id.llProductDescription);
		// 出价记录
		tvLuckAuctionH.setText("出价记录");
		tvRedPacketH.setText("中拍记录");
		llOneH.setVisibility(View.VISIBLE);
		tvOneYuanH.setVisibility(View.GONE);
		ivLineOneYuanH.setVisibility(View.GONE);
		llOne.setVisibility(View.VISIBLE);
		tvOneYuan.setVisibility(View.GONE);
		ivLineOneYuan.setVisibility(View.GONE);
		// 晒单分享
		llGoodsShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, NewestSingleShareActivity.class);
				// 商品ID
				bundle.putInt("goodsProductId", Integer.parseInt(productId));
				// 类型
				bundle.putInt("type", type);
				intent.putExtras(bundle);
				startActivity(intent);

			}

		});
		// 往期揭晓
		llBygoneDays.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act,
						NewestLaterAnnounceActivity.class);
				// 商品ID
				bundle.putInt("goodsProductId", Integer.parseInt(productId));
				// 类型
				bundle.putInt("type", type);
				intent.putExtras(bundle);
				startActivity(intent);

			}

		});
		// 商品描述
		llProductDescription.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, GoodsDescriptionActivity.class);
				// 商品ID
				bundle.putString("goodsId", goodsId);
				// 类型
				bundle.putInt("type", type);
				intent.putExtras(bundle);
				startActivity(intent);

			}

		});

		// 幸运拍
		tvLuckAuctionH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				selectDid();
			}

		});
		// 红包区
		tvRedPacketH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				selectWin();
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
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
		// 获得详细商品数据
		getLuck();
		// 出价记录
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSaveCache = true;
				PriceRecord();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				PriceRecord();
			}
		});
		// 初始化数据
		adapterbid = new BidRecordAdapter(act,
				new ArrayList<DidResultEntity>(), type);
		listView.setAdapter(adapterbid);

		adapterbid.addAll(CacheManage.get("luck_list" + flag,
				DidResultEntity.class));
		adapterbid.notifyDataSetChanged();

		// 出价记录
		PriceRecord();
	}

	/**
	 * 获取幸运拍商品详情适配器
	 * 
	 * @author: yizhong.xu
	 */
	private void getLuck() {
		adapter = new GoodsLuckRedOneDetailAdapter(act,
				new ArrayList<LuckyProductByIdResult>(), type, flag);
		lvHead = (ListView) headerView.findViewById(R.id.lvHead);
		lvHead.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		getLuckData();
	}

	/**
	 * 获得详细页面数据
	 * 
	 * @author: songbing.zhou
	 * @param type
	 */
	private void getLuckData() {
		// 获取详细页面数据
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/TryArea/GetTryAreaProductById");
		httpClient.url.append("/" + Integer.parseInt(goodsId));
		httpClient.post(new xResopnse() {

			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyProductByIdEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyProductByIdEntity.class);
				if (response != null) {
					List<LuckyProductByIdResult> tmpList = response.getResult();
					List<AdInfoEntity> AdInfoList = response.getPImgSrc();
					// 产品ID
					productId = tmpList.get(0).getProductId() + "";
					// 轮播
					slideShowView.init(AdInfoList);
					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

					switch (tmpList.get(0).getIsFinish()) {
					case "0": {// 进行中
						btn_Bidders.setText("参与竞拍");
						break;
					}
					case "1": {// 开始
						btn_Bidders.setText("参与竞拍");
						break;
					}
					case "2": {// 结束
						btn_Bidders.setText("竞拍结束");
						btn_Bidders.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.shape3));
						break;
					}
					}
				}

			}
		});
	}

	/**
	 * 出价记录
	 * 
	 * @author: yizhong.xu
	 */
	private void PriceRecord() {

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/TryArea/GetTryAreaOffer");// 方法
		httpClient.url.append("/" + goodsId);// 每页条数
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xAllResopnse() {
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyGetLuckyOfferEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyGetLuckyOfferEntity.class);
				if (response != null) {

					List<DidResultEntity> tmpList = response.getResult();
					if (isSaveCache) {
						CacheManage.remove("luck_list" + flag);
						CacheManage.put("luck_list" + flag, tmpList);
						adapterbid.getList().clear();
						isSaveCache = false;
					}
					adapterbid.addAll(tmpList);
					adapterbid.notifyDataSetChanged();

				}
				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onStart() {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 中拍记录
	 * 
	 * @author: yizhong.xu
	 */
	private void priceWin() {

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/tryarea/GetTryAreaWinnerById");// 方法
		httpClient.url.append("/" + goodsId);// ID
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				DetailsPriceWinEntity response = JsonUtil.parseObject(act,
						responseInfo.result, DetailsPriceWinEntity.class);
				if (response != null) {

					List<DetailsPriceResult> tmpList = response.getResult();
					if (isSaveCache) {
						CacheManage.remove("red_list" + flag);
						CacheManage.put("red_list" + flag, tmpList);
						adapterwin.getList().clear();
						isSaveCache = false;
					}
					adapterwin.addAll(tmpList);

					adapterwin.notifyDataSetChanged();
					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				}
			}
		});

	}

	/**
	 * 选中出价记录
	 * 
	 * @author: yizhong.xu
	 */
	private void selectDid() {
		// 重置
		reset();
		// 选中当前点击的Item
		ivLineLuck.setVisibility(View.VISIBLE);
		ivLineLuckH.setVisibility(View.VISIBLE);
		tvLuckAuction.setTextColor(getResources().getColor(
				R.color.include_title));
		tvLuckAuctionH.setTextColor(getResources().getColor(
				R.color.include_title));
		// 下拉加载更多
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSaveCache = true;
				PriceRecord();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				PriceRecord();
			}
		});
		// 初始化数据
		adapterbid = new BidRecordAdapter(act,
				new ArrayList<DidResultEntity>(), type);
		listView.setAdapter(adapterbid);
		adapterbid.addAll(CacheManage.get("luck_list" + flag,
				DidResultEntity.class));
		adapterbid.notifyDataSetChanged();
		listView.setSelection(2);
		PriceRecord();

	}

	/**
	 * 中拍
	 * 
	 * @author: yizhong.xu
	 */
	private void selectWin() {
		// 重置
		reset();
		// 选中当前点击的Item
		tvRedPacket
				.setTextColor(getResources().getColor(R.color.include_title));
		tvRedPacketH.setTextColor(getResources()
				.getColor(R.color.include_title));
		ivLineRed.setVisibility(View.VISIBLE);
		ivLineRedH.setVisibility(View.VISIBLE);

		// 下拉加载更多
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSaveCache = true;
				priceWin();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				priceWin();
			}
		});
		// 加载数据

		adapterwin = new WinRecordAdapter(act,
				new ArrayList<DetailsPriceResult>(), 1);
		listView.setAdapter(adapterwin);
		adapterwin.addAll(CacheManage.get("red_list" + flag,
				DetailsPriceResult.class));
		adapterwin.notifyDataSetChanged();
		listView.setSelection(2);
		priceWin();

	}

	/**
	 * 重置
	 * 
	 * @author: bangwei.yang
	 */
	private void reset() {
		currPage = 1;
		isSaveCache = true;
		// 幸运拍 字体
		tvLuckAuction.setTextColor(getResources().getColor(
				android.R.color.black));
		tvLuckAuctionH.setTextColor(getResources().getColor(
				android.R.color.black));
		ivLineLuck.setVisibility(View.INVISIBLE);
		ivLineLuckH.setVisibility(View.INVISIBLE);
		ivLineRed.setVisibility(View.INVISIBLE);
		ivLineRedH.setVisibility(View.INVISIBLE);
		// 红包区 字体
		tvRedPacket
				.setTextColor(getResources().getColor(android.R.color.black));
		tvRedPacketH.setTextColor(getResources()
				.getColor(android.R.color.black));
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvLuckAuction, R.id.tvRedPacket,
			R.id.llProductDescription, R.id.llGoodsShare, R.id.llBygoneDays,
			R.id.btn_Bidders })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft: {// 返回
			finish();
			break;
		}
		case R.id.tvLuckAuction: {// 跳转出价记录
			selectDid();
			break;
		}
		case R.id.tvRedPacket: {// 跳转中拍记录
			selectWin();
			break;
		}
		case R.id.btn_Bidders: {// 参与竞拍
			if (!UserManage.isLogin()) {
				showActivity(act, LoginActivity.class);
				return;
			}
			if (!btn_Bidders.getText().toString().equals("竞拍结束")) {
				Bundle bundle = new Bundle();
				bundle.putInt("type", 1);// 1幸运拍，2红包区，3一元拍
				bundle.putString("goodsId", goodsId);// 1幸运拍，2红包区，3一元拍
				showActivity(act, PartakeActivity.class, bundle);

			} else {
				ToolToast.showShort("该商品竞拍已经结束了");
			}
			break;
		}
		}
	}
}
