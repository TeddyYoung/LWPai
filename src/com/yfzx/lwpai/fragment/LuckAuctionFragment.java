package com.yfzx.lwpai.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.LuckAuctionAdapter;
import com.yfzx.lwpai.entity.Good;

/**
 * 幸运拍
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月26日
 */
public class LuckAuctionFragment extends BaseFragment {
	// 表格显示
	@ViewInject(R.id.gridview)
	private GridView gridview;
	// 适配器
	private LuckAuctionAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_luck_s_auction,
				container, false);
		// 绑定界面UI
		ViewUtils.inject(this, view);
		initWidget();
		initDate();
		return view;
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
		adapter.removeAll();

		String[] list = { "242000", "59", "45", "52", "48", "69", "38", "35" };
		int[] img = { R.drawable.iphone, R.drawable.blue, R.drawable.oona,
				R.drawable.suit, R.drawable.dabai, R.drawable.power,
				R.drawable.camera, R.drawable.headset };

		for (int i = 0; i < list.length; i++) {
			Good good = new Good();
			good.setName(list[i]);
			good.setImgId(img[i]);
			adapter.add(good);
		}

		adapter.notifyDataSetInvalidated();
	}

	/**
	 * 初始化页面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		adapter = new LuckAuctionAdapter(act, new ArrayList<Good>());
		gridview.setAdapter(adapter);
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
	@OnItemClick(R.id.gridview)
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			break;
		default:
			break;
		}
	}
}
