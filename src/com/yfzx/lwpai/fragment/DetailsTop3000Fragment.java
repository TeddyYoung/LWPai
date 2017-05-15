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
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.Top3000RecordAdapter;
import com.yfzx.lwpai.entity.DetailsTop3000Entity;
import com.yfzx.lwpai.entity.DetailsTop3000ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * top3000
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class DetailsTop3000Fragment extends BaseFragment {

	@ViewInject(R.id.listView)
	private XListView listView;
	private int currPage = 1;

	private Top3000RecordAdapter adapter;
	protected boolean isSave = true;
	private String goods;

	public static DetailsTop3000Fragment newInstance(String goodsid) {
		Bundle bundle = new Bundle();
		bundle.putString("goodsid", goodsid);
		DetailsTop3000Fragment fragment = new DetailsTop3000Fragment();
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
		adapter.addAll(CacheManage.get("bid_oneyuans_list", DetailsTop3000ResultEntity.class));
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
		adapter = new Top3000RecordAdapter(act, new ArrayList<DetailsTop3000ResultEntity>());
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
		lazyLoad();
		// if (flag == 1) {// 幸运拍
		// lastOpen();
		// }
		// if (flag == 2)// 红包区
		// {
		// lastTryAreaOpen();
		// }
		// if (flag == 3)// 一元拍
		// {
		// OneYuanOpen();
		// }

	}

	/**
	 * 出价记录
	 * 
	 * @author: yizhong.xu
	 */
	private void lastOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/lucky/GetLuckyTopWinner");// 方法
		httpClient.url.append("/" + goods);// 每页条数
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				DetailsTop3000Entity response = JsonUtil.parseObject(act,
						responseInfo.result, DetailsTop3000Entity.class);
				if (response != null) {

					List<DetailsTop3000ResultEntity> tmpList = response.getResult();

					adapter.addAll(tmpList);

					adapter.notifyDataSetChanged();
					setListViewHeightBasedOnChildren(listView);

				} else {
					ToolToast.showShort("没有出价记录");
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
