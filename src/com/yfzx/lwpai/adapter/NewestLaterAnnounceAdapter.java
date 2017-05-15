package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.NewestGetLuckyWinner.ResultEntity;

/**
 * 往期揭曉適配器
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月13日
 */
public class NewestLaterAnnounceAdapter extends BaseListAdapter<ResultEntity> {

	private int type;
	private String OfferTime;

	public NewestLaterAnnounceAdapter(Activity context,
			List<ResultEntity> list, int type) {
		super(context, list);
		this.type = type;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_later_announce, null);
		}
		TextView tvUserName = ViewHolder.get(convertView, R.id.tvUserName);// 中拍者
		TextView tvEndDate = ViewHolder.get(convertView, R.id.tvEndDate);// 中拍时间
		TextView tvLuckyProvince = ViewHolder.get(convertView,
				R.id.tvLuckyProvince);// 中拍地址
		TextView tvWinLotteryNum = ViewHolder.get(convertView,
				R.id.tvWinLotteryNum);// 中拍号码
		ImageView ivFaceImage = ViewHolder.get(convertView, R.id.ivFaceImage);// 中拍者头像
		TextView tvPeriodIndex = ViewHolder.get(convertView, R.id.tvIssues);// 第几期
		TextView tvSumCount = ViewHolder.get(convertView, R.id.tvSumCount);// 人数
		TextView tvZhongpa1 = ViewHolder.get(convertView, R.id.tvZhongpa1);// 人数
		
		ResultEntity date = list.get(position);
		// tvName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
		tvUserName.setText(date.getUserName());
		// 时间
		if (type == 3) {
			// 中奖时间
			OfferTime = date.getOnePurchaseTime().replaceAll("/", "-");
			// 参与次数
			tvSumCount.setText(Html.fromHtml(date.getOnePurchaseCount()
					+ "<font color=black>人次</font>"));
			tvLuckyProvince.setVisibility(View.GONE);
		} else {
			// 中奖时间
			OfferTime = date.getOfferTime().replaceAll("/", "-");
			tvZhongpa1.setText("参与组数：");
			tvSumCount.setText(Html.fromHtml(date.getOfferNum()
					+ "<font color=black>组</font>"));
			// 中拍地点
			tvLuckyProvince.setText("(" + date.getLuckyProvince()
					+ date.getLuckyCity() + ")");
		}
		tvEndDate.setText(OfferTime);
		tvWinLotteryNum.setText(date.getWinLotteryNum());
		tvPeriodIndex.setText("[第" + date.getPeriodIndex() + "期]");
		ImageLoaderUtil.getByUrl(date.getFaceImage(), ivFaceImage);
		return convertView;
	}
}
