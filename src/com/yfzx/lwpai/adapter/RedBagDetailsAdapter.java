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
import com.yfzx.lwpai.entity.RedBagDetailsEntity.ResultEntity;

/**
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class RedBagDetailsAdapter extends BaseListAdapter<ResultEntity> {

	public RedBagDetailsAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_balance, null);
		}
		TextView tvType = ViewHolder.get(convertView, R.id.tvType);
		TextView tvNum = ViewHolder.get(convertView, R.id.tvNum);
		TextView tvDate = ViewHolder.get(convertView, R.id.tvDate);
		TextView tvResult = ViewHolder.get(convertView, R.id.tvResult);

		ResultEntity data = list.get(position);

		// 第0期不显示的判断
		if (!data.getPeriodIndex().equals("0")) {
			tvType.setText("第" + data.getPeriodIndex() + "期"
					+ data.getProductName());
		} else {
			tvType.setText(data.getProductName());
		}

		tvNum.setText(data.getUsedMoney());
		tvDate.setText(data.getUsedTime());
		tvResult.setVisibility(View.INVISIBLE);

		return convertView;
	}

}
