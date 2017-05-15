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
import com.yfzx.lwpai.entity.GetUserByGuidResultEntity;

/**
 * 查看幸运码3级页面
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年8月16日
 */
public class GetUserOfferByGuidAdapter extends
		BaseListAdapter<GetUserByGuidResultEntity> {
	public GetUserOfferByGuidAdapter(Activity context,
			List<GetUserByGuidResultEntity> list) {
		super(context, list);
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_examine_next_luck_code_three, null);
		}

		TextView tvNumberss = ViewHolder.get(convertView, R.id.tvNumberss);// 幸运码
		GetUserByGuidResultEntity data = list.get(position);
		tvNumberss.setText(data.getNumbers());

		return convertView;
	}
}
