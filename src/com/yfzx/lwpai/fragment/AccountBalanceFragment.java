package com.yfzx.lwpai.fragment;

import java.util.ArrayList;

import android.R.integer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.BalanceAdapter;
import com.yfzx.lwpai.entity.BalanceEntity;
import com.yfzx.lwpai.entity.BalanceEntity.ResultEntity;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 账户金额
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class AccountBalanceFragment extends BaseFragment {

	private XListView listView;

	private View mFragmentView;
	private BalanceAdapter adapter;
	private int currPage = 1;
	private int type;
	// 总金额
	private TextView tvBalance;
	// 提现s
	private TextView tvRemind;

	public static AccountBalanceFragment newInstance(int type,
			TextView tvBalance, TextView tvRemind) {
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		AccountBalanceFragment fragment = new AccountBalanceFragment(tvBalance,
				tvRemind);
		fragment.setArguments(bundle);
		return fragment;
	}

	/**
	 * @param type
	 *            // 添加切换的页面 ,全部0，收入1，支出2
	 * @param tvBalance
	 *            总金额
	 * @param tvRemind
	 *            提现
	 */
	public AccountBalanceFragment(TextView tvBalance, TextView tvRemind) {
		this.tvBalance = tvBalance;
		this.tvRemind = tvRemind;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mFragmentView == null) {
			mFragmentView = inflater.inflate(R.layout.fragment_account_balance,
					container, false);
			listView = (XListView) mFragmentView.findViewById(R.id.listView);
			// 获得索引值
			Bundle bundle = getArguments();
			if (bundle != null) {
				type = bundle.getInt("type");
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
		adapter = new BalanceAdapter(act, new ArrayList<ResultEntity>());
		listView.setAdapter(adapter);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(false);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getUserBalance();
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
		getUserBalance();
	}

	/**
	 * 账户金额
	 * 
	 * @author: bangwei.yang
	 */
	private void getUserBalance() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserBalance");// 账目明细
		httpClient.url.append("/" + 1);// 1表示金额，2表示充值
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.url.append("/" + type);// status全部0，收入1，支出2
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BalanceEntity response = JsonUtil.parseObject(act,
						responseInfo.result, BalanceEntity.class);
				if (response != null) {
					// 总金额
					tvBalance.setText(response.getUserBalance() + "");
					// 提现
					tvRemind.setText(response.getUserableBalance() + "");
					adapter.addAll(response.getResult());
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
}
