package com.yfzx.lwpai.adapter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.yfzx.library.tools.ToolLog;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.entity.HomeLuckResult;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.util.Helper;
import com.yfzx.lwpai.util.ScreenHelper;

/**
 * 首页幸运拍的适配器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-27
 */
public class HomeLuckAdapter extends BaseListAdapter<HomeLuckResult> {
	private int homeFlag;
	private Activity act;
	private int status = 0;
	private ImageView ivThumbnailL;
	private TextView tvProductNameL;
	private ProgressBar pbarL;
	private TextView tvPercentageL;
	private ImageView ivThumbnailR;
	private TextView tvProductNameR;
	private ProgressBar pbarR;
	private TextView tvPercentageR;
	private LinearLayout llytLeft;
	private LinearLayout llytRight;

	@Override
	public int getCount() {
		return Helper.paging(list.size(), 2);
	}

	public HomeLuckAdapter(Activity context, List<HomeLuckResult> list,
			int homeFlag) {
		super(context, list);
		act = context;
		// 判断 3一元拍 55幸运拍 56红包区
		this.homeFlag = homeFlag;
	}

	public void setHomeFlag(int homeFlag) {
		this.homeFlag = homeFlag;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_home, null);
		}

		ivThumbnailL = ViewHolder.get(convertView, R.id.ivThumbnailL);
		tvProductNameL = ViewHolder.get(convertView, R.id.tvProductNameL);
		pbarL = ViewHolder.get(convertView, R.id.pbarL);
		tvPercentageL = ViewHolder.get(convertView, R.id.tvPercentageL);
		ivThumbnailR = ViewHolder.get(convertView, R.id.ivThumbnailR);
		tvProductNameR = ViewHolder.get(convertView, R.id.tvProductNameR);
		pbarR = ViewHolder.get(convertView, R.id.pbarR);
		tvPercentageR = ViewHolder.get(convertView, R.id.tvPercentageR);
		llytLeft = (LinearLayout) convertView.findViewById(R.id.llytLeft);
		llytRight = (LinearLayout) convertView.findViewById(R.id.llytRight);

		switch (homeFlag) {
		case 3: {// 一元拍
			// 左边
			if ((position * 2) < list.size()) {
				HomeLuckResult homeOneYuanResultL = list.get(position * 2);
				oneYuan(homeOneYuanResultL, llytLeft, ivThumbnailL,
						tvProductNameL, tvPercentageL, pbarL);
			}

			// 右边
			if ((position * 2 + 1) < list.size()) {
				HomeLuckResult homeOneYuanResultR = list.get(position * 2 + 1);
				oneYuan(homeOneYuanResultR, llytRight, ivThumbnailR,
						tvProductNameR, tvPercentageR, pbarR);
				llytRight.setVisibility(View.VISIBLE);
			} else {
				llytRight.setVisibility(View.INVISIBLE);
			}
			break;
		}
		case 55: {// 幸运拍
			// 左边
			if ((position * 2) < list.size()) {
				HomeLuckResult homeLuckResultL = list.get(position * 2);
				luckRed(homeLuckResultL, llytLeft, ivThumbnailL,
						tvProductNameL, tvPercentageL, pbarL, homeFlag);
			}

			// 右边
			if ((position * 2 + 1) < list.size()) {
				HomeLuckResult homeLuckResultR = list.get(position * 2 + 1);
				luckRed(homeLuckResultR, llytRight, ivThumbnailR,
						tvProductNameR, tvPercentageR, pbarR, homeFlag);
				llytRight.setVisibility(View.VISIBLE);
			} else {
				llytRight.setVisibility(View.INVISIBLE);
			}
			break;
		}
		case 56: {// 红包区
			// 左边
			if ((position * 2) < list.size()) {
				HomeLuckResult homeRedResultL = list.get(position * 2);
				luckRed(homeRedResultL, llytLeft, ivThumbnailL, tvProductNameL,
						tvPercentageL, pbarL, homeFlag);
			}

			// 右边
			if ((position * 2 + 1) < list.size()) {
				HomeLuckResult homeRedResultR = list.get(position * 2 + 1);
				luckRed(homeRedResultR, llytRight, ivThumbnailR,
						tvProductNameR, tvPercentageR, pbarR, homeFlag);
				llytRight.setVisibility(View.VISIBLE);
			} else {
				llytRight.setVisibility(View.INVISIBLE);
			}
			break;
		}
		}

		LinearLayout.LayoutParams lParams = (android.widget.LinearLayout.LayoutParams) ivThumbnailL
				.getLayoutParams();
		ivThumbnailL.getLayoutParams();
		lParams.width = (ScreenHelper.getScreenWidthPix(mContext)) / 2 - 80;
		lParams.height = lParams.width;
		ivThumbnailL.setLayoutParams(lParams);

		LinearLayout.LayoutParams RParams = (android.widget.LinearLayout.LayoutParams) ivThumbnailR
				.getLayoutParams();
		ivThumbnailR.getLayoutParams();
		RParams.width = (ScreenHelper.getScreenWidthPix(mContext)) / 2 - 80;
		RParams.height = lParams.width;
		ivThumbnailR.setLayoutParams(RParams);
		return convertView;
	}

	/**
	 * 幸运拍
	 * 
	 * @author: songbing.zhou
	 * @param homeLuckResult
	 * @param llyt
	 * @param ivThumbnail
	 * @param tvProductName
	 * @param tvPercentage
	 * @param pbar
	 */
	public void luckRed(final HomeLuckResult homeLuckResult, LinearLayout llyt,
			ImageView ivThumbnail, TextView tvProductName,
			TextView tvPercentage, ProgressBar pbar, final int homeFlag) {
		// 开始时间
		Date startDate = ToolDateTime.parseDate(homeLuckResult.getStartDate());
		// 结束时间
		Date endDate = ToolDateTime.parseDate(homeLuckResult.getEndDate());
		// 服务器时间
		Date timeNow = GetTimeHelper.getTime();
		final long start = startDate.getTime();
		final long end = endDate.getTime();
		final long time = timeNow.getTime();
		// 剩余时间比
		double surplusTime = (double) ((time - start) * 1.0 / (end - start - 4800));
		// 一元拍与红包区
		switch (homeFlag) {
		case 55: {// 幸运拍
			ImageLoaderUtil.getByUrl(homeLuckResult.getThumbnailUrl100(),
					homeLuckResult.getThumbnailUrl440(), ivThumbnail);
			tvProductName.setText(homeLuckResult.getProductName());
			// 当前时间大于结束时间
			if (time > end) {
				tvPercentage.setText("已结束");
				pbar.setProgress(100);
			} else {
				if (homeLuckResult.getMaxNumPercentage() != null) {
					// 最大人数等于0 进度条用剩余时间
					if (Integer.parseInt(homeLuckResult.getMaxNumPercentage()) != 0) {
						// 人数比
						double number = Double.parseDouble(homeLuckResult
								.getCount() + "")
								/ Double.parseDouble(homeLuckResult
										.getMaxNumPercentage());
						// 剩余时间比大于人数比
						if (surplusTime > number) {
							tvPercentage.setText("已完成"
									+ (int) (surplusTime * 100) + "%");
							pbar.setProgress((int) (surplusTime * 100));
						} else {
							tvPercentage.setText("已完成" + (int) (number * 100)
									+ "%");
							pbar.setProgress((int) (number * 100));
						}
					} else {
						tvPercentage.setText("已完成" + (int) (surplusTime * 100)
								+ "%");
						pbar.setProgress((int) (surplusTime * 100));
					}
				}
			}
			break;
		}
		case 56: {// 红包区
			// 当前时间大于结束时间
			ImageLoaderUtil.getByUrl(homeLuckResult.getThumbnailUrl100(),
					homeLuckResult.getThumbnailUrl440(), ivThumbnail);
			tvProductName.setText(homeLuckResult.getProductName());
			if (time > end) {
				tvPercentage.setText("已结束");
				pbar.setProgress(100);
			} else {
				tvPercentage.setText("已完成" + (int) (surplusTime * 100) + "%");
				pbar.setProgress((int) (surplusTime * 100));
			}
			break;
		}
		}
		// 模块界面点击事件
		llyt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 状态判断
				if (time < start) {
					status = 1;
				} else if (time > end) {
					status = 2;
				} else {
					status = 0;
				}
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(act, GoodsLuckDetailActivity.class);
				// 幸运拍
				bundle.putInt("type", homeFlag);
				// 状态 0进行 1即将开始 2结束
				bundle.putInt("category", status);
				// 商品ID
				bundle.putString("goodsId",
						homeLuckResult.getAuctionProductsId() + "");
				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);
			}
		});
	}

	/**
	 * 一元拍
	 * 
	 * @author: songbing.zhou
	 * @param homeLuckResult
	 * @param llyt
	 * @param ivThumbnail
	 * @param tvProductName
	 * @param tvPercentage
	 * @param pbar
	 */
	public void oneYuan(final HomeLuckResult homeLuckResult, LinearLayout llyt,
			ImageView ivThumbnail, TextView tvProductName,
			TextView tvPercentage, ProgressBar pbar) {
		ImageLoaderUtil.getByUrl(homeLuckResult.getThumbnailUrl100(),
				homeLuckResult.getThumbnailUrl440(), ivThumbnail);
		tvProductName.setText(homeLuckResult.getProductName());
		tvPercentage.setText("已完成" + homeLuckResult.getPercentage() + "%");
		if (!TextUtils.isEmpty(homeLuckResult.getPercentage())) {
			pbar.setProgress((int) Double.parseDouble(homeLuckResult
					.getPercentage()));
		}
		// 状态判断
		final int statu = homeLuckResult.getStatus();
		// 左边界面点击事件
		llyt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(act, GoodsLuckDetailActivity.class);
				Bundle bundle = new Bundle();
				// 一元拍
				bundle.putInt("type", homeFlag);
				// 状态 0进行 1即将开始 2结束
				bundle.putInt("category", statu);
				// 商品ID
				bundle.putString("goodsId",
						homeLuckResult.getAuctionProductsId() + "");
				// 结束商品ID
				bundle.putString("goodsPTId",
						homeLuckResult.getOnePurchasePTId() + "");
				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);
			}
		});

	}

}
