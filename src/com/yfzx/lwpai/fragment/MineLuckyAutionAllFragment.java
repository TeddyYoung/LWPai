package com.yfzx.lwpai.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.widget.xlistview.XListView;
import com.yfzx.library.widget.xlistview.XListView.IXListViewListener;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.MineLuckAutionAdapter;
import com.yfzx.lwpai.entity.MineLuckEntity;
import com.yfzx.lwpai.entity.MineLuckyAutionEntity;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 乐拍记录 幸运拍
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月20日
 */
public class MineLuckyAutionAllFragment extends BaseFragment {

	@ViewInject(R.id.listView)
	private XListView listView;

	@ViewInject(R.id.llytNot)
	private LinearLayout llytNot;

	private int currPage = 1;

	private MineLuckAutionAdapter adapter;
	protected boolean isSave = true;
	private int type = 0;
	private int cate = 0;
	private String users;

	public static MineLuckyAutionAllFragment newInstance(int cate, int type) {
		Bundle bundle = new Bundle();
		bundle.putInt("cate", cate);
		bundle.putInt("type", type);
		MineLuckyAutionAllFragment fragment = new MineLuckyAutionAllFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_luckt_getlucky,
				container, false);

		// 绑定界面UI
		ViewUtils.inject(this, view);

		Bundle bundle = getArguments();
		if (bundle != null) {
			cate = bundle.getInt("cate");
			type = bundle.getInt("type");
		}
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
		User user = new User();
		users = user.getRealName();
		adapter = new MineLuckAutionAdapter(act,
				new ArrayList<MineLuckEntity>());
		listView.setAdapter(adapter);
		adapter.setCate(cate);
		adapter.setType(type);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(true);

		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				currPage = 1;
				isSave = true;
				// adapter.getList().clear();
				getListData();
			}

			@Override
			public void onLoadMore() {
				currPage++;
				getListData();
			}
		});

	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		lazyLoad();
	}

	private void lazyLoad() {
		// adapter.addAll(CacheManage.get("luckys_list" + cate + "type" +
		// "users",
		// MineLuckEntity.class));
		// adapter.notifyDataSetChanged();
		getListData();
	}

	/**
	 * 
	 * @author: yizhong.xu
	 */
	private void getListData() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserOfferRecord");
		httpClient.url.append("/" + cate);// 55幸运拍 56 红包区 3一元拍 77趣味拍
		httpClient.url.append("/" + type);// 状态 0全部1进行中 2已结束
		httpClient.url.append("/" + CommonGlobal.PAGESIZE);// 每页条数
		httpClient.url.append("/" + currPage);// 第几页
		httpClient.post(new xResopnse() {

			private List<MineLuckEntity> tmpList;

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				MineLuckyAutionEntity response = JsonUtil.parseObject(act,
						responseInfo.result, MineLuckyAutionEntity.class, "");
				if (response != null) {
					tmpList = response.getResult();
					// adapter.notifyDataSetChanged();
					// if (isSave) {
					// CacheManage.remove("luckys_list" + cate + "type"
					// + "users");
					// CacheManage.put(
					// "luckys_list" + cate + "type" + "users",
					// tmpList);
					// adapter.getList().clear();
					// isSave = false;
					// }
					if (currPage == 1) {
						adapter.setList(tmpList);
					} else {
						adapter.addAll(tmpList);
					}

					adapter.notifyDataSetChanged();

					if (currPage < Helper.paging(response.getTotal())) {
						listView.setPullLoadEnable(true);
					} else {
						listView.setPullLoadEnable(false);
					}
				} else {
					llytNot.setVisibility(View.VISIBLE);
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
		// Intent intent = new Intent();
		// Bundle bundle = new Bundle();
		// intent.setClass(getActivity(), AuctionBeginActivity.class);//
		// NewsDetailsActivity
		// bundle.putInt("category", flag); // 1为从商场的入口
		// bundle.putSerializable("goodDetailType", adapter.getList()
		// .get(position));
		// intent.putExtras(bundle);
		// act.startActivityForResult(intent, 0);
	}
}
