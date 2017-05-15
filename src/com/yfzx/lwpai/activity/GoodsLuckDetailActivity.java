package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.adapter.GoodsLuckRedOneDetailAdapter;
import com.yfzx.lwpai.adapter.RecordAdapter;
import com.yfzx.lwpai.adapter.RecordAdapter.RecordItem;
import com.yfzx.lwpai.entity.AdInfoEntity;
import com.yfzx.lwpai.entity.DetailsPriceResult;
import com.yfzx.lwpai.entity.DetailsPriceWinEntity;
import com.yfzx.lwpai.entity.DetailsTop3000Entity;
import com.yfzx.lwpai.entity.DetailsTop3000ResultEntity;
import com.yfzx.lwpai.entity.DidResultEntity;
import com.yfzx.lwpai.entity.LuckyGetLuckyOfferEntity;
import com.yfzx.lwpai.entity.LuckyProductByIdEntity;
import com.yfzx.lwpai.entity.LuckyProductByIdResult;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;
import com.yfzx.lwpai.view.SlideShowView;

/**
 * 幸运拍商品详情界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年8月9日
 */
@ContentView(R.layout.activity_auction_begins)
public class GoodsLuckDetailActivity extends BaseActivity {
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;

	// 按钮
	@ViewInject(R.id.btn_Bidders)
	private Button btn_Bidders;
	// 悬浮部分内容
	@ViewInject(R.id.llytInvis)
	private LinearLayout llytInvis;
	// 中拍记录列表
	@ViewInject(R.id.listView)
	private XListView listView;
	// 布局中默认的出价记录,中拍记录,top3000
	@ViewInject(R.id.tvLuckAuction)
	private TextView tvLuckAuction;
	@ViewInject(R.id.tvRedPacket)
	private TextView tvRedPacket;
	@ViewInject(R.id.tvOneYuan)
	private TextView tvOneYuan;
	@ViewInject(R.id.ivLineLuck)
	private ImageView ivLineLuck;
	@ViewInject(R.id.ivLineRed)
	private ImageView ivLineRed;
	@ViewInject(R.id.ivLineOneYuan)
	private ImageView ivLineOneYuan;
	// 布局中隐藏的出价记录,中拍记录,top3000
	private TextView tvLuckAuctionH;
	private TextView tvRedPacketH;
	private TextView tvOneYuanH;
	private ImageView ivLineLuckH;
	private ImageView ivLineRedH;
	private ImageView ivLineOneYuanH;
	// 商品描述，往期揭晓，晒单分享
	private LinearLayout llGoodsShare;
	private LinearLayout llBygoneDays;
	private LinearLayout llProductDescription;

	// 隐藏内容
	private View headerView;
	// 头部列表
	private ListView lvHead;
	// 轮播
	private SlideShowView slideShowView;
	// 判断上下拉刷新
	private int currPage = 1;
	// 详细页面适配器, top3000 ,出价, 中拍
	private GoodsLuckRedOneDetailAdapter adapter;
	private RecordAdapter adapterbid;
	// 幸运拍56红包区3一元拍
	private int type;
	private String productId;
	private String goodsId;
	// 0进行 1即将开始 2结束
	private int flag = 0;//
	// 一元拍附加ID
	private String goodsPTId;
	// 一元拍PTID
	private String onePurchasePTId;
	private List<AdInfoEntity> AdInfoList;
	// 状态
	private int status;
	/**
	 * 商品ID号
	 */
	private String id;
	/**
	 * 市场价格
	 */
	private int marketPrice;

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
				// 区分红包区 一元拍 幸运拍
				type = bundle.getInt("type");
				// 一元拍附加ID
				goodsPTId = bundle.getString("goodsPTId");
			}
		}
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		// 标题
		tvCenter.setText("拍品详情");
		ivLeft.setImageResource(R.drawable.top_back_round);
		tvLuckAuction.setText("出价记录");
		tvRedPacket.setText("中拍记录");
		tvOneYuan.setText("Top3000");

		// 头部内容
		headerView = View.inflate(act, R.layout.activity_auction_begin_head,
				null);
		// 隐藏内容
		View invisView = View.inflate(act, R.layout.fragment_home_action, null);
		// 添加头部内容
		listView.addHeaderView(headerView);
		listView.addHeaderView(invisView);

		// 添加底部内容
		slideShowView = (SlideShowView) headerView
				.findViewById(R.id.slideShowView);
		llGoodsShare = (LinearLayout) headerView
				.findViewById(R.id.llGoodsShare);
		llBygoneDays = (LinearLayout) headerView
				.findViewById(R.id.llBygoneDays);
		llProductDescription = (LinearLayout) headerView
				.findViewById(R.id.llProductDescription);
		// 晒单分享
		llGoodsShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (productId != null) {
					Bundle bundle = new Bundle();
					Intent intent = new Intent(act,
							NewestSingleShareActivity.class);
					// 商品ID
					bundle.putInt("goodsProductId", Integer.parseInt(productId));
					// 类型
					bundle.putInt("type", type);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}

		});
		// 往期揭晓
		llBygoneDays.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (productId != null) {
					Bundle bundle = new Bundle();
					Intent intent = new Intent(act,
							NewestLaterAnnounceActivity.class);
					// 商品ID
					bundle.putInt("goodsProductId", Integer.parseInt(productId));
					// 类型
					bundle.putInt("type", type);
					if (type==3&&status==2) {
						bundle.putInt("status", 2);
						bundle.putInt("onePurchasePTId", Integer.parseInt(onePurchasePTId));
					}
					intent.putExtras(bundle);
					startActivity(intent);
				}

			}

		});
		// 商品描述
		llProductDescription.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (goodsId != null) {
					Bundle bundle = new Bundle();
					Intent intent = new Intent(act,
							GoodsDescriptionActivity.class);
					// 商品ID
					bundle.putString("goodsId", goodsId);
					// 类型
					bundle.putInt("type", type);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}

		});

		// 出价记录
		tvLuckAuctionH = (TextView) invisView.findViewById(R.id.tvLuckAuction);
		ivLineLuckH = (ImageView) invisView.findViewById(R.id.ivLineLuck);
		// 中拍记录
		tvRedPacketH = (TextView) invisView.findViewById(R.id.tvRedPacket);
		ivLineRedH = (ImageView) invisView.findViewById(R.id.ivLineRed);
		// Top3000
		tvOneYuanH = (TextView) invisView.findViewById(R.id.tvOneYuan);
		ivLineOneYuanH = (ImageView) invisView.findViewById(R.id.ivLineOneYuan);

		tvLuckAuctionH.setText("出价记录");
		tvRedPacketH.setText("中拍记录");
		tvOneYuanH.setText("Top3000");
		if (type == 3 || type == 56) {
			tvOneYuan.setVisibility(View.GONE);
			tvOneYuanH.setVisibility(View.GONE);
			ivLineOneYuan.setVisibility(View.GONE);
			ivLineOneYuanH.setVisibility(View.GONE);
		}
		// 出价记录
		tvLuckAuctionH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				selectDid();
			}

		});
		// 中拍记录
		tvRedPacketH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				selectWin();
			}
		});
		// Top3000
		tvOneYuanH.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				selectTop();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 * @param type
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
				PriceRecord();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				PriceRecord();
			}
		});
		// 初始化数据
		adapterbid = new RecordAdapter(act, new ArrayList<RecordItem>(), type);
		listView.setAdapter(adapterbid);
		// adapterbid.addAll(CacheManage.get("luck_list" + flag,
		// DidResultEntity.class));
		adapterbid.notifyDataSetChanged();
	}

	/**
	 * 获取中间部分商品详情适配器
	 * 
	 * @author: yizhong.xu
	 */
	private void getLuck() {
		adapter = new GoodsLuckRedOneDetailAdapter(act,
				new ArrayList<LuckyProductByIdResult>(), type, flag);
		lvHead = (ListView) headerView.findViewById(R.id.lvHead);
		lvHead.setAdapter(adapter);
		getLuckData();
	}

	/**
	 * 获得详细页面数据
	 * 
	 * @author: yizhong.xu
	 * @param type
	 */
	private void getLuckData() {
		// 获取详细页面数据
		xHttpClient httpClient = new xHttpClient(act);
		if (type == 55 || type == 77) {
			httpClient.url.append("api/Lucky/GetLuckyProductById");
			httpClient.url.append("/" + Integer.parseInt(goodsId));
		}
		if (type == 56) {
			httpClient.url.append("api/TryArea/GetTryAreaProductById");
			httpClient.url.append("/" + Integer.parseInt(goodsId));
		}
		if (type == 3) {
			httpClient.url.append("api/One/GetOneProductById");
			httpClient.url.append("/" + Integer.parseInt(goodsId));
			if (flag == 2) {
				httpClient.url.append("/" + Integer.parseInt(goodsPTId));
			}

		}

		httpClient.post(new xResopnse() {

			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyProductByIdEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyProductByIdEntity.class);
				if (response != null) {
					List<LuckyProductByIdResult> tmpList = response.getResult();
					AdInfoList = response.getPImgSrc();
					// 产品ID
					productId = tmpList.get(0).getProductId() + "";

					// 启动图片轮播线程
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							Message message = new Message();
							message.what = 1;
							mHandler.sendMessage(message);
						}
					});
					thread.start();
					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

					if (type == 3) {
						marketPrice = response.getResult().get(0)
								.getMarketPrice();
						adapter.setOneYuan(response.getStatus());
						adapter.setOnePurchaseTime(response
								.getOnePurchaseTime());
						// 一元拍的状态
						if (response.getStatus().equals("2")) {
							status = 2;
						} else if (response.getStatus().equals("1")) {
							status = 1;
						} else {
							status = 0;
						}
						// 一元拍的PTID
						onePurchasePTId = tmpList.get(0).getOnePurchasePTId();
						PriceRecord();
					} else {
						// 开始时间
						Date startTemp = ToolDateTime.parseTime(tmpList.get(0)
								.getEndDate());
						id = tmpList.get(0).getId();
						long startData = startTemp.getTime();
						// 服务器时间
						long timeNow = GetTimeHelper.getTime().getTime();
						// 幸运拍 红包区状态
						if (tmpList.get(0).getIsFinish().equals("0")) {
							status = 0;
						} else if (tmpList.get(0).getIsFinish().equals("1")) {
							if (timeNow > startData) {
								status = 1;// 即将开始
							} else {
								status = 4;// 即将揭晓
							}

						} else if (tmpList.get(0).getIsFinish().equals("2")) {
							status = 2;
						} else if (tmpList.get(0).getIsFinish().equals("3")) {
							status = 3;
						}
						PriceRecord();
					}

					switch (status) {
					case 0: {// 进行中
						btn_Bidders.setText("参与竞拍");
						break;
					}
					case 1: {// 开始
						btn_Bidders.setText("即将开始");
						btn_Bidders.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.shape3));
						break;
					}
					case 2: {// 结束
						btn_Bidders.setText("竞拍结束");
						btn_Bidders.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.shape5));
						break;
					}
					case 3: {// 流拍
						btn_Bidders.setText("竞拍结束");
						btn_Bidders.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.shape5));
						break;
					}
					case 4: {// 等待揭晓
						btn_Bidders.setText("等待揭晓");
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
	 * 
	 * 图片轮播
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-8-18
	 */
	public Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				slideShowView.setFlag("2");
				// 轮播
				slideShowView.init(AdInfoList);

				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

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
				PriceRecord();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				PriceRecord();
			}
		});
		// 初始化数据
		if (adapterbid == null) {
			adapterbid = new RecordAdapter(act, new ArrayList<RecordItem>(),
					type);
			listView.setAdapter(adapterbid);
		} else {
			adapterbid.setType(type);
		}
		// adapterbid.addAll(CacheManage.get("luck_list" + flag,
		// DidResultEntity.class));
		// adapterbid.notifyDataSetChanged();
		// listView.setSelection(2);
		PriceRecord();
	}

	/**
	 * 出价记录
	 * 
	 * @author: yizhong.xu
	 */
	private void PriceRecord() {
		xHttpClient httpClient = new xHttpClient(act, false);
		if (type == 55 || type == 77) {
			httpClient.url.append("api/lucky/GetLuckyOffer");// 方法
		}
		if (type == 56) {
			httpClient.url.append("api/TryArea/GetTryAreaOffer");// 方法
		}
		if (type == 3) {
			httpClient.url.append("api/One/GetOneProductOffer");// 方法
			httpClient.url.append("/" + onePurchasePTId);// 每页条数
		} else {
			httpClient.url.append("/" + goodsId);// 每页条数
		}
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyGetLuckyOfferEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyGetLuckyOfferEntity.class);
				if (response != null) {
					List<DidResultEntity> tmpList = response.getResult();
					List<RecordItem> dataList = new ArrayList<RecordItem>();
					for (DidResultEntity bigItem : tmpList) {
						RecordItem item = new RecordItem(
								RecordItem.TYPE_BIG_RECORD);
						item.bigRecord = bigItem;
						dataList.add(item);
					}
					// if (isSaveCache) {
					// CacheManage.remove("luck_list" + flag);
					// CacheManage.put("luck_list" + flag, tmpList);
					// adapterbid.getList().clear();
					// isSaveCache = false;
					// }
					if (currPage == 1) {
						adapterbid.setList(dataList);
					} else {
						adapterbid.addAll(dataList);
					}

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				} else {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}
				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				listView.stopLoadMore();
				listView.stopRefresh();
				if (currPage == 1) {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}
			}

			@Override
			public void onStart() {

			}
		});
	}

	/**
	 * 中拍
	 * 
	 * @author: yizhong.xu
	 */
	private void selectWin() {
		// 重置
		reset();
		ivLineRed.setVisibility(View.VISIBLE);
		ivLineRedH.setVisibility(View.VISIBLE);
		tvRedPacket
				.setTextColor(getResources().getColor(R.color.include_title));
		tvRedPacketH.setTextColor(getResources()
				.getColor(R.color.include_title));

		// 下拉加载更多
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				priceWin();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				priceWin();
			}
		});
		// 加载数据
		if (adapterbid == null) {
			adapterbid = new RecordAdapter(act, new ArrayList<RecordItem>(),
					type);
			listView.setAdapter(adapterbid);
		} else {
			adapterbid.setType(type);
		}
		// adapterwin.addAll(CacheManage.get("red_list" + flag,
		// DetailsPriceResult.class));
		// adapterwin.notifyDataSetChanged();
		// listView.setSelection(2);
		priceWin();

	}

	/**
	 * 中拍记录
	 * 
	 * @author: yizhong.xu
	 */
	private void priceWin() {

		xHttpClient httpClient = new xHttpClient(act, false);
		if (type == 55 || type == 77) {
			httpClient.url.append("api/lucky/GetLuckyWinnerById");// 方法
			httpClient.url.append("/" + goodsId);// ID
		}
		if (type == 56) {
			httpClient.url.append("api/tryarea/GetTryAreaWinnerById");// 方法
			httpClient.url.append("/" + goodsId);// ID
		}
		if (type == 3) {
			httpClient.url.append("api/One/GetOneWinnerById");// 方法
			httpClient.url.append("/" + goodsPTId);// ID
		}

		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				DetailsPriceWinEntity response = JsonUtil.parseObject(act,
						responseInfo.result, DetailsPriceWinEntity.class);
				if (response != null) {

					List<DetailsPriceResult> tmpList = response.getResult();
					List<RecordItem> dataList = new ArrayList<RecordItem>();
					for (DetailsPriceResult bigItem : tmpList) {
						RecordItem item = new RecordItem(
								RecordItem.TYPE_WIN_RECORD);
						item.winRecord = bigItem;
						dataList.add(item);
					}
					// if (isSaveCache) {
					// CacheManage.remove("red_list" + flag);
					// CacheManage.put("red_list" + flag, tmpList);
					// adapterwin.getList().clear();
					// isSaveCache = false;
					// }
					if (currPage == 1) {
						adapterbid.setList(dataList);
					} else {
						adapterbid.addAll(dataList);
					}

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				} else {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}

				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				listView.stopLoadMore();
				listView.stopRefresh();
				if (currPage == 1) {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}
			}

			@Override
			public void onStart() {

			}
		});

	}

	/**
	 * top3000
	 * 
	 * @author: yizhong.xu
	 */
	private void selectTop() {
		// 重置
		reset();
		// 选中当前点击的Item
		ivLineOneYuan.setVisibility(View.VISIBLE);
		ivLineOneYuanH.setVisibility(View.VISIBLE);
		tvOneYuan.setTextColor(getResources().getColor(R.color.include_title));
		tvOneYuanH.setTextColor(getResources().getColor(R.color.include_title));
		// 下拉加载更多
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				top3000();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				top3000();
			}
		});
		// 加载数据
		if (adapterbid == null) {
			adapterbid = new RecordAdapter(act, new ArrayList<RecordItem>(),
					type);
			listView.setAdapter(adapterbid);
		}
		// adaptertop.addAll(CacheManage.get("bid_oneyuans_list" + flag,
		// DetailsTop3000ResultEntity.class));
		// adaptertop.notifyDataSetChanged();
		// listView.setSelection(2);
		top3000();
	}

	/**
	 * top3000
	 * 
	 * @author: yizhong.xu
	 */
	private void top3000() {
		xHttpClient httpClient = new xHttpClient(act, true);
		httpClient.url.append("api/lucky/GetLuckyTopWinner");// 方法
		httpClient.url.append("/" + goodsId);// 每页条数
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				DetailsTop3000Entity response = JsonUtil.parseObject(act,
						responseInfo.result, DetailsTop3000Entity.class);
				if (response != null) {
					List<DetailsTop3000ResultEntity> tmpList = response
							.getResult();
					if (!tmpList.isEmpty()) {
						List<RecordItem> dataList = new ArrayList<RecordItem>();
						for (DetailsTop3000ResultEntity bigItem : tmpList) {
							RecordItem item = new RecordItem(
									RecordItem.TYPE_TOP_RECORD);
							item.topRecord = bigItem;
							dataList.add(item);
						}
						// if (isSaveCache) {
						// CacheManage.remove("bid_oneyuans_list" + flag);
						// CacheManage
						// .put("bid_oneyuans_list" + flag, tmpList);
						// adaptertop.getList().clear();
						// isSaveCache = false;
						// }
						if (currPage == 1) {
							adapterbid.setList(dataList);
						} else {
							adapterbid.addAll(dataList);
						}
						if (currPage < Helper.paging(response.getTotal())) {
							listView.setPullLoadEnable(true);
						} else {
							listView.setPullLoadEnable(false);
						}
					}
				} else {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}

				listView.stopLoadMore();
				listView.stopRefresh();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				listView.stopLoadMore();
				listView.stopRefresh();
				if (currPage == 1) {
					adapterbid.getList().clear();
					adapterbid.notifyDataSetChanged();
				}
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

		});
	}

	/**
	 * 重置按钮状态
	 * 
	 * @author: yizhong.xu
	 */
	private void reset() {
		currPage = 1;
		// 一元拍 下划线
		ivLineOneYuan.setVisibility(View.INVISIBLE);
		ivLineOneYuanH.setVisibility(View.INVISIBLE);
		// 幸运拍 下划线
		ivLineLuck.setVisibility(View.INVISIBLE);
		ivLineLuckH.setVisibility(View.INVISIBLE);
		// 红包区 下划线
		ivLineRed.setVisibility(View.INVISIBLE);
		ivLineRedH.setVisibility(View.INVISIBLE);
		// 一元拍 字体
		tvOneYuan.setTextColor(getResources().getColor(android.R.color.black));
		tvOneYuanH.setTextColor(getResources().getColor(android.R.color.black));
		// 幸运拍 字体
		tvLuckAuction.setTextColor(getResources().getColor(
				android.R.color.black));
		tvLuckAuctionH.setTextColor(getResources().getColor(
				android.R.color.black));
		// 红包区 字体
		tvRedPacket
				.setTextColor(getResources().getColor(android.R.color.black));
		tvRedPacketH.setTextColor(getResources()
				.getColor(android.R.color.black));
		if (type == 3 || type == 56) {
			tvOneYuan.setVisibility(View.GONE);
			tvOneYuanH.setVisibility(View.GONE);
			ivLineOneYuan.setVisibility(View.GONE);
			ivLineOneYuanH.setVisibility(View.GONE);
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvLuckAuction, R.id.tvRedPacket,
			R.id.tvOneYuan, R.id.ivLineLuck, R.id.llProductDescription,
			R.id.llGoodsShare, R.id.llBygoneDays, R.id.btn_Bidders })
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
		case R.id.tvOneYuan: {// 跳转top3000
			selectTop();
			break;
		}
		case R.id.btn_Bidders: {// 参与竞拍
			if (btn_Bidders.getText().toString().equals("等待揭晓")) {
				return;
			}
			if (btn_Bidders.getText().toString().equals("即将开始")) {
				return;
			}
			if (btn_Bidders.getText().toString().equals("竞拍结束")) {
				ToolToast.showShort("该商品竞拍已经结束了");
				return;
			}
			if (!UserManage.isLogin()) {
				showActivity(act, LoginActivity.class);
				return;
			}
			if (!btn_Bidders.getText().toString().equals("竞拍结束")) {
				Bundle bundle = new Bundle();
				bundle.putInt("type", type);// 55幸运拍，56红包区，3一元拍
				bundle.putString("id", id);
				bundle.putString("goodsId", goodsId);
				bundle.putString("goodsPTId", goodsPTId);
				bundle.putString("goodsOneID", productId);
				if (type == 3) {
					bundle.putInt("marketPrice", marketPrice);
				}
				showActivity(act, PartakeActivity.class, bundle);
				// 刷新列表
				adapterbid.notifyDataSetChanged();
			} else {
				ToolToast.showShort("该商品竞拍已经结束了");
			}
			break;
		}
		}
	}
}
