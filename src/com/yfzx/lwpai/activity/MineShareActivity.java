package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MineShareAdapter;
import com.yfzx.lwpai.entity.GetOrderShareListEntity;
import com.yfzx.lwpai.entity.GetOrderShareListResult;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的晒单
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-19
 */
@ContentView(R.layout.activity_mine_share)
public class MineShareActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.xListView)
	private XListView listview;
	// 页面数
	@ViewInject(R.id.tvCurrPage)
	private TextView tvCurrPage;
	// 总条数
	@ViewInject(R.id.tvTotal)
	private TextView tvTotal;

	private MineShareAdapter adapter;
	private int currPage = 1;

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
		tvCenter.setText("我的晒单");
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		adapter = new MineShareAdapter(act,
				new ArrayList<GetOrderShareListResult>());
		listview.setAdapter(adapter);
		listview.setDivider(null);
		listview.setPullLoadEnable(false);
		listview.setPullRefreshEnable(false);
		listview.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {

			}

			@Override
			public void onLoadMore() {
				currPage++;
				getOrderShare();
			}
		});

		getOrderShare();
	}

	/**
	 * 获取帮助列表
	 * 
	 * @author: bangwei.yang
	 */
	private void getOrderShare() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetOrderShareList");// 0为全部，1为收件箱，2为发件箱
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);
		httpClient.url.append("/" + currPage);
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetOrderShareListEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetOrderShareListEntity.class);
				if (response != null) {
					List<GetOrderShareListResult> data = response.getResult();
					adapter.addAll(data);

					// 设置顶部内容
//					tvCurrPage.setText(Integer.toString(currPage));
					tvTotal.setText(Integer.toString(response.getTotal()));
					// 分页
					if (Helper.paging(response.getTotal()) > currPage) {
						listview.setPullLoadEnable(true);
					} else {
						listview.setPullLoadEnable(false);
					}
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
		intent.setClass(act, HomeBillShareDetailsActivity.class);
		bundle.putString("id", adapter.getList().get(position - 1).getId() + "");
		intent.putExtras(bundle);
		act.startActivityForResult(intent, 0);
	}
}