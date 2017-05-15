package com.yfzx.lwpai.activity;

import java.util.ArrayList;

import android.os.Bundle;
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
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
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
 * 充值记录
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
@ContentView(R.layout.activity_mine_share)
public class AccountRechargeRecordActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 标题
	@ViewInject(R.id.tvRight)
	private TextView tvRight;

	@ViewInject(R.id.xListView)
	private XListView listview;
	// 页面数
	@ViewInject(R.id.tvCurrPage)
	private TextView tvCurrPage;
	// 总条数
	@ViewInject(R.id.tvTotal)
	private TextView tvTotal;
	// 其他内容
	@ViewInject(R.id.textView3)
	private TextView textView3;
	@ViewInject(R.id.textView1)
	private TextView textView1;

	private BalanceAdapter adapter;
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
		tvCenter.setText("充值记录");
		ivLeft.setImageResource(R.drawable.top_back_black);
		tvRight.setText("充值");

		// 设置顶部内容
		tvCurrPage.setVisibility(View.GONE);
		textView3.setVisibility(View.GONE);
		textView1.setText("总金额：");
		tvTotal.setTextColor(getResources().getColor(R.color.btn_orange));
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			tvTotal.setText(bundle.getString("balance"));
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		adapter = new BalanceAdapter(act, new ArrayList<ResultEntity>(), 1);// 充值记录没有余额
		listview.setAdapter(adapter);
		listview.setPullLoadEnable(false);
		listview.setPullRefreshEnable(false);
		listview.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {

			}

			@Override
			public void onLoadMore() {
				currPage++;
				getUserBalance();
			}
		});

		getUserBalance();
	}

	/**
	 * 充值记录
	 * 
	 * @author: bangwei.yang
	 */
	private void getUserBalance() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetUserBalance/2");// 1账号余额(status全部0，收入1，支出2)，2充值记录(不用传递status)
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);
		httpClient.url.append("/" + currPage);
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BalanceEntity response = JsonUtil.parseObject(act,
						responseInfo.result, BalanceEntity.class);
				if (response != null) {
					adapter.addAll(response.getResult());

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
	@OnClick({ R.id.ivLeft, R.id.tvRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.tvRight:
			showActivity(act, RechargeActivity.class);
			break;
		}
	}

}