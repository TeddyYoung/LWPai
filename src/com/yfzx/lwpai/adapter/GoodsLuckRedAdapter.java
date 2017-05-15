package com.yfzx.lwpai.adapter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.entity.GoodsLuckResult;
import com.yfzx.lwpai.util.GetTimeHelper;

/**
 * 商品分类幸运拍适配器
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-27
 */
public class GoodsLuckRedAdapter extends BaseListAdapter<GoodsLuckResult> {

	private Activity act;
	private GoodsLuckResult goodsLuckResult;
	// 状态
	private int status;
	// 类型
	private int goodType;

	public GoodsLuckRedAdapter(Activity context, List<GoodsLuckResult> list,
			int status, int goodType) {
		super(context, list);
		act = context;
		this.status = status;
		this.goodType = goodType;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		goodsLuckResult = list.get(position);

		if (convertView == null) {
			switch (status) {
			case 0:// 进行中
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_luckred_conduct, null);
				break;
			case 1:// 即将开始
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_luckred_start, null);
				break;
			case 2:// 竞拍结束
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_luckred_end, null);
				break;
			}
		}
		ImageView ivLuckRedImg = ViewHolder.get(convertView, R.id.ivLuckRedImg);
		TextView tvLuckTitle = ViewHolder.get(convertView, R.id.tvLuckTitle);
		TextView tvSalePrice = ViewHolder.get(convertView, R.id.tvSalePrice);
		TextView tvMarketPrice = ViewHolder
				.get(convertView, R.id.tvMarketPrice);
		TextView tvUrlTip = ViewHolder.get(convertView, R.id.tvUrlTip);
		TextView tvPercentage = ViewHolder.get(convertView, R.id.tvPercentage);
		TextView TvCurrentCount = ViewHolder.get(convertView,
				R.id.TvCurrentCount);
		TextView tvStartDate = ViewHolder.get(convertView, R.id.tvStartDate);
		TextView tvShoot = ViewHolder.get(convertView, R.id.tvShoot);
		ProgressBar pabrLuck = ViewHolder.get(convertView, R.id.pabrLuck);
		// 线性布局
		LinearLayout llytUrlTip = ViewHolder.get(convertView, R.id.llytUrlTip);
		switch (status) {
		case 0: {// 进行中
			Date startDate = ToolDateTime.parseTime(goodsLuckResult
					.getStartDate());
			Date endDate = ToolDateTime.parseTime(goodsLuckResult.getEndDate());
			// 服务器时间
			Date timeNow = GetTimeHelper.getTime();
			final long start = startDate.getTime();
			final long end = endDate.getTime();
			final long time = timeNow.getTime();
			ImageLoaderUtil.getByUrl(goodsLuckResult.getThumbnailUrl100(),
					goodsLuckResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsLuckResult.getProductName());
			tvSalePrice.setText(goodsLuckResult.getSalePrice() + "");
			tvMarketPrice.setText(goodsLuckResult.getMarketPrice() + "");
			tvMarketPrice.getPaint().setFlags(
					Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
			tvUrlTip.setText("参与竞拍");
			TvCurrentCount.setText("已参拍：" + goodsLuckResult.getCurrentCount()
					+ "人");
			// 剩余时间
			double surplusTime = (double) ((time - start) * 1.0 / (end - start - 4800));
			// 当前时间大于结束时间
			if (time > end) {
				tvPercentage.setText("已结束");
				pabrLuck.setProgress(100);
			} else {
				// 最大人数等于0 进度条用剩余时间
				if (Integer.parseInt(goodsLuckResult.getMaxNumPercentage()) != 0) {
					// 人数比
					double number = Double.parseDouble(goodsLuckResult
							.getCurrentCount())
							/ Double.parseDouble(goodsLuckResult
									.getMaxNumPercentage());
					// 剩余时间比大于人数比
					if (surplusTime > number) {
						tvPercentage.setText("已完成" + (int) (surplusTime * 100)
								+ "%");
						pabrLuck.setProgress((int) (surplusTime * 100));
					} else {
						tvPercentage
								.setText("已完成" + (int) (number * 100) + "%");
						pabrLuck.setProgress((int) (number * 100));
					}
				} else {
					tvPercentage.setText("已完成" + (int) (surplusTime * 100)
							+ "%");
					pabrLuck.setProgress((int) (surplusTime * 100));
				}
			}
			break;
		}
		case 1: {// 即将开始
			ImageLoaderUtil.getByUrl(goodsLuckResult.getThumbnailUrl100(),
					goodsLuckResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsLuckResult.getProductName());
			tvSalePrice.setText(goodsLuckResult.getSalePrice() + "");
			tvMarketPrice.setText(goodsLuckResult.getMarketPrice() + "");
			tvMarketPrice.getPaint().setFlags(
					Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
			tvUrlTip.setText("即将开始");
			tvStartDate.setText(goodsLuckResult.getStartDate());
			TvCurrentCount.setText("已参拍：" + goodsLuckResult.getCurrentCount()
					+ "人");
			break;
		}
		case 2: {// 竞拍结束
			ImageLoaderUtil.getByUrl(goodsLuckResult.getThumbnailUrl100(),
					goodsLuckResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsLuckResult.getProductName());
			tvSalePrice.setText(goodsLuckResult.getSalePrice() + "");
			tvMarketPrice.setText(goodsLuckResult.getMarketPrice() + "");
			tvMarketPrice.getPaint().setFlags(
					Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
			tvUrlTip.setText("竞拍结束");
			tvShoot.setText(goodsLuckResult.getWinnerName());
			TvCurrentCount.setText("已参拍：" + goodsLuckResult.getCurrentCount()
					+ "人");
			break;
		}
		}
		// 点击查看事件
		llytUrlTip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(act, GoodsLuckDetailActivity.class);
				bundle.putInt("category", status);
				// 区分幸运拍 红包区一元拍
				bundle.putInt("type", goodType);
				// 显示TOP3000
				bundle.putInt("lucky", 1);
				// 商品ID
				bundle.putString("goodsId", list.get(position).getId());
				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);
			}
		});

		return convertView;
	}

}
