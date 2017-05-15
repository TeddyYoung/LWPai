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
import com.yfzx.lwpai.entity.ChooseAddressKeyValue;

/**
 * 省市区选择 适配器
 * 
 * @author Gy
 */
public class ChooseAddressAdapter extends
		BaseListAdapter<ChooseAddressKeyValue> {

	public ChooseAddressAdapter(Activity context,
			List<ChooseAddressKeyValue> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_chooseaddress, null);
		}

		TextView address = ViewHolder
				.get(convertView, R.id.tvItemChooseAddress);
		TextView id = ViewHolder.get(convertView, R.id.tvItemChooseAddressId);
		ChooseAddressKeyValue chooseAddressKeyValue = list.get(position);
		address.setText(chooseAddressKeyValue.getValue());
		id.setText(chooseAddressKeyValue.getKey());
		return convertView;
	}
}
