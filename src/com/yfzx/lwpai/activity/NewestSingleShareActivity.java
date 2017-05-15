package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GetOrderShareAdapter;
import com.yfzx.lwpai.entity.GetOrderShareEntity;
import com.yfzx.lwpai.entity.GetOrderShareEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 晒单分享
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月16日
 */
@ContentView(R.layout.activity_newest_later_announce)
public class NewestSingleShareActivity extends BaseActivity {
	@ViewInject(R.id.listView)
	private ListView listView;

	@ViewInject(R.id.ivRight)
	private ImageView ivRight;

	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 适配器
	private GetOrderShareAdapter adapter;

	private int currPage = 1;
	private int type = 0;
	private int goodsProductId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// goodSysId = bundle.getString("goodtype");
				type = bundle.getInt("type");
				goodsProductId = bundle.getInt("goodsProductId");

			}
		}
		initWidget();
		initData();

	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
//		ivRight.setImageResource(R.drawable.top_home_page);
		tvCenter.setText("晒单分享");
		adapter = new GetOrderShareAdapter(act, new ArrayList<ResultEntity>());

		listView.setAdapter(adapter);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initData() {
		if (type == 55) {// 幸运拍
			lastOpen();
		}
		if (type == 56)// 红包区
		{
			lastTryAreaOpen();
		}
		if (type == 3)// 一元拍
		{
			OneYuanOpen();
		}
		if (type == 77)// 趣味拍
		{
			lastOpen();
		}

	}

	/**
	 * 往期揭晓幸运拍
	 * 
	 * @author: yizhong.xu
	 */
	private void lastOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/GetOrderShare");// 方法
		httpClient.url.append("/" + 55);// 結束公佈的中
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetOrderShareEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetOrderShareEntity.class);
				if (response != null) {

					List<ResultEntity> tmpList = response.getResult();

					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

				}
			}
		});

	}

	/**
	 * 往期揭晓红包区
	 * 
	 * @author: yizhong.xu
	 */
	private void lastTryAreaOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/GetOrderShare");// 方法
		httpClient.url.append("/" + 56);// 結束公佈的中拍者
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetOrderShareEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetOrderShareEntity.class);
				if (response != null) {

					List<ResultEntity> tmpList = response.getResult();

					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

				}
			}
		});

	}

	/**
	 * 往期揭晓一元拍
	 * 
	 * @author: yizhong.xu
	 */
	private void OneYuanOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/GetOrderShare");// 方法
		httpClient.url.append("/" + 3);// 結束公佈的中拍者
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetOrderShareEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetOrderShareEntity.class);
				if (response != null) {

					List<ResultEntity> tmpList = response.getResult();
					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();

				}
			}
		});

	}

	/**
	 * 点击
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.ivRight })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		case R.id.ivRight: {
//			finish();
//			MainActivity.clickMenu(1);
			break;
		}
		}
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
	@OnItemClick(R.id.listView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(act, HomeBillShareDetailsActivity.class);
		bundle.putString("id", adapter.getList().get(position).getId() + "");
		intent.putExtras(bundle);
		act.startActivityForResult(intent, 0);
	}
}
