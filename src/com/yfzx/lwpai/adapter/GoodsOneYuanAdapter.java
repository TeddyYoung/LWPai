package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.entity.GoodsOneYuanResult;

/**
 * 商品分类幸运拍适配器
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-27
 */
public class GoodsOneYuanAdapter extends BaseListAdapter<GoodsOneYuanResult> {

	private int status;
	private GoodsOneYuanResult goodsOneYuanResult;
	private Activity act;

	public GoodsOneYuanAdapter(Activity context, List<GoodsOneYuanResult> list,
			int status) {
		super(context, list);
		this.status = status;
		act = context;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		goodsOneYuanResult = list.get(position);

		if (convertView == null) {
			switch (status) {
			case 0:// 进行中
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_oneyuan_conduct, null);
				break;
			case 1:// 即将开始
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_oneyuan_start, null);
				break;
			case 2:// 竞拍结束
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_goods_oneyuan_end, null);
				break;
			}
		}
		ImageView ivLuckRedImg = ViewHolder.get(convertView, R.id.ivLuckRedImg);// 图片
		TextView tvLuckTitle = ViewHolder.get(convertView, R.id.tvLuckTitle);// 标题
		TextView tvOnePurchaseNum = ViewHolder.get(convertView,
				R.id.tvOnePurchaseNum);// 所需人数
		ProgressBar pabrOneYuan = ViewHolder.get(convertView, R.id.pabrOneYuan);// 进度条
		TextView tvCurrentPurchaseNum = ViewHolder.get(convertView,
				R.id.tvCurrentPurchaseNum);// 当前人数
		TextView tvLastPurchaseNum = ViewHolder.get(convertView,
				R.id.tvLastPurchaseNum);// 剩余人数
		TextView tvSalePrice = ViewHolder.get(convertView, R.id.tvSalePrice);// 价钱
		TextView tvShoot = ViewHolder.get(convertView, R.id.tvShoot);// 中奖者
		TextView tvShootNum = ViewHolder.get(convertView, R.id.tvShootNum);// 中奖号码
		TextView tvHaoMa = ViewHolder.get(convertView, R.id.tvHaoMa);// 日期
		TextView tvUrlTip = ViewHolder.get(convertView, R.id.tvUrlTip);// 查看

		// 线性布局
		LinearLayout llytUrlTip = ViewHolder.get(convertView, R.id.llytUrlTip);
		switch (status) {
		case 0:// 进行
			ImageLoaderUtil.getByUrl(goodsOneYuanResult.getThumbnailUrl100(),
					goodsOneYuanResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsOneYuanResult.getProductName());
			tvOnePurchaseNum.setText("总需"
					+ goodsOneYuanResult.getOnePurchaseNum() + "人次");
			pabrOneYuan.setProgress((int) goodsOneYuanResult.getPercentage());
			tvCurrentPurchaseNum.setText("已参与"
					+ goodsOneYuanResult.getCurrentPurchaseNum() + "");
			tvLastPurchaseNum.setText(goodsOneYuanResult.getLastPurchaseNum()
					+ "");
			break;
		case 1:// 开始
			ImageLoaderUtil.getByUrl(goodsOneYuanResult.getThumbnailUrl100(),
					goodsOneYuanResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsOneYuanResult.getProductName());
			tvOnePurchaseNum.setText("总需"
					+ goodsOneYuanResult.getOnePurchaseNum() + "人次");
			tvHaoMa.setText(goodsOneYuanResult.getOnePurchaseTime() + "后开始");
			break;
		case 2:// 结束
			ImageLoaderUtil.getByUrl(goodsOneYuanResult.getThumbnailUrl100(),
					goodsOneYuanResult.getThumbnailUrl440(), ivLuckRedImg);
			tvLuckTitle.setText(goodsOneYuanResult.getProductName());
			tvSalePrice.setText("￥" + goodsOneYuanResult.getSalePrice() + "");
			tvShoot.setText(goodsOneYuanResult.getWinnerName());
			tvShootNum.setText(goodsOneYuanResult.getWinnerNum());

		}
		// 点击查看事件
		llytUrlTip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(act, GoodsLuckDetailActivity.class);
				// 状态
				bundle.putInt("category", status);
				// 类型
				bundle.putInt("type", 3);
				// 商品ID
				bundle.putString("goodsId", list.get(position)
						.getOnePurchasePId() + "");
				bundle.putString("goodsPTId", list.get(position)
						.getOnePurchasePTId() + "");
				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);
			}
		});

		return convertView;
	}

}
