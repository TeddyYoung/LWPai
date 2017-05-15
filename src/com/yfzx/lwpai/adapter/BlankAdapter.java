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
import com.yfzx.lwpai.entity.BlankEntity.ResultEntity;

/**
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class BlankAdapter extends BaseListAdapter<ResultEntity> {

	public BlankAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_blank, null);
		}
		TextView tvType = ViewHolder.get(convertView, R.id.tvType);
		TextView tvDate = ViewHolder.get(convertView, R.id.tvDate);

		ResultEntity data = list.get(position);

		tvType.setText(data.getBankName());
		tvDate.setText(data.getBankCardNumber());

		return convertView;
	}

}
