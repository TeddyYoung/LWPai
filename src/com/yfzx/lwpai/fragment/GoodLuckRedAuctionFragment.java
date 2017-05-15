package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GoodsLuckRedAdapter;
import com.yfzx.lwpai.entity.GoodsLuckEntity;
import com.yfzx.lwpai.entity.GoodsLuckResult;
import com.yfzx.lwpai.entity.LuckyDateEntity;
import com.yfzx.lwpai.entity.LuckyDateEntity.ResultEntity;
import com.yfzx.lwpai.util.CountDown;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 幸运拍
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-12
 */
public class GoodLuckRedAuctionFragment extends BaseFragment {

	// 列表
	@ViewInject(R.id.listView)
	private XListView listView;
	// 头部广告
	@ViewInject(R.id.llytHeadAd)
	private LinearLayout llytHeadAd;
	// 标签1
	@ViewInject(R.id.rdoBtnOne)
	private RadioButton rdoBtnOne;
	// 标签2
	@ViewInject(R.id.rdoBtnTwo)
	private RadioButton rdoBtnTwo;
	// 标签3
	@ViewInject(R.id.rdoBtnThree)
	private RadioButton rdoBtnThree;
	// 标签4
	@ViewInject(R.id.rdoBtnFour)
	private RadioButton rdoBtnFour;
	// 标签5
	@ViewInject(R.id.rdoBtnFive)
	private RadioButton rdoBtnFive;

	// 标签1
	@ViewInject(R.id.iv_bottom_line1)
	private ImageView iv_bottom_line1;
	// 标签2
	@ViewInject(R.id.iv_bottom_line2)
	private ImageView iv_bottom_line2;
	// 标签3
	@ViewInject(R.id.iv_bottom_line3)
	private ImageView iv_bottom_line3;
	// 标签4
	@ViewInject(R.id.iv_bottom_line4)
	private ImageView iv_bottom_line4;
	// 标签5
	@ViewInject(R.id.iv_bottom_line5)
	private ImageView iv_bottom_line5;
	// 距离本次结束时间
	@ViewInject(R.id.tvDistance)
	private TextView tvDistance;

	// 适配器
	private GoodsLuckRedAdapter adapter;
	// 页面
	private int currPage = 1;
	protected boolean isSave = true;
	// 0位即将开始，1为正在进行，2为结束
	private int goodStatus = 0;
	/**
	 * 判断头部日期个数
	 */
	private int conductValue = 0;
	// 3是一元拍，55是幸运拍，56是红包区
	private int goodType = 55;

	// 进行日期
	private String conductDay;
	// 即将开始
	private String conduct4;
	private String conduct3;
	private String conduct2;
	private String conduct1;
	private List<GoodsLuckResult> tmpList;
	// 判断是否在进行
	private boolean isConduct = false;
	// 判断是否是即将开始
	private boolean isBegin = false;
	// 点击等数据加载完，才可以下一次加载数据
	private boolean isOnclick = true;
	/**
	 * 判断是否加滚动条
	 */
	private boolean isLoad = true;
	private xHttpClient httpClient;

	public GoodLuckRedAuctionFragment(int goodType) {
		this.goodType = goodType;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_goods_luckauction,
				container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initWidget(goodType);
		// 读取商品分类日期
		LuckyDate(goodType);
		initData(goodStatus, goodType, conductDay);
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget(final int goodType) {
		// 头部广告
		tvDistance.setVisibility(View.VISIBLE);
		llytHeadAd.setVisibility(View.VISIBLE);
		// 倒计时
		ReceiveRedTime();
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				// 判断是否在进行
				isConduct = true;
				if (conductDay == null) {
					conductValue = 0;
					LuckyDate(goodType);
				}
				if (rdoBtnTwo.isChecked()) {
					conductDay = conduct1;
				} else if (rdoBtnThree.isChecked()) {
					conductDay = conduct2;
				} else if (rdoBtnFour.isChecked()) {
					conductDay = conduct3;
				} else if (rdoBtnFive.isChecked()) {
					conductDay = conduct4;
				}
				isLoad = false;
				getListData(goodStatus, goodType, conductDay);

			}

			@Override
			public void onLoadMore() {
				currPage++;
				isLoad = false;
				getListData(goodStatus, goodType, conductDay);
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData(final int status, final int goodType,
			String conductDay) {
		adapter = new GoodsLuckRedAdapter(act,
				new ArrayList<GoodsLuckResult>(), goodStatus, goodType);
		listView.setAdapter(adapter);
		// 读取缓存
		lazyLoad(status, goodType, conductDay);
	}

	/**
	 * 获取幸运拍日期数据
	 * 
	 * @author: songbing.zhou
	 */
	private void LuckyDate(final int goodType) {
		xHttpClient httpClient = new xHttpClient(act, false);
		switch (goodType) {
		case 55:// 幸运拍
			httpClient.url.append("api/Lucky/GetLuckyDateList");
			break;
		case 56:// 红包区
			httpClient.url.append("api/TryArea/GetTryAreaDateList");
			break;
		}
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyDateEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyDateEntity.class, "");
				if (response != null) {
					List<ResultEntity> tmpList = response.getResult();
					for (ResultEntity resultEntity : tmpList) {
						if (Integer.parseInt(resultEntity.getIsStart()) == 0) {// 即将开始
							rdoBtnFive.setVisibility(View.VISIBLE);
							iv_bottom_line5.setVisibility(View.INVISIBLE);
							String[] startTemp = ToolDateTime
									.split(resultEntity.getEndTime());
							rdoBtnFive.setText(startTemp[1] + "月"
									+ startTemp[2] + "日" + "\n即将开始");
							conduct4 = resultEntity.getEndTime();
							isBegin = true;
						} else {// 进行中的
							String[] conductTemp = ToolDateTime
									.split(resultEntity.getEndTime());

							switch (conductValue) {
							case 0:
								iv_bottom_line2.setVisibility(View.INVISIBLE);
								rdoBtnTwo.setVisibility(View.VISIBLE);
								rdoBtnTwo.setText(conductTemp[1] + "月"
										+ conductTemp[2] + "日" + "\n竞拍进行");
								// 进行日期
								conduct1 = resultEntity.getEndTime();
								conductValue++;
								rdoBtnTwo.performClick();
								conductDay = conduct1;
								break;
							case 1:
								iv_bottom_line3.setVisibility(View.INVISIBLE);
								rdoBtnThree.setVisibility(View.VISIBLE);
								rdoBtnThree.setText(conductTemp[1] + "月"
										+ conductTemp[2] + "日" + "\n竞拍进行");
								// 判断是否在进行
								isConduct = true;
								// 进行日期
								conduct2 = resultEntity.getEndTime();
								conductDay = conduct2;
								conductValue++;
								break;
							case 2:
								iv_bottom_line4.setVisibility(View.INVISIBLE);
								rdoBtnFour.setVisibility(View.VISIBLE);
								rdoBtnFour.setText(conductTemp[1] + "月"
										+ conductTemp[2] + "日" + "\n竞拍进行");
								// 判断是否在进行
								isConduct = true;
								// 进行日期
								conduct3 = resultEntity.getEndTime();
								conductDay = conduct3;
								conductValue++;
								break;
							case 3:
								iv_bottom_line5.setVisibility(View.INVISIBLE);
								rdoBtnFive.setVisibility(View.VISIBLE);
								rdoBtnFive.setText(conductTemp[1] + "月"
										+ conductTemp[2] + "日" + "\n竞拍进行");
								// 判断是否在进行
								isConduct = true;
								// 判断是否是即将开始
								isBegin = false;
								// 进行日期
								conduct4 = resultEntity.getEndTime();
								conductDay = conduct4;
								conductValue++;
								break;
							}
						}
					}
				}

			}

		});
	}

	/**
	 * 加载缓存
	 * 
	 * @author: bangwei.yang
	 * @param status
	 */
	private void lazyLoad(int status, int goodType, String conductDay) {
		if (currPage == 1) {
			// 没有日期的正在进行或者即将开始不进入缓存
			if (conductDay == null) {
				// 商品结束没有日期可以进入缓存
				if (goodType == 2) {
					adapter.setList(CacheManage
							.get("goodsluck_list" + status + "" + goodType
									+ conductDay, GoodsLuckResult.class));
				}
			} else {
				adapter.setList(CacheManage.get("goodsluck_list" + status + ""
						+ goodType + conductDay, GoodsLuckResult.class));
			}

		} else {
			// 没有日期的正在进行或者即将开始不进入缓存
			if (conductDay == null) {
				// 商品结束没有日期可以进入缓存
				if (goodType == 2) {
					adapter.addAll(CacheManage
							.get("goodsluck_list" + status + "" + goodType
									+ conductDay, GoodsLuckResult.class));
				}
			} else {
				adapter.addAll(CacheManage.get("goodsluck_list" + status + ""
						+ goodType + conductDay, GoodsLuckResult.class));
			}

		}
		adapter.notifyDataSetChanged();
		// 得到数据
		getListData(status, goodType, conductDay);
	}

	/**
	 * 获取列表数据
	 * 
	 * @author: songbing.zhou
	 */
	private void getListData(final int status, final int goodType,
			final String conductDay) {
		if (isLoad) {
			httpClient = new xHttpClient(act, true);
		} else {
			httpClient = new xHttpClient(act, false);
		}
		switch (goodType) {
		case 55:// 幸运拍
			httpClient.url.append("api/Lucky/GetLuckyProductList");
			break;

		case 56:// 红包区
			httpClient.url.append("api/TryArea/GetTryAreaProductList");
			break;
		}
		httpClient.url.append("/" + status);// 0进行中，1即将开始，2竞拍结束
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		if (conductDay != null) {
			if (isConduct) {
				httpClient.url.append("/" + conductDay);// 进行日期
				isConduct = false;
			}
		}
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GoodsLuckEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GoodsLuckEntity.class);
				if (response != null) {
					tmpList = response.getResult();
					if (isSave) {
						CacheManage.remove("goodsluck_list" + status + ""
								+ goodType + conductDay);
						CacheManage.put("goodsluck_list" + status + ""
								+ goodType + conductDay, tmpList);
						adapter.getList().clear();
						isSave = false;
					}
					if (currPage == 1) {
						adapter.setList(tmpList);
					} else {
						adapter.addAll(tmpList);
					}
					adapter.notifyDataSetChanged();

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				}

				listView.stopLoadMore();
				listView.stopRefresh();
				// 判断数据是否加载完
				isOnclick = true;
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
	 * 倒计时 距离领取红包结束时间
	 * 
	 * @author: songbing.zhou
	 */
	private CountDown countDown;

	private void ReceiveRedTime() {
		// Date EndDate = ToolDateTime.parseDate(tmpList.get(0)
		// .getEndDate());
		// Long millisInFuture = EndDate.getTime();
		if (countDown != null) {
			countDown.cancel();
			countDown = null;
		}
		if (conductDay != null) {
			try {
				String[] days = conductDay.split("-");
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, Integer.parseInt(days[0]));
				calendar.set(Calendar.MONTH, Integer.parseInt(days[1]) - 1);
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(days[2]));
				calendar.set(Calendar.HOUR_OF_DAY, 21);
				calendar.set(Calendar.MINUTE, 50);
				calendar.set(Calendar.SECOND, 00);
				long countTime = calendar.getTimeInMillis()
						- Calendar.getInstance().getTimeInMillis();
				if (isBegin) {
					tvDistance.setVisibility(View.GONE);
				} else {
					if (countTime <= 0) {
						tvDistance.setVisibility(View.GONE);
					} else {
						tvDistance.setVisibility(View.VISIBLE);
						countDown = new CountDown(countTime, tvDistance, 1);// 默认一天
						countDown.start();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				tvDistance.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 重置所有底部页面
	 * 
	 * @author: bangwei.yang
	 */
	private void resetIv() {
		isLoad = true;
		if (iv_bottom_line1.getVisibility() == View.VISIBLE) {
			iv_bottom_line1.setVisibility(View.INVISIBLE);
		}
		if (iv_bottom_line2.getVisibility() == View.VISIBLE) {
			iv_bottom_line2.setVisibility(View.INVISIBLE);
		}
		if (iv_bottom_line3.getVisibility() == View.VISIBLE) {
			iv_bottom_line3.setVisibility(View.INVISIBLE);
		}
		if (iv_bottom_line4.getVisibility() == View.VISIBLE) {
			iv_bottom_line4.setVisibility(View.INVISIBLE);
		}
		if (iv_bottom_line5.getVisibility() == View.VISIBLE) {
			iv_bottom_line5.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.rdoBtnOne, R.id.rdoBtnTwo, R.id.rdoBtnThree,
			R.id.rdoBtnFour, R.id.rdoBtnFive, R.id.tvReceiveRed })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rdoBtnOne:// 结束商品
			resetIv();
			iv_bottom_line1.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				currPage = 1;
				goodStatus = 2;
				initData(2, goodType, conductDay);
				llytHeadAd.setVisibility(View.GONE);

				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnTwo:// 竞拍进行
			resetIv();
			iv_bottom_line2.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				currPage = 1;
				// 进行日期
				conductDay = conduct1;
				// 判断是否在进行
				isConduct = true;
				isBegin = false;
				goodStatus = 0;
				initData(0, goodType, conduct1);
				llytHeadAd.setVisibility(View.VISIBLE);

				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnThree:// 竞拍进行
			resetIv();
			iv_bottom_line3.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				currPage = 1;
				// 进行日期
				conductDay = conduct2;
				// 判断是否在进行
				isConduct = true;
				isBegin = false;
				goodStatus = 0;
				initData(0, goodType, conduct2);
				llytHeadAd.setVisibility(View.VISIBLE);

				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnFour:// 竞拍进行
			resetIv();
			iv_bottom_line4.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				currPage = 1;
				// 进行日期
				conductDay = conduct3;
				// 判断是否在进行
				isConduct = true;
				isBegin = false;
				goodStatus = 0;
				initData(0, goodType, conduct3);
				llytHeadAd.setVisibility(View.VISIBLE);

				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnFive:// 即将开始
			resetIv();
			iv_bottom_line5.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				currPage = 1;
				// 进行日期
				conductDay = conduct4;
				// 判断是否在进行
				isConduct = true;
				if (isBegin) {
					// 即将开始
					goodStatus = 1;
					llytHeadAd.setVisibility(View.VISIBLE);
					tvDistance.setVisibility(View.GONE);
					initData(1, goodType, conduct4);
				} else {
					// 竞拍进行
					goodStatus = 0;
					llytHeadAd.setVisibility(View.VISIBLE);
					initData(0, goodType, conduct4);
				}

				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		}
		ReceiveRedTime();
	}
}
