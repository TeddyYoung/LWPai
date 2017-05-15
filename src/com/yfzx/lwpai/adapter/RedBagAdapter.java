package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.RedBagEntity.ResultEntity;

/**
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-21
 */
public class RedBagAdapter extends BaseListAdapter<ResultEntity> {

	private int type = 1;// 1可使用，2已过期，3已用完

	public RedBagAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	public RedBagAdapter(Activity context, List<ResultEntity> list, int type) {
		super(context, list);
		this.type = type;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_red_bag, null);
		}

		LinearLayout llytContent = ViewHolder
				.get(convertView, R.id.llytContent);
		ImageView ivRedBag = ViewHolder.get(convertView, R.id.ivRedBag);
		TextView tvName = ViewHolder.get(convertView, R.id.tvName);
		TextView tvNum = ViewHolder.get(convertView, R.id.tvNum);
		TextView tvDetails = ViewHolder.get(convertView, R.id.tvDetails);
		TextView tvDate = ViewHolder.get(convertView, R.id.tvDate);
		View div = ViewHolder.get(convertView, R.id.div);

		ResultEntity data = list.get(position);

		tvName.setText(data.getHongBaoName());
		tvDate.setText(data.getGetTime());
		tvNum.setText(data.getParvalue() + "");
		tvDetails.setText("面额" + data.getParvalue() + "元，已使用"
				+ data.getUserdMoney() + "元");
		if (type == 2) {
			llytContent.setBackgroundResource(R.drawable.personal_42);
			ivRedBag.setImageResource(R.drawable.personal_83);
			tvNum.setTextColor(mContext.getResources().getColor(
					R.color.darkgray));
			tvName.setTextColor(mContext.getResources().getColor(R.color.black));
		} else if (type == 3) {
			llytContent.setBackgroundResource(R.drawable.personal_31);
			ivRedBag.setImageResource(R.drawable.personal_83);
			tvNum.setTextColor(mContext.getResources().getColor(
					R.color.darkgray));
			tvName.setTextColor(mContext.getResources().getColor(R.color.black));
		} else {
			div.setBackgroundColor(mContext.getResources().getColor(
					R.color.reds));
		}

		return convertView;
	}
}
