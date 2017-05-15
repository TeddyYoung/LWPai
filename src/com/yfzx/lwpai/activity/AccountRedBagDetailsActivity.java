package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.RedBagDetailsAdapter;
import com.yfzx.lwpai.entity.RedBagDetailsEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的红包详情
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-22
 */
@ContentView(R.layout.activity_account_red_bag_details)
public class AccountRedBagDetailsActivity extends BaseActivity {
	// back
	@ViewInject(R.id.llytContent)
	private LinearLayout llytContent;

	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.xListView)
	private XListView listview;
	// 红包类型
	@ViewInject(R.id.tvName)
	private TextView tvName;
	// 总额
	@ViewInject(R.id.tvTotal)
	private TextView tvTotal;
	// 剩余
	@ViewInject(R.id.tvSurplus)
	private TextView tvSurplus;
	// 已使用
	@ViewInject(R.id.tvUsed)
	private TextView tvUsed;

	private RedBagDetailsAdapter adapter;

	// private int currPage = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		initWidget();
		initData();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		tvCenter.setText("红包使用明细");
		ivLeft.setImageResource(R.drawable.top_back_black);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			getHongBaoList(bundle.getString("urlId"));
		}

	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		adapter = new RedBagDetailsAdapter(act,
				new ArrayList<RedBagDetailsEntity.ResultEntity>());
		listview.setAdapter(adapter);
		listview.setPullLoadEnable(false);
		listview.setPullRefreshEnable(false);
		listview.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {

			}

			@Override
			public void onLoadMore() {
				// currPage++;
				// getOrderShare();
			}
		});

	}

	/**
	 * 获取帮助列表
	 * 
	 * @author: bangwei.yang
	 */
	private void getHongBaoList(String urlId) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetHongBaoListById");// 0为全部，1为收件箱，2为发件箱
		httpClient.url.append("/" + urlId);
		// httpClient.url.append("/" + CommonGlobal.PAGESIZE);
		// httpClient.url.append("/" + currPage);
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				RedBagDetailsEntity response = JsonUtil.parseObject(act,
						responseInfo.result, RedBagDetailsEntity.class);
				if (response != null) {
					llytContent.setVisibility(View.VISIBLE);

					adapter.addAll(response.getResult());
					// 设置头部信息
					tvName.setText(response.getHongBaoName() + ":");
					tvTotal.setText(response.getParvalue() + "元");
					tvSurplus.setText(response.getBalance() + "元");
					tvUsed.setText(Integer.valueOf(response.getParvalue())
							- Integer.valueOf(response.getBalance()) + "元");

					// 分页
					// if (Helper.paging(response.getTotal()) > currPage) {
					// listview.setPullLoadEnable(true);
					// } else {
					// listview.setPullLoadEnable(false);
					// }
				}

				listview.stopLoadMore();
				listview.stopRefresh();
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				listview.stopLoadMore();
				listview.stopRefresh();
			}

		});

	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		}
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
	@OnItemClick(R.id.xListView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(act, GoodsLuckDetailActivity.class);
		// 区分幸运拍 红包区一元拍
		bundle.putInt("type", 56);
		// 判断有没有商品ID
		if (!adapter.getList().get(position - 1).getTryAreaProductId()
				.equals("0")) {
			bundle.putString("goodsId", adapter.getList().get(position - 1)
					.getTryAreaProductId());
			bundle.putString("category", "0");
			intent.putExtras(bundle);
			act.startActivityForResult(intent, 0);
		}

	}
}