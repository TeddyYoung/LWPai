package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GetLogisticsAdapter;
import com.yfzx.lwpai.entity.GetLogisticsEntity;
import com.yfzx.lwpai.entity.GetLogisticsResult;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 
 * 物流查询
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-2
 */
@ContentView(R.layout.activity_mine_logistics)
public class MineLogisticsActivity extends BaseActivity {

	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 物流单号
	@ViewInject(R.id.tvCodenumber)
	private TextView tvCodenumber;
	// 状态
	@ViewInject(R.id.tvState)
	private TextView tvState;
	// 公司
	@ViewInject(R.id.tvCompanytype)
	private TextView tvCompanytype;
	// 物流列表
	@ViewInject(R.id.listView)
	private XListView listView;

	private GetLogisticsAdapter adapter;
	private String orderId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: songbing.zhou
	 */
	private void initWidget() {
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		tvCenter.setText("物流查询");
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				GetLogistics();
			}

			@Override
			public void onLoadMore() {
				GetLogistics();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: songbing.zhou
	 */
	private void initDate() {
		adapter = new GetLogisticsAdapter(act,
				new ArrayList<GetLogisticsResult>());
		listView.setAdapter(adapter);
		Bundle bundle = getIntent().getExtras();
		orderId = bundle.getString("orderId");
		GetLogistics();
	}

	/**
	 * 获得物流数据
	 * 
	 * @author: songbing.zhou
	 */
	private void GetLogistics() {

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetLogistics");// 物流
		httpClient.url.append("/" + orderId);// 订单号
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetLogisticsEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetLogisticsEntity.class);// 不做提示
				if (response != null) {
		
					if (response.getResult().getStatus() != null) {
						if (response.getResult().getStatus().equals("200")) {
							tvCodenumber.setText("物流单号："
									+ Html.fromHtml("<font color='"
											+ act.getResources().getColor(
													R.color.btn_orange) + "'>"
											+ response.getResult().getNu()
											+ "</font>"));

							// 物流状态
							String state = response.getResult().getState();
							if (state != null) {
								if (state.equals("0")) {
									tvState.setText("正在运输途中");
								} else if (state.equals("1")) {
									tvState.setText("已揽件");
								} else if (state.equals("2")) {
									tvState.setText("疑难件");
								} else if (state.equals("3")) {
									tvState.setText("已签收");
								} else if (state.equals("4")) {
									tvState.setText("客户退签");
								} else if (state.equals("5")) {
									tvState.setText("派件中");
								} else if (state.equals("6")) {
									tvState.setText("客户退回");
								}
							}
							tvCompanytype.setText("配送公司："
									+ response.getResult().getCom());
							// 清空列表
							adapter.getList().clear();
							List<GetLogisticsResult> tmpList = response
									.getResult().getData();
							adapter.addAll(tmpList);
							adapter.notifyDataSetChanged();
						} else {
							// 失败的提示
							ToolToast.showShort(response.getResult().getMessage());
						}
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
	 * 点击事件
	 * 
	 * @author: songbing.zhou
	 * @param v
	 */
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: // 返回
			finish();
			break;
		}
	}
}
