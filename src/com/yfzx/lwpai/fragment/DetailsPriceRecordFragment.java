package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.BidRecordAdapter;
import com.yfzx.lwpai.entity.DidResultEntity;
import com.yfzx.lwpai.entity.LuckyGetLuckyOfferEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 出价记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月17日
 */
public class DetailsPriceRecordFragment extends BaseFragment {

	@ViewInject(R.id.listView)
	private XListView listView;
	private int currPage = 1;

	private BidRecordAdapter adapter;
	protected boolean isSave = true;
	private String goods;
	private int date = 1;// 出价记录
	private int type;// 区分是红包区幸运拍一元拍
	private String typename;// 区分是红包区幸运拍一元拍

	public static DetailsPriceRecordFragment newInstance(String goodsid,
			int type) {
		Bundle bundle = new Bundle();
		bundle.putString("goodsid", goodsid);
		bundle.putInt("type", type);
		DetailsPriceRecordFragment fragment = new DetailsPriceRecordFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_luckt_getlucky,
				container, false);
		// 获得索引值

		// 绑定界面UI

		ViewUtils.inject(this, view);

		Bundle bundle = getArguments();
		if (bundle != null) {
			goods = bundle.getString("goodsid");
			type = bundle.getInt("type");
		}
		initWidget();
		initData();
		return view;
	}

	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	private void lazyLoad() {
		adapter.addAll(CacheManage
				.get("bid_record_list", DidResultEntity.class));
		adapter.notifyDataSetChanged();
		setListViewHeightBasedOnChildren(listView);
		lastOpen();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		adapter = new BidRecordAdapter(act, new ArrayList<DidResultEntity>(),
				type);
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				lastOpen();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				lastOpen();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initData() {

		if (type == 55) {// 幸运拍
			typename = "api/lucky/GetLuckyOffer";
			lazyLoad();
		}
		if (type == 56)// 红包区
		{
			typename = "api/TryArea/GetTryAreaOffer";
			lazyLoad();
		}
		if (type == 3)// 一元拍
		{
			typename = "api/One/GetOneProductOffer";
			lazyLoad();
		}

	}

	/**
	 * 出价记录(幸运拍)
	 * 
	 * @author: yizhong.xu
	 */
	private void lastOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append(typename);// 方法
		httpClient.url.append("/" + goods);// 每页条数
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LuckyGetLuckyOfferEntity response = JsonUtil.parseObject(act,
						responseInfo.result, LuckyGetLuckyOfferEntity.class);
				if (response != null) {

					List<DidResultEntity> tmpList = response.getResult();

					adapter.addAll(tmpList);

					adapter.notifyDataSetChanged();

					// int listViewHeight =
					// ViewUtil.setListViewHeightBasedOnChildren1(listView);
					// ViewGroup.LayoutParams params =
					// vpImgpager.getLayoutParams();
					// params.height = listViewHeight;
					// vpZifuduo.setLayoutParams(params);
					setListViewHeightBasedOnChildren(listView);
				}
			}
		});

	}

	// /**
	// * 往期揭晓红包区
	// *
	// * @author: yizhong.xu
	// */
	// private void lastTryAreaOpen() {
	// xHttpClient httpClient = new xHttpClient(act, false);
	// httpClient.url.append("api/OrderShare/GetOrderShare");// 方法
	// httpClient.url.append("/" + 56);// 結束公佈的中拍者
	// httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
	// httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
	// httpClient.url.append("/" + currPage);// 第几页
	// httpClient.post(new xResopnse() {
	// @Override
	// public void onSuccess(ResponseInfo<String> responseInfo) {
	// ProgressHelper.getInstance().cancel();
	// GetOrderShareEntity response = JsonUtil.parseObject(act,
	// responseInfo.result, GetOrderShareEntity.class);
	// if (response != null) {
	//
	// List<ResultEntity> tmpList = response.getResult();
	//
	// adapter.addAll(tmpList);
	// adapter.notifyDataSetChanged();
	//
	// } else {
	// ToolToast.showShort("没数据");
	// }
	// }
	// });
	//
	// }
	//
	// /**
	// * 往期揭晓一元拍
	// *
	// * @author: yizhong.xu
	// */
	// private void OneYuanOpen() {
	// xHttpClient httpClient = new xHttpClient(act, false);
	// httpClient.url.append("api/OrderShare/GetOrderShare");// 方法
	// httpClient.url.append("/" + 3);// 結束公佈的中拍者
	// httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
	// httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
	// httpClient.url.append("/" + currPage);// 第几页
	// httpClient.post(new xResopnse() {
	// @Override
	// public void onSuccess(ResponseInfo<String> responseInfo) {
	// ProgressHelper.getInstance().cancel();
	// GetOrderShareEntity response = JsonUtil.parseObject(act,
	// responseInfo.result, GetOrderShareEntity.class);
	// if (response != null) {
	//
	// List<ResultEntity> tmpList = response.getResult();
	// adapter.addAll(tmpList);
	// adapter.notifyDataSetChanged();
	//
	// } else {
	// ToolToast.showShort("没往期数据");
	// }
	// }
	// });
	//
	// }

}
