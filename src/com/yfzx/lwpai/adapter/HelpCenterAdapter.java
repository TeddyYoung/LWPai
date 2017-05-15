package com.yfzx.lwpai.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.MineHelpContentActivity;
import com.yfzx.lwpai.entity.HelperEntity.ResultEntity;

/**
 * 帮助中心适配器
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
public class HelpCenterAdapter extends BaseExpandableListAdapter {

	private Map<ResultEntity, List<ResultEntity>> map;
	private List<ResultEntity> parent;
	private Context context;
	int[] img = { R.drawable.personal_903x, R.drawable.personal_913x,
			R.drawable.personal_923x, R.drawable.personal_933x};

	public HelpCenterAdapter(Context context,
			Map<ResultEntity, List<ResultEntity>> map, List<ResultEntity> list) {
		this.map = map;
		this.parent = list;
		this.context = context;
	}

	/**
	 * 得到子item需要关联的数据
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ResultEntity key = parent.get(groupPosition);
		return (map.get(key).get(childPosition));
	}

	/**
	 * 得到子item的ID
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * 设置子item的组件
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final ResultEntity key = HelpCenterAdapter.this.parent
				.get(groupPosition);
		final ResultEntity data = map.get(key).get(childPosition);
		LinearLayout childLayout = (LinearLayout) View.inflate(context,
				R.layout.item_helpcenter_children, null);
		TextView tvTextView = (TextView) childLayout
				.findViewById(R.id.tvHelpcenterChild);
		// 点击事件
		childLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(context, MineHelpContentActivity.class);
				bundle.putString("id", data.getId());
				bundle.putString("title", data.getName());
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});

		tvTextView.setText(data.getName());
		return childLayout;
	}

	/**
	 * 获取当前父item下的子item的个数
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		ResultEntity key = parent.get(groupPosition);
		int size = map.get(key).size();
		return size;
	}

	/**
	 * 获取当前父item的数据
	 */
	@Override
	public Object getGroup(int groupPosition) {
		return parent.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return parent.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * 设置父item组件
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		LinearLayout parentLayout = (LinearLayout) View.inflate(context,
				R.layout.item_helpcenter_parent, null);
		TextView parentTextView = (TextView) parentLayout
				.findViewById(R.id.tvHelpcenterParent);
		ImageView ivHelpcenterParent = (ImageView) parentLayout
				.findViewById(R.id.ivHelpcenterParent);
		// 添加图片
		ivHelpcenterParent.setImageResource(img[groupPosition]);
		parentTextView.setText(HelpCenterAdapter.this.parent.get(groupPosition)
				.getName());
		return parentLayout;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
