package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.Good;

/**
 * 幸运拍界面适配器
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月27日
 */
public class LuckAuctionAdapter extends BaseListAdapter<Good> {

	public LuckAuctionAdapter(Activity context, List<Good> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_goods_luck_au, null);
		}
		ImageView ivPhoto = ViewHolder.get(convertView, R.id.ivGoodsPrint);
		TextView tvName = ViewHolder.get(convertView, R.id.tvNumber);
		// tvName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
		Good good = list.get(position);
		ivPhoto.setImageResource(good.getImgId());
		tvName.setText(good.getName());

		return convertView;
	}

}
