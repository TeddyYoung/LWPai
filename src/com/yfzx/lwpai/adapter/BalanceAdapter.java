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
import com.yfzx.lwpai.entity.BalanceEntity.ResultEntity;

/**
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class BalanceAdapter extends BaseListAdapter<ResultEntity> {

	private int type = 0;

	public BalanceAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	public BalanceAdapter(Activity context, List<ResultEntity> list, int type) {
		super(context, list);
		this.type = type;
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

		tvType.setText(data.getTradeTypeName());
		tvNum.setText(data.getIncomeExpenses());
		tvDate.setText(data.getTradeDate());
		if (type == 0) {
			tvResult.setText("余额:" + data.getBalance());
		} else {
			tvResult.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

}
