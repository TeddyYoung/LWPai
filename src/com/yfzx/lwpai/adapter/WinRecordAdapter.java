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
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.DetailsPriceResult;

/**
 * 中拍记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class WinRecordAdapter extends BaseListAdapter<DetailsPriceResult> {

	private int type;

	public WinRecordAdapter(Activity context, List<DetailsPriceResult> list,
			int type) {
		super(context, list);
		this.type = type;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_bid_record, null);
		}
		DetailsPriceResult date = list.get(position);
		ImageView ivFaceImage = ViewHolder.get(convertView, R.id.ivFaceImage);// 中拍者头像
		TextView tvUserName = ViewHolder.get(convertView, R.id.tvUserName);// 中拍者账号
		TextView tvSite = ViewHolder.get(convertView, R.id.tvSite);// 中拍者地址
		TextView tvTime = ViewHolder.get(convertView, R.id.tvTime);// 中拍時間
		TextView tvGroup = ViewHolder.get(convertView, R.id.tvGroup);// 组数
		// 头像
		ImageLoaderUtil.getByUrl(date.getFaceImage(), ivFaceImage);
		// 用户名
		tvUserName.setText(date.getUserName());

		if (type == 3) {
			tvGroup.setText(date.getOnePurchaseCount() + "组");
			tvTime.setText(date.getOnePurchaseTime());
			tvSite.setText("");
		} else {
			tvGroup.setText(date.getOfferNum() + "组");
			tvTime.setText(date.getOfferTime());
			tvSite.setText("(" + date.getLuckyProvince() + date.getLuckyCity()
					+ ")");
		}

		return convertView;
	}
}
