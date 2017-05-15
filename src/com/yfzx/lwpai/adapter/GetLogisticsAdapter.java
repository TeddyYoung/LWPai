package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.GetLogisticsResult;

public class GetLogisticsAdapter extends BaseListAdapter<GetLogisticsResult> {


	public GetLogisticsAdapter(Activity context, List<GetLogisticsResult> list) {
		super(context, list);
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_mine_logistics, null);
		}

		GetLogisticsResult getLogistics = list.get(position);

		// 时间
		TextView tvTime = ViewHolder.get(convertView, R.id.tvTime);
		// 内容
		TextView tvContext = ViewHolder.get(convertView, R.id.tvContext);
		tvTime.setText(getLogistics.getTime());
		tvContext.setText(getLogistics.getContext());

		return convertView;
	}

}
