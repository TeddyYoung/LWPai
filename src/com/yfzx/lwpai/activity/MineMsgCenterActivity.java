package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.yfzx.lwpai.adapter.MsgAdapter;
import com.yfzx.lwpai.entity.MsgEntity;
import com.yfzx.lwpai.entity.MsgEntity.ResultEntity;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 消息中心
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
@ContentView(R.layout.activity_msg_center)
public class MineMsgCenterActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.xListView)
	private XListView listview;

	private MsgAdapter adapter;
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
		tvCenter.setText("消息中心");
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		adapter = new MsgAdapter(act, new ArrayList<ResultEntity>());
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
				getHelper();
			}
		});

		getHelper();
	}

	/**
	 * 获取帮助列表
	 * 
	 * @author: bangwei.yang
	 */
	private void getHelper() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/members/GetMessage/0");// 0为全部，1为收件箱，2为发件箱
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);
		httpClient.url.append("/" + currPage);
		httpClient.post(new xAllResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				MsgEntity response = JsonUtil.parseObject(act,
						responseInfo.result, MsgEntity.class);
				if (response != null) {
					List<ResultEntity> data = response.getResult();
					adapter.addAll(data);

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
	 * item点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnItemClick(R.id.xListView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle extras = new Bundle();
		extras.putString("messageId", adapter.getList().get(position-1)
				.getMessageId());
		extras.putString("messageDate", adapter.getList().get(position-1)
				.getDate());
		showActivity(act, MineMsgContentActivity.class, extras);
	}
}