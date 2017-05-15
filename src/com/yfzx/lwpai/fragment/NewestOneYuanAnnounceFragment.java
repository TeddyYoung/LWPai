package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.widget.pulltorefresh.ILoadingLayout;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshGridView;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.adapter.NewestAnnounceAdapter;
import com.yfzx.lwpai.entity.LastOpen;
import com.yfzx.lwpai.entity.LastOpen.SSQReusltEntity;
import com.yfzx.lwpai.entity.NewestResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 一元拍
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月8日
 */
public class NewestOneYuanAnnounceFragment extends BaseFragment {
	// Code
	@ViewInject(R.id.llCode)
	private View llCode;
	// 自定义gridview
	@ViewInject(R.id.pullToRefreshGridView1)
	private PullToRefreshGridView pullToRefreshGridView1;

	private boolean firstLoad = true;
	private int currPage = 1;
	private int flag = 3;
	// 适配器
	private NewestAnnounceAdapter adapter;

	// static NewestAnnounceFragment newInstance(int flag) {
	// Bundle bundle = new Bundle();
	// bundle.putInt("flag", flag);
	// NewestAnnounceFragment fragment = new NewestAnnounceFragment();
	// fragment.setArguments(bundle);
	// return fragment;
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_luck_auction, container,
				false);
		// 获得索引值
		Bundle bundle = getArguments();
		if (bundle != null) {
			flag = bundle.getInt("flag");
		}

		// 绑定界面UI
		ViewUtils.inject(this, view);
		initWidget();
		initDate();
		return view;
	}

	/**
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		adapter = new NewestAnnounceAdapter(act, new ArrayList<NewestResult>());
		pullToRefreshGridView1.setAdapter(adapter);
		pullToRefreshGridView1
				.setOnRefreshListener(new OnRefreshListener2<GridView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						String label = DateUtils.formatDateTime(
								act.getApplicationContext(),
								System.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);
						currPage = 1;
						firstLoad = true;
						lastOpen();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						currPage++;
						lastOpen();
					}
				});

		ILoadingLayout startLabels = pullToRefreshGridView1
				.getLoadingLayoutProxy();
		startLabels.setPullLabel("下拉刷新");// 刚下拉时，显示的提示
		startLabels.setRefreshingLabel("正在加载...");// 刷新时
		startLabels.setReleaseLabel("松开刷新数据");// 下来达到一定距离时，显示的提示

		llCode.setVisibility(View.GONE);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		lazyLoad();
	}

	/**
	 * 加载缓存中的数据
	 * 
	 * @author: bangwei.yang
	 */
	private void lazyLoad() {
		adapter.addAll(CacheManage.get("oneyuan", NewestResult.class));
		adapter.notifyDataSetChanged();
		lastOpen();
	}

	/**
	 * 幸运拍
	 * 
	 * @author: yizhong.xu
	 */
	private void lastOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/LastOpen/GetProductList");// 方法
		httpClient.url.append("/" + 3);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				LastOpen response = JsonUtil.parseObject(act,
						responseInfo.result, LastOpen.class);

				if (response != null) {
					SSQReusltEntity reuslts = response.getSSQReuslt();
					List<NewestResult> tmpList = response.getResult();

					// 第一次加载
					if (firstLoad) {
						// 如果缓存为空，存入缓存。不为空，清除，添加新的数据
						CacheManage.remove("oneyuan");
						// 最新10条数据，存入缓存
						CacheManage.put("oneyuan", tmpList);
						// 清空已加载的缓存数据
						adapter.getList().clear();
						firstLoad = false;
					}

					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

					if (currPage < Helper.paging(response.getTotal())) {// 不足10条数据，说明没有下一页
						pullToRefreshGridView1.setMode(Mode.BOTH);
					} else {
						pullToRefreshGridView1.setMode(Mode.PULL_FROM_START);
					}

					pullToRefreshGridView1.onRefreshComplete();
				} else {
					ToolToast.showShort("已经没有数据可以刷新");
					pullToRefreshGridView1.setMode(Mode.DISABLED);
				}
			}
		});

	}

	/**
	 * 点击跳转
	 * 
	 * @author: yizhong.xu
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick(R.id.pullToRefreshGridView1)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Intent intent = new Intent();
		// Bundle bundle = new Bundle();
		// intent.setClass(act, AuctionBeginActivity.class);
		// bundle.putInt("category", flag);
		// bundle.putSerializable("goodDetailType", adapter.getList()
		// .get(position).get);
		// intent.putExtras(bundle);
		// act.startActivityForResult(intent, 0);
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		// intent.setClass(act, GoodsOneDetailActivity.class);
		// bundle.putInt("category", 2);
		// bundle.putInt("flag", 3);
		intent.setClass(act, GoodsLuckDetailActivity.class);
		bundle.putInt("category", 2);// 传结束
		bundle.putInt("type", 3);// 一元拍
		bundle.putString("goodsId", adapter.getList().get(position)
				.getOnePurchasePId());
		bundle.putString("goodsPTId", adapter.getList().get(position)
				.getOnePurchasePTId());
		bundle.putInt("goodsOneID", adapter.getList().get(position)
				.getProductId());
		intent.putExtras(bundle);
		act.startActivityForResult(intent, 0);
	}
}
