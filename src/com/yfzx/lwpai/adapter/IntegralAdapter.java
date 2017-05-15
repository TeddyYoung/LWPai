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
import com.yfzx.lwpai.entity.IntegralEntity.ResultEntity;

/**
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class IntegralAdapter extends BaseListAdapter<ResultEntity> {

	public IntegralAdapter(Activity context, List<ResultEntity> list) {
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

		// Remark为空 就显示TradeType（兑换红包）
		if (data.getRemark().equals("")) {
			tvType.setText(data.getTradeType());
		} else {
			tvType.setText(data.getRemark());
		}
		if (data.getReduced().equals("0")) {
			tvNum.setText("+ " + data.getIncreased());
		} else {
			tvNum.setText("- " + data.getReduced());
		}
		tvDate.setText(data.getTradeDate());
		tvResult.setText("当前积分:" + data.getPoints());

		return convertView;
	}

}
