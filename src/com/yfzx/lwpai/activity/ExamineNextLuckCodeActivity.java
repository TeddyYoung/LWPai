package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yfzx.library.widget.pulltorefresh.ILoadingLayout;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.yfzx.library.widget.pulltorefresh.PullToRefreshGridView;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.GetUserOfferByGuidAdapter;
import com.yfzx.lwpai.entity.GetUserByGuidResultEntity;
import com.yfzx.lwpai.entity.GetUserOfferByGuid;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 3级幸运码
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
@ContentView(R.layout.activity_examine_next_luck_code)
public class ExamineNextLuckCodeActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 商品名
	@ViewInject(R.id.tvName)
	private TextView tvName;
	// 组数
	@ViewInject(R.id.tvOfferCount)
	private TextView tvOfferCount;
	// 界面公用
	@ViewInject(R.id.llOne)
	private LinearLayout llOne;
	@ViewInject(R.id.llTow)
	private LinearLayout llTow;
	@ViewInject(R.id.llThree)
	private LinearLayout llThree;

	// 自定义gridview
	@ViewInject(R.id.pullToRefreshGridView1)
	private PullToRefreshGridView pullToRefreshGridView1;
	private String name;
	private String index;
	private String count;
	private String id;
	private int cate;
	private int currPage = 1;
	private GetUserOfferByGuidAdapter adapter;
	protected boolean isSave = true;
	private String luckyguid;
	private String luckyid;
	private String sumCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				luckyid = bundle.getString("luckyid");// 商品ID
				luckyguid = bundle.getString("luckyguid");
				// 区分红包区一元拍幸运拍
				cate = bundle.getInt("cate");
				tvOfferCount.setText(bundle.getString("offerCount"));// 几组
				tvName.setText(bundle.getString("productName"));// 描述
				sumCount = bundle.getString("sumCount");
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
		tvCenter.setText("查看幸运码");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);

		adapter = new GetUserOfferByGuidAdapter(act,
				new ArrayList<GetUserByGuidResultEntity>());

		pullToRefreshGridView1.setAdapter(adapter);
		if (cate != 56) {
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
							isSave = true;
							getUserOfferById();
						}

						@Override
						public void onPullUpToRefresh(
								PullToRefreshBase<GridView> refreshView) {
							currPage++;
							getUserOfferById();
						}
					});

			ILoadingLayout startLabels = pullToRefreshGridView1
					.getLoadingLayoutProxy();
			startLabels.setPullLabel("下拉刷新");// 刚下拉时，显示的提示
			startLabels.setRefreshingLabel("正在加载...");// 刷新时
			startLabels.setReleaseLabel("松开刷新数据");// 下来达到一定距离时，显示的提示
		}else {
			pullToRefreshGridView1.setMode(Mode.DISABLED);
		}
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
		httpClient.url.append("api/members/GetUserOfferByGuid");
		httpClient.url.append("/" + cate);// 幸运拍 红包区 一元拍
		httpClient.url.append("/" + luckyid);//
		httpClient.url.append("/" + luckyguid);//
		if (cate != 56) {
			httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		} else {
			httpClient.url.append("/" + 0);// 全部条数
		}
		httpClient.url.append("/" + currPage);// 第几页

		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetUserOfferByGuid response = JsonUtil.parseObject(act,
						responseInfo.result, GetUserOfferByGuid.class, "");
				if (response != null) {
					// tvName.setText(response.getProductName());
					// tvIndex.setText("(第" + response.getPeriodIndex() + "期)");
					// tvCount.setText(response.getTotal());
					List<GetUserByGuidResultEntity> tmpList = response
							.getResult();
					if (isSave) {
						// 清空已加载的缓存数据
						// adapter.getList().clear();
						isSave = false;
					}
					if (currPage == 1) {
						adapter.setList(tmpList);
					} else {
						adapter.addAll(tmpList);
					}

					adapter.notifyDataSetChanged();
					if (cate != 56) {
						if (currPage < Helper.paging(Integer.parseInt(sumCount))) {// 不足10条数据，说明没有下一页
							pullToRefreshGridView1.setMode(Mode.BOTH);
						} else {
							pullToRefreshGridView1
									.setMode(Mode.PULL_FROM_START);
						}
					}

					pullToRefreshGridView1.onRefreshComplete();
				} else {
					ToolToast.showShort("已经没有数据可以刷新");
					pullToRefreshGridView1.setMode(Mode.DISABLED);
				}
			}

			@Override
			public void onStart() {
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				pullToRefreshGridView1.setMode(Mode.DISABLED);
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

}
