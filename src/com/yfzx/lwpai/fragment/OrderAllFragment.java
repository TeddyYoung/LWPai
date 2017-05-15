package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.OrderAllAdapter;
import com.yfzx.lwpai.entity.UserOrderEntity;
import com.yfzx.lwpai.entity.UserOrderResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的订单列表 全部订单
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-27
 */
public class OrderAllFragment extends BaseFragment {

	// 订单列表
	@ViewInject(R.id.listView)
	private XListView listView;
	// 订单列表
	@ViewInject(R.id.llytOrderNull)
	private LinearLayout llytOrderNull;

	private int currPage = 1;
	private OrderAllAdapter adapter;
	// 缓存
	private boolean isSave = true;
	private int orderAllFlag;

	private View view;

	public OrderAllFragment(int orderAllFlag) {
		this.orderAllFlag = orderAllFlag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_share_essence, container,
				false);
		// 绑定界面UI
		ViewUtils.inject(this, view);
		initWidget();
		initDate();
		return view;
	}

	/**
	 * 初始化页面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				getUserOrder();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getUserOrder();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
		adapter = new OrderAllAdapter(act, new ArrayList<UserOrderResult>());
		listView.setAdapter(adapter);
		lazyLoad();
	}

	/**
	 * 缓存
	 * 
	 * @author: songbing.zhou
	 */
	private void lazyLoad() {
		adapter.addAll(CacheManage.get("orederAll_list" + orderAllFlag,
				UserOrderResult.class));
		adapter.notifyDataSetChanged();
		getUserOrder();
	}

	/**
	 * 获取订单信息
	 * 
	 * @author: songbing.zhou
	 */
	private void getUserOrder() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserOrderList");// 用户订单列表
		httpClient.url.append("/" + orderAllFlag);// 1全部，2待发货，3待收货，4待晒单
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				UserOrderEntity response = JsonUtil.parseObject(act,
						responseInfo.result, UserOrderEntity.class,"");//不做提示
				if (response != null) {
					llytOrderNull.setVisibility(View.GONE);
					// 清空列表
//					adapter.getList().clear();
					List<UserOrderResult> tmpList = response.getResult();
					if (isSave) {
						CacheManage.remove("orederAll_list" + orderAllFlag);
						CacheManage.put("orederAll_list" + orderAllFlag,
								tmpList);
						adapter.getList().clear();
						isSave = false;
					}
					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				} else {
					llytOrderNull.setVisibility(View.VISIBLE);
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
	 * 点击跳转
	 * 
	 * @author: songbing.zhou
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick(R.id.listView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

}
