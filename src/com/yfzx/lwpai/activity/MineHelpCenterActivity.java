package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.HelpCenterAdapter;
import com.yfzx.lwpai.entity.HelperEntity;
import com.yfzx.lwpai.entity.HelperEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 帮助中心
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
@ContentView(R.layout.activity_help_center)
public class MineHelpCenterActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 标题
	@ViewInject(R.id.expandableListView)
	private ExpandableListView expandableListView;

	// 存放数据
	private Map<ResultEntity, List<ResultEntity>> map = new HashMap<ResultEntity, List<ResultEntity>>();
	private List<ResultEntity> parent = new ArrayList<ResultEntity>();
	private HelpCenterAdapter adapter;

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
		tvCenter.setText("帮助中心");
		ivLeft.setImageResource(R.drawable.top_back_black);

		// Item不收起
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {

		adapter = new HelpCenterAdapter(act, map, parent);
		expandableListView.setAdapter(adapter);

		getHelper();
	}

	/**
	 * 获取帮助列表
	 * 
	 * @author: bangwei.yang
	 */
	private void getHelper() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Helper/GetHelper");// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				HelperEntity response = JsonUtil.parseObject(act,
						responseInfo.result, HelperEntity.class);
				if (response != null) {
					List<ResultEntity> data = response.getResult();
					// 父布局数据
					for (ResultEntity result : data) {

						// Id：0表示第一级（不为0获取帮助中心详情页Id）
						if (result.getId().equals("0")) {
							map.put(result, new ArrayList<ResultEntity>());
							parent.add(result);
						}
					}
					// 子布局数据
					for (ResultEntity result : data) {
						// Id：0表示第一级（不为0获取帮助中心详情页Id）
						if (!result.getId().equals("0")) {
							Iterator iterator = map.entrySet().iterator();
							while (iterator.hasNext()) {
								Map.Entry entry = (Map.Entry) iterator.next();
								ResultEntity key = (ResultEntity) entry
										.getKey();
								List<ResultEntity> val = (List<ResultEntity>) entry
										.getValue();
								if (result.getCategoryId().equals(
										key.getCategoryId())) {
									val.add(result);
								}
							}
						}
					}

					// 默认展开
					for (int i = 0; i < map.size(); i++) {
						expandableListView.expandGroup(i);
					}
					adapter.notifyDataSetChanged();
				}
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
}