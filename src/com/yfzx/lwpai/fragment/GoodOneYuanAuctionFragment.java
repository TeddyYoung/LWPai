package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
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
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GoodsOneYuanAdapter;
import com.yfzx.lwpai.entity.GoodsOneYuanEntity;
import com.yfzx.lwpai.entity.GoodsOneYuanResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 一元拍
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-12
 */
public class GoodOneYuanAuctionFragment extends BaseFragment {

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
	// 领取红包
	@ViewInject(R.id.tvReceiveRed)
	private TextView tvReceiveRed;

	private GoodsOneYuanAdapter adapter;
	private int currPage = 1;
	protected boolean isSave = true;
	// 0进行中，1即将开始，2竞拍结束
	private int goodStatus = 0;
	// 一元实体
	private List<GoodsOneYuanResult> tmpList;
	// 点击等数据加载完，才可以下一次加载数据
	private boolean isOnclick = true;
	/**
	 * 判断是否加滚动条
	 */
	private boolean isLoad = true;
	private xHttpClient httpClient;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_goods_luckauction,
				container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initWidget();
		initData(goodStatus);
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		llytHeadAd.setVisibility(View.VISIBLE);
		rdoBtnOne.setText("正在进行");
		rdoBtnTwo.setText("结束商品");
		rdoBtnTwo.setVisibility(View.VISIBLE);
		rdoBtnThree.setText("即将开始");
		rdoBtnThree.setVisibility(View.VISIBLE);
		iv_bottom_line2.setVisibility(View.INVISIBLE);
		iv_bottom_line3.setVisibility(View.INVISIBLE);
		tvReceiveRed.setText("一元众筹勇夺宝");
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initData(final int status) {
		adapter = new GoodsOneYuanAdapter(act,
				new ArrayList<GoodsOneYuanResult>(), status);
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				isLoad = false;
				getListData(status);

			}

			@Override
			public void onLoadMore() {
				currPage++;
				isLoad = false;
				getListData(status);
			}
		});
		lazyLoad(status);
	}

	private void lazyLoad(int status) {
		adapter.addAll(CacheManage.get("goodsone_list" + status,
				GoodsOneYuanResult.class));
		adapter.notifyDataSetChanged();
		getListData(status);
	}

	/**
	 * 获取列表数据
	 * 
	 * @author: songbing.zhou
	 */
	private void getListData(final int status) {
		if (isLoad) {
			httpClient = new xHttpClient(act, true);
		} else {
			httpClient = new xHttpClient(act, false);
		}
		httpClient.url.append("api/One/GetOneProductList");//
		httpClient.url.append("/" + status);// 0进行中，1即将开始，2竞拍结束
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GoodsOneYuanEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GoodsOneYuanEntity.class);
				if (response != null) {
					tmpList = response.getResult();
					if (isSave) {
						CacheManage.remove("goodsone_list" + status);
						CacheManage.put("goodsone_list" + status, tmpList);
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
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.rdoBtnOne, R.id.rdoBtnTwo, R.id.rdoBtnThree })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rdoBtnOne:// 竞拍进行
			resetIv();
			iv_bottom_line1.setVisibility(View.VISIBLE);
			// 判断数据是否加载完
			if (isOnclick) {
				adapter.getList().clear();
				initData(0);
				llytHeadAd.setVisibility(View.VISIBLE);
				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnTwo:// 结束商品
			resetIv();
			iv_bottom_line2.setVisibility(View.VISIBLE);
			if (isOnclick) {
				adapter.getList().clear();
				initData(2);
				llytHeadAd.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		case R.id.rdoBtnThree:// 即将开始
			resetIv();
			iv_bottom_line3.setVisibility(View.VISIBLE);
			if (isOnclick) {
				adapter.getList().clear();
				initData(1);
				llytHeadAd.setVisibility(View.VISIBLE);
				adapter.notifyDataSetChanged();
				isOnclick = false;
			}
			break;
		}
	}
}
