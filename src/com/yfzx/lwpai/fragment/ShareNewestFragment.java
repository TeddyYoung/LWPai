package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import com.yfzx.lwpai.activity.HomeBillShareDetailsActivity;
import com.yfzx.lwpai.adapter.ShareNewestAdapter;
import com.yfzx.lwpai.entity.OrderShareList;
import com.yfzx.lwpai.entity.OrderShareResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 最新
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-28
 */
public class ShareNewestFragment extends BaseFragment {
	// 晒单列表
	@ViewInject(R.id.listView)
	private XListView listView;

	private int currPage = 1;
	private ShareNewestAdapter adapter;
	protected boolean isSave = true;
	private int status;

	public ShareNewestFragment(int status) {
		this.status = status;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_share_newest, container,
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
		adapter = new ShareNewestAdapter(act, new ArrayList<OrderShareResult>());
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				adapter.getList().clear();
				getListData();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getListData();
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
		lazyLoad();
	}

	private void lazyLoad() {
		adapter.addAll(CacheManage.get("shareNews_list" + status,
				OrderShareResult.class));
		adapter.notifyDataSetChanged();
		getListData();
	}

	/**
	 * 获得列表数据
	 * 
	 * @author: songbing.zhou
	 */
	private void getListData() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/GetOrderShareList");//
		httpClient.url.append("/" + status);// 状态 0最新 2精华 3推荐 4人气
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				OrderShareList response = JsonUtil.parseObject(act,
						responseInfo.result, OrderShareList.class);
				if (response != null) {
					List<OrderShareResult> tmpList = response.getResult();
					if (isSave) {
						CacheManage.remove("shareNews_list" + status);
						CacheManage.put("shareNews_list" + status, tmpList);
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
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(act, HomeBillShareDetailsActivity.class);
		bundle.putString("id", adapter.getList().get(position - 1).getId() + "");
		intent.putExtras(bundle);
		act.startActivityForResult(intent, 0);
	}
}
