package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GetUserOfferByIdAdapter;
import com.yfzx.lwpai.entity.GetUserOfferById;
import com.yfzx.lwpai.entity.GetUserResultEntity;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 查看幸运码
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
@ContentView(R.layout.activity_examine_luck_code)
public class ExamineLuckCodeActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 商品名
	@ViewInject(R.id.tvName)
	private TextView tvName;
	// 次数
	@ViewInject(R.id.tvCount)
	private TextView tvCount;
	// 参与时间
	@ViewInject(R.id.tvInTime)
	private TextView tvInTime;
	// 参与组数
	@ViewInject(R.id.tvInGroup)
	private TextView tvInGroup;

	// 布局1
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;

	// 布局2
	@ViewInject(R.id.llTow)
	private LinearLayout llTow;
	// 布局3
	@ViewInject(R.id.llThree)
	private LinearLayout llThree;
	@ViewInject(R.id.listView)
	private XListView listView;

	private String id;
	private int cate;
	private int currPage = 1;
	private GetUserOfferByIdAdapter adapter;
	protected boolean isSave = true;
	/**
	 * 实体
	 */
	private List<GetUserResultEntity> tmpList;
	/**
	 * 总个数
	 */
	private String sumCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// name = bundle.getString("name");
				// index = bundle.getString("index");
				// count = bundle.getString("count");
				// 商品ID
				id = bundle.getString("id");
				// 区分红包区一元拍幸运拍
				cate = bundle.getInt("cate");
			}
		}
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		if (cate != 3) {
			tvCenter.setText("查看幸运码");
			tvInTime.setText("参与人数");
		} else {
			tvCenter.setText("查看随机码");
			tvInTime.setText("以下是您的随机码");
			tvInGroup.setVisibility(View.GONE);
		}

		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		if (cate == 3) {
			llOne.setVisibility(View.GONE);
			llTow.setVisibility(View.GONE);
			llThree.setVisibility(View.VISIBLE);

		}
		adapter = new GetUserOfferByIdAdapter(act,
				new ArrayList<GetUserResultEntity>(), cate);

		listView.setAdapter(adapter);
		// tvName.setText(name);
		// tvIndex.setText("(第" + index + "期)");
		// tvCount.setText(count);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);

		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				getUserOfferById();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getUserOfferById();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		getUserOfferById();
	}

	/**
	 * 
	 * @author: yizhong.xu
	 */
	private void getUserOfferById() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserOfferById");
		httpClient.url.append("/" + cate);// 幸运拍 红包区 一元拍
		httpClient.url.append("/" + id);//
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetUserOfferById response = JsonUtil.parseObject(act,
						responseInfo.result, GetUserOfferById.class, "");
				if (response != null) {
					tvName.setText("(第" + response.getPeriodIndex() + "期)"
							+ response.getProductName());
					tvCount.setText(response.getTotal());
					tmpList = response.getResult();
					if (isSave) {
						adapter.getList().clear();
						isSave = false;
					}
					adapter.addAll(tmpList);
					adapter.notifyDataSetChanged();
					String st = response.getTotal();
					int it = Integer.parseInt(st);
					if (currPage < Helper.paging(it)) {
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

	/**
	 * 点击事件
	 * 
	 * @param view
	 */
	@OnClick({ R.id.ivLeft })
	public void OnClick(View view) {
		switch (view.getId()) {
		case R.id.ivLeft:
			finish();
			break;

		default:
			break;
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
		if (cate != 3) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			intent.setClass(act, ExamineNextLuckCodeActivity.class);
			bundle.putString("luckyguid", adapter.getList().get(position - 1)
					.getLuckyGUID());
			bundle.putInt("cate", cate);
			bundle.putString("luckyid", adapter.getList().get(position - 1)
					.getLuckyId());
			bundle.putString("offerCount", adapter.getList().get(position - 1)
					.getOfferCount());
			// 个数
			bundle.putString("sumCount", tmpList.get(position - 1)
					.getOfferCount());
			bundle.putString("productName", tvName.getText().toString());
			intent.putExtras(bundle);
			act.startActivity(intent);
		}

	}
}
