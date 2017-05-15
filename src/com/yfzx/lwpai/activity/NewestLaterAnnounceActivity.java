package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cn.sharesdk.facebook.b;

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
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.NewestLaterAnnounceAdapter;
import com.yfzx.lwpai.entity.NewestGetLuckyWinner;
import com.yfzx.lwpai.entity.NewestGetLuckyWinner.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 最新揭晓的往期揭晓界面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月13日
 */
@ContentView(R.layout.activity_newest_later_announce)
public class NewestLaterAnnounceActivity extends BaseActivity {

	@ViewInject(R.id.listView)
	private ListView listView;

	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 适配器
	private NewestLaterAnnounceAdapter adapter;

	private int currPage = 1;
	private int type = 0;
	private int goodsProductId;

	/**
	 * 实体
	 */
	private List<ResultEntity> tmpList;

	private int status;
	private int onePurchasePTId;

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
				status = bundle.getInt("status");
				onePurchasePTId = bundle.getInt("onePurchasePTId");
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
		tvCenter.setText("往期揭晓");
		adapter = new NewestLaterAnnounceAdapter(act,
				new ArrayList<ResultEntity>(), type);
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
		if (type == 77) {// 幸运拍
			lastOpen();
		}
	}

	/**
	 * 往期揭晓幸运拍 TryAreaEntity
	 * 
	 * @author: yizhong.xu
	 */
	private void lastOpen() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/lucky/GetLuckyWinner");// 方法
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				NewestGetLuckyWinner response = JsonUtil.parseObject(act,
						responseInfo.result, NewestGetLuckyWinner.class);
				if (response != null) {

					tmpList = response.getResult();
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
		httpClient.url.append("api/tryarea/GetTryAreaWinner");// 方法
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				NewestGetLuckyWinner response = JsonUtil.parseObject(act,
						responseInfo.result, NewestGetLuckyWinner.class);
				if (response != null) {

					tmpList = response.getResult();

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
		httpClient.url.append("api/one/GetOneWinner");// 方法
		httpClient.url.append("/" + goodsProductId);// 結束公佈的中拍者
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		if (status == 2) {
			httpClient.url.append("/" + onePurchasePTId);// 第几页
		}
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				NewestGetLuckyWinner response = JsonUtil.parseObject(act,
						responseInfo.result, NewestGetLuckyWinner.class);
				if (response != null) {

					tmpList = response.getResult();

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
	@OnClick({ R.id.ivLeft })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		}
	}

	/**
	 * item点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnItemClick(R.id.listView)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		switch (type) {
		case 3:
			bundle.putString("goodsId", tmpList.get(position)
					.getOnePurchasePId());
			if (status == 2) {
				bundle.putString("goodsPTId", tmpList.get(position)
						.getOnePurchasePTId());
				bundle.putInt("category", 2);
			}
			// 类型
			bundle.putInt("type", type);
			break;
		case 55:
			bundle.putString("goodsId", tmpList.get(position).getLuckyId());
			// 类型
			bundle.putInt("type", type);
			break;
		case 56:
			bundle.putString("goodsId", tmpList.get(position)
					.getTryAreaProductId());
			// 类型
			bundle.putInt("type", type);
			break;
		default:
			break;
		}
		showActivity(act, GoodsLuckDetailActivity.class, bundle);
	}
}