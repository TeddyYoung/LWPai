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
import com.yfzx.lwpai.entity.GetUserResultEntity;
import com.yfzx.lwpai.util.Helper;

/**
 * 查看验证码适配器
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月31日
 */
public class GetUserOfferByIdAdapter extends
		BaseListAdapter<GetUserResultEntity> {

	private Activity act;
	private int cate;// 区分红包区 幸运拍

	public GetUserOfferByIdAdapter(Activity context,
			List<GetUserResultEntity> list, int cate) {
		super(context, list);
		act = context;
		this.cate = cate;
	}

	@Override
	public int getCount() {
		if (cate != 3) {
			return Helper.paging(list.size());
		} else {
			return Helper.paging(list.size(), 2);
		}

	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {

		if (cate != 3) {
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_examine_luck_code, null);
			}
		} else {
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_examine_one_code, null);
			}
		}

		TextView tvPeriodIndex = ViewHolder
				.get(convertView, R.id.tvPeriodIndex);// 时间
		TextView tvProductName = ViewHolder
				.get(convertView, R.id.tvProductName);// 日期
		TextView tvSumCount = ViewHolder.get(convertView, R.id.tvSumCount);// 第几组
		// 随机码
		TextView tvRandomL = ViewHolder.get(convertView, R.id.tvRandomL);
		TextView tvRandomR = ViewHolder.get(convertView, R.id.tvRandomR);
		// TextView tvLuckSum = ViewHolder.get(convertView, R.id.tvLuckSum);//
		// 查看验证码
		GetUserResultEntity date = list.get(position);

		if (cate != 3) {
			String time = date.getOfferTime();
			String mytime[] = time.split(" ", 2);
			tvProductName.setText(mytime[0]);
			tvPeriodIndex.setText(mytime[1]);
			tvSumCount.setText(date.getOfferCount() + "组");
		} else {
			if ((position * 2) < list.size()) {
				GetUserResultEntity oneDateL = list.get(position * 2);
				tvRandomL.setText(oneDateL.getNumbers());
			}

			if ((position * 2 + 1) < list.size()) {
				GetUserResultEntity oneDateR = list.get(position * 2 + 1);
				tvRandomR.setText(oneDateR.getNumbers());
				tvRandomR.setVisibility(View.VISIBLE);
			} else {
				tvRandomR.setVisibility(View.INVISIBLE);
			}

		}

		return convertView;
	}

}
