package com.yfzx.lwpai.adapter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.yfzx.lwpai.activity.ExamineLuckCodeActivity;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.entity.MineLuckEntity;
import com.yfzx.lwpai.util.GetTimeHelper;

/**
 * 乐拍记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月22日
 */
public class MineLuckAutionAdapter extends BaseListAdapter<MineLuckEntity> {

	private Activity act;
	private MineLuckEntity date;
	private MineLuckEntity mineluck;
	private String name;
	/**
	 * 区分幸运拍 红包区一元拍
	 */
	private int cate;
	/**
	 * // 0进行 1即将开始 2结束
	 */
	private int type;

	private Date startDate;
	private long start;
	private long end;
	private long time;
	private double surplusTime;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
	}

	public MineLuckAutionAdapter(Activity context, List<MineLuckEntity> list) {
		super(context, list);
		act = context;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_mine_lucky_auction, null);
		}

		ImageView ivPicture = ViewHolder.get(convertView, R.id.ivPicture);
		TextView tvProductName = ViewHolder
				.get(convertView, R.id.tvProductName);
		TextView tvSumCount = ViewHolder.get(convertView, R.id.tvSumCount);
		TextView tvLuckSum = ViewHolder.get(convertView, R.id.tvLuckSum);
		TextView tvIng = ViewHolder.get(convertView, R.id.tvIng);
		TextView tvLuckyName = ViewHolder.get(convertView, R.id.tvLuckyName);
		TextView tvNumber = ViewHolder.get(convertView, R.id.tvNumber);
		ProgressBar pbar = ViewHolder.get(convertView, R.id.pbar);
		TextView tvPercentage = ViewHolder.get(convertView, R.id.tvPercentage);
		LinearLayout llCode = ViewHolder.get(convertView, R.id.llCode);
		TextView tvAlreadyend = ViewHolder.get(convertView, R.id.tvAlreadyend);
		LinearLayout llProgressbar = ViewHolder.get(convertView,
				R.id.llProgressbar);
		LinearLayout llytMineLuck = ViewHolder.get(convertView,
				R.id.llytMineLuck);

		date = list.get(position);
		// 图片
		ImageLoaderUtil.getByUrl(date.getThumbnailUrl100(),
				date.getThumbnailUrl440(), ivPicture);
		tvProductName.setText("(第" + date.getPeriodIndex() + "期)"
				+ date.getProductName());
		tvSumCount.setText(date.getSumCount() + "人次");
		if (position == 0) {
			mineluck = list.get(position);
			// String str = String.valueOf(position);
			// ToolLog.d(str);

			// ToolLog.d(id);
		}
		if (position > 0) {
			mineluck = list.get(position - 1);
		}
		name = mineluck.getProductName();
		// id = mineluck.getId();
		ToolLog.d(name);
		// 结束时间
		Date endDate = ToolDateTime.parseTime(date.getEndDate());
		end = endDate.getTime();
		// 服务器时间
		Date timeNow = GetTimeHelper.getTime();
		time = timeNow.getTime();
		if (cate != 3) {
			// 开始时间
			startDate = ToolDateTime.parseTime(date.getStartDate());
			start = startDate.getTime();
			// 剩余时间比
			surplusTime = (double) ((time - start) * 1.0 / (end - start - 4800));
		}
		switch (cate) {
		case 3: {
			// 一元拍没有幸运码
			tvLuckSum.setText("查看随机码");
			// 当前时间大于结束时间
			if (!date.getIsStop().equals("0")) {
				llCode.setVisibility(View.VISIBLE);
				llProgressbar.setVisibility(View.GONE);
				tvLuckyName.setText(date.getWinnerName());
				tvNumber.setText(date.getWinnerNum());
				tvAlreadyend.setText("已结束");
				if (date.getStatus().equals("0")) {
					tvIng.setText("未中");
				} else if (date.getStatus().equals("1")) {
					tvIng.setText("中拍");
				}

			} else {
				llCode.setVisibility(View.GONE);
				llProgressbar.setVisibility(View.VISIBLE);
				tvPercentage.setText("已完成" + date.getPercentage() + "%");
				pbar.setProgress((int) Double.parseDouble(date.getPercentage()));
				tvAlreadyend.setText("进行中");
			}

		}

			break;
		case 55: {
			// 当前时间大于结束时间
			if (time > end) {
				llCode.setVisibility(View.VISIBLE);
				llProgressbar.setVisibility(View.GONE);
				tvLuckyName.setText(date.getWinnerName());
				tvNumber.setText(date.getWinnerNum());
				tvAlreadyend.setText("已结束");
				if (date.getStatus().equals("0")) {
					tvIng.setText("未中");
				} else if (date.getStatus().equals("1")) {
					tvIng.setText("中拍");
				}

			} else {
				llCode.setVisibility(View.GONE);
				llProgressbar.setVisibility(View.VISIBLE);
				// 最大人数等于0 进度条用剩余时间
				if (Integer.parseInt(date.getMaxNumPercentage()) != 0) {
					// 剩余时间比大于人数比

					// 人数比
					double number = Double.parseDouble(date.getCurrentCount()
							+ "")
							/ Double.parseDouble(date.getMaxNumPercentage());
					if (surplusTime > number) {
						tvPercentage.setText("已完成" + (int) (surplusTime * 100)
								+ "%");
						pbar.setProgress((int) (surplusTime * 100));
					} else {
						tvPercentage
								.setText("已完成" + (int) (number * 100) + "%");
						pbar.setProgress((int) (number * 100));
					}
				} else {
					tvPercentage.setText("已完成" + (int) (surplusTime * 100)
							+ "%");
					pbar.setProgress((int) (surplusTime * 100));
				}
				tvAlreadyend.setText("进行中");
			}

		}

			break;
		case 56: {// 红包区
			// 当前时间大于结束时间
			if (time > end) {
				tvLuckyName.setText(date.getWinnerName());
				tvNumber.setText(date.getWinnerNum());
				llCode.setVisibility(View.VISIBLE);
				llProgressbar.setVisibility(View.GONE);
				tvAlreadyend.setText("已结束");
				if (date.getStatus().equals("0")) {
					tvIng.setText("未中");
				} else if (date.getStatus().equals("1")) {
					tvIng.setText("中拍");
				}
			} else {
				tvPercentage.setText("已完成" + (int) (surplusTime * 100) + "%");
				pbar.setProgress((int) (surplusTime * 100));
				llCode.setVisibility(View.GONE);
				llProgressbar.setVisibility(View.VISIBLE);
				tvAlreadyend.setText("进行中");
			}
			break;

		}
		case 77: {
			// 当前时间大于结束时间
			if (time > end) {
				tvLuckyName.setText(date.getWinnerName());
				tvNumber.setText(date.getWinnerNum());
				llCode.setVisibility(View.VISIBLE);
				llProgressbar.setVisibility(View.GONE);
				tvAlreadyend.setText("已结束");
				if (date.getStatus().equals("0")) {
					tvIng.setText("未中");
				} else if (date.getStatus().equals("1")) {
					tvIng.setText("中拍");
				}
			} else {
				llCode.setVisibility(View.GONE);
				llProgressbar.setVisibility(View.VISIBLE);
				// 最大人数等于0 进度条用剩余时间
				if (Integer.parseInt(date.getMaxNumPercentage()) != 0) {
					// 剩余时间比大于人数比
					// 人数比
					double number = Double.parseDouble(date.getCurrentCount()
							+ "")
							/ Double.parseDouble(date.getMaxNumPercentage());
					if (surplusTime > number) {
						tvPercentage.setText("已完成" + (int) (surplusTime * 100)
								+ "%");
						pbar.setProgress((int) (surplusTime * 100));
					} else {
						tvPercentage
								.setText("已完成" + (int) (number * 100) + "%");
						pbar.setProgress((int) (number * 100));
					}
				} else {
					tvPercentage.setText("已完成" + (int) (surplusTime * 100)
							+ "%");
					pbar.setProgress((int) (surplusTime * 100));
				}
				tvAlreadyend.setText("进行中");
			}

		}
			break;

		default:
			break;
		}

		tvLuckSum.setOnClickListener(new OnClickListener() {
			// 查看幸运码
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(act, ExamineLuckCodeActivity.class);
				// bundle.putString("index", mineluck.getPeriodIndex());
				// bundle.putString("name", name);
				// bundle.putString("count", mineluck.getSumCount());
				if (cate != 3) {
					bundle.putString("id", list.get(position).getLuckyId());
				} else {
					bundle.putString("id", list.get(position).getId());

				}
				bundle.putInt("cate", cate);
				intent.putExtras(bundle);
				act.startActivity(intent);

			}
		});
		// 列表点击
		llytMineLuck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(act, GoodsLuckDetailActivity.class);
				// 区分幸运拍 红包区一元拍
				bundle.putInt("type", cate);

				if (cate == 3) {
					Log.i("zzdate.getPercentage()", date.getPercentage());
					// 0进行 1即将开始 2结束
					if (list.get(position).getPercentage().equals("100.00")) {
						bundle.putInt("category", 2);
					}
					// 一元商品ID
					bundle.putString("goodsId", list.get(position)
							.getOnePurchasePId());
					// 一元商品PTId
					bundle.putString("goodsPTId", list.get(position)
							.getOnePurchasePTId());
				} else {
					// 商品ID
					bundle.putString("goodsId", list.get(position).getLuckyId());
				}

				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);
			}
		});

		return convertView;
	}
}
