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
import com.yfzx.lwpai.entity.MsgEntity.ResultEntity;

/**
 * 首页幸运拍的适配器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-27
 */
public class MsgAdapter extends BaseListAdapter<ResultEntity> {

	public MsgAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_msg, null);
		}

		TextView tvTitle = ViewHolder.get(convertView, R.id.tvTitle);// 消息标题
		TextView tvContent = ViewHolder.get(convertView, R.id.tvContent);// 消息内容
		TextView tvDate = ViewHolder.get(convertView, R.id.tvDate);// 消息时间

		ResultEntity data = this.list.get(position);

		tvTitle.setText(data.getTitle());
		tvContent.setText(data.getContent());
		tvDate.setText(data.getDate());

		return convertView;
	}

}
