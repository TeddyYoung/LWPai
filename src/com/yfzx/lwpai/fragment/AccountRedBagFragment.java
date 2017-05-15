package com.yfzx.lwpai.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.AccountRedBagDetailsActivity;
import com.yfzx.lwpai.adapter.RedBagAdapter;
import com.yfzx.lwpai.entity.RedBagEntity;
import com.yfzx.lwpai.entity.RedBagEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的红包
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class AccountRedBagFragment extends BaseFragment {

	private XListView listView;

	private View mFragmentView;
	private LinearLayout llytRedNull;
	private RedBagAdapter adapter;
	// private int currPage = 1;
	private int type;

	public static AccountRedBagFragment newInstance(int type) {
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		AccountRedBagFragment fragment = new AccountRedBagFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mFragmentView == null) {
			mFragmentView = inflater.inflate(R.layout.fragment_account_balance,
					container, false);
			listView = (XListView) mFragmentView.findViewById(R.id.listView);
			LinearLayout llytContent = (LinearLayout) mFragmentView
					.findViewById(R.id.llytContent);
			llytRedNull = (LinearLayout) mFragmentView
					.findViewById(R.id.llytRedNull);
			listView.setDivider(null);
			// 点击事件
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					try {
						ResultEntity data = adapter.getList().get(position - 1);
						if (!data.getUrlId().equals("0")) {// 如果UrlId=0表示没用过就不需要跳转到详细页
							Bundle bundle = new Bundle();
							bundle.putString("urlId", data.getUrlId());
							showActivity(act,
									AccountRedBagDetailsActivity.class, bundle);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			// 获得索引值
			Bundle bundle = getArguments();
			if (bundle != null) {
				type = bundle.getInt("type");
				if (type == 1) {
					llytContent.setVisibility(View.VISIBLE);
				}
			}
		}

		// 因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
		ViewGroup parent = (ViewGroup) mFragmentView.getParent();
		if (parent != null) {
			parent.removeView(mFragmentView);
		}

		return mFragmentView;
	}

	/*
	 * 初始化 (non-Javadoc)
	 * 
	 * @author: bangwei.yang
	 * 
	 * @see com.yfzx.meipei.BaseFragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initWidget();
		initDate();
	}

	/**
	 * 初始化页面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		adapter = new RedBagAdapter(act, new ArrayList<ResultEntity>(), type);
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(false);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
			}

			@Override
			public void onLoadMore() {
				// currPage++;
				getHongBao();
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
		getHongBao();
	}

	private void getHongBao() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetHongBaoList");
		// httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		// httpClient.url.append("/" + currPage);// 第几页
		httpClient.url.append("/" + type);// 1可使用，2已过期，3已用完
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				RedBagEntity response = JsonUtil.parseObject(act,
						responseInfo.result, RedBagEntity.class, "");//不做提示
				if (response != null) {
					adapter.addAll(response.getResult());
					adapter.notifyDataSetChanged();

					// if (currPage < Helper.paging(response.getTotal())) {
					// listView.setPullLoadEnable(true);
					// } else {
					// listView.setPullLoadEnable(false);
					// }
				} else {
					llytRedNull.setVisibility(View.VISIBLE);
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

}
