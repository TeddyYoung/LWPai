package com.yfzx.lwpai.adapter;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.library.tools.ToolPicture;
import com.yfzx.library.widget.dialog.ShareDialog;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.activity.LoginActivity;
import com.yfzx.lwpai.entity.GetLuckyNumEntity;
import com.yfzx.lwpai.entity.GetLuckyNumEntity.ResultEntity;
import com.yfzx.lwpai.entity.LuckyProductByIdResult;
import com.yfzx.lwpai.util.CountDown;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.view.AutoTextView;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 商品分类幸运拍详细适配器
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-27
 */
public class GoodsLuckRedOneDetailAdapter extends
		BaseListAdapter<LuckyProductByIdResult> {

	private int status;
	private int type;
	private LuckyProductByIdResult luckyByIdResult;
	private Activity act;
	private int afficheIndex = 0;
	private List<ResultEntity> afficheData;
	private Boolean isLuckyNum = true;
	private int oneStatus = 0;
	private long startData;
	private long endData;
	private long timeNow;
	// 一元拍状态
	private String oneYuan;
	// 倒计时
	private String onePurchaseTime;
	/**
	 * 保留两位小数
	 */
	private DecimalFormat df;

	public String getOnePurchaseTime() {
		return onePurchaseTime;
	}

	public void setOnePurchaseTime(String onePurchaseTime) {
		this.onePurchaseTime = onePurchaseTime;
	}

	public String getOneYuan() {
		return oneYuan;
	}

	public void setOneYuan(String oneYuan) {
		this.oneYuan = oneYuan;
	}

	public GoodsLuckRedOneDetailAdapter(Activity context,
			List<LuckyProductByIdResult> list, int type, int status) {
		super(context, list);
		act = context;
		// this.status = status;
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @author: songbing.zhou
	 * 
	 * @see com.yfzx.library.core.BaseListAdapter#bindView(int,
	 * android.view.View, android.view.ViewGroup)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		luckyByIdResult = list.get(position);
		// 服务器时间
		timeNow = GetTimeHelper.getTime().getTime();
		// 一元拍
		if (type == 3) {
			// 一元拍的状态
			if (oneYuan.equals("2")) {
				oneStatus = 2;
			} else if (oneYuan.equals("1")) {
				oneStatus = 1;
			} else {
				oneStatus = 0;
			}
			// 一元拍
			if (convertView == null) {
				switch (oneStatus) {
				case 0:// 进行中
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_oneyuan_conduct, null);
					break;
				case 1:// 即将开始
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_oneyuan_start, null);
					break;
				case 2:// 竞拍结束
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_oneyuan_end, null);
					break;
				}
			}
			// 幸运拍 红包区
		} else {
			// 开始时间
			Date startTemp = ToolDateTime.parseTime(luckyByIdResult
					.getEndDate());
			startData = startTemp.getTime();
			// 结束时间
			Date endTemp = ToolDateTime.parseTime(luckyByIdResult.getEndDate());
			endData = endTemp.getTime();
			// 幸运拍 红包区状态
			if (luckyByIdResult.getIsFinish().equals("0")) {
				status = 0;
			} else if (luckyByIdResult.getIsFinish().equals("1")) {
				if (timeNow > startData) {
					status = 1;// 即将开始
				} else {
					status = 4;// 即将揭晓
				}

			} else if (luckyByIdResult.getIsFinish().equals("2")) {
				status = 2;
			} else if (luckyByIdResult.getIsFinish().equals("3")) {
				status = 3;
			}

			// 幸运拍 红包区
			if (convertView == null) {
				switch (status) {
				case 0:// 进行中
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_luckred_conduct, null);
					break;
				case 1:// 即将开始
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_luckred_start, null);
					break;
				case 2:// 竞拍结束
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_luckred_end, null);
					break;
				case 3:// 流拍
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_luckred_conduct, null);
					break;
				case 4:// 即将揭晓
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.include_luckred_conduct, null);
					break;
				}
			}
		}

		// 标题
		final TextView tvProductName = ViewHolder.get(convertView,
				R.id.tvProductName);
		// 分享
		final String url = "http://www.lwpai.com/weixin/";
		ImageView ivShare = ViewHolder.get(convertView, R.id.ivShare);
		ivShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String titleString = "乐拍网-";
				if (type == 3) {
					titleString += "一元拍";
				} else if (type == 55) {
					titleString += "幸运拍";
				} else {
					titleString += "红包区";
				}
				ShareDialog.getInstance().show(act, url, titleString,
						tvProductName.getText().toString(),
						ToolPicture.takeScreenShot(mContext));

			}
		});

		// 距结束
		TextView tvTime = ViewHolder.get(convertView, R.id.tvTime);
		// 销售价
		TextView tvSalePrice = ViewHolder.get(convertView, R.id.tvSalePrice);
		// 市场价
		TextView tvMarketPrice = ViewHolder
				.get(convertView, R.id.tvMarketPrice);
		// 报名费
		TextView tvFees = ViewHolder.get(convertView, R.id.tvFees);
		// 限制人数
		TextView tvMaxNumPercentage = ViewHolder.get(convertView,
				R.id.tvMaxNumPercentage);
		// 限制购买组数
		TextView tvLimitCount = ViewHolder.get(convertView, R.id.tvLimitCount);
		// 中奖者
		TextView tvWinnerName = ViewHolder.get(convertView, R.id.tvWinnerName);
		// 中奖号
		TextView tvWinnerNum = ViewHolder.get(convertView, R.id.tvWinnerNum);
		// 剩余时间
		TextView tvEndDate = ViewHolder.get(convertView, R.id.tvEndDate);
		// 竞拍开始
		TextView tvOvew = ViewHolder.get(convertView, R.id.tvOvew);
		// 登录查看幸运码
		TextView tvGoodsLogin = ViewHolder.get(convertView, R.id.tvGoodsLogin);
		// 剩余人数
		TextView tvResidue = ViewHolder.get(convertView, R.id.tvResidue);
		// 进度条
		ProgressBar pbarOneYuan = ViewHolder.get(convertView, R.id.pbarOneYuan);
		// 已经参与人数
		TextView tvCurrentCount = ViewHolder.get(convertView,
				R.id.tvCurrentCount);
		// 流拍不用的布局
		LinearLayout llytDown = ViewHolder.get(convertView, R.id.llytDown);
		// 幸运码
		AutoTextView tvLuckyNum = ViewHolder.get(convertView, R.id.tvLuckyNum);
		// 人
		TextView tvPeople = ViewHolder.get(convertView, R.id.tvPeople);
		// 组
		TextView tvGroup = ViewHolder.get(convertView, R.id.tvGroup);
		// 参拍人
		TextView tvGroupPrice = ViewHolder.get(convertView, R.id.tvGroupPrice);

		df = new java.text.DecimalFormat("#.##");
		if (UserManage.isLogin()) {
			tvGoodsLogin.setVisibility(View.GONE);
			if (type == 3) {
				tvLuckyNum.setVisibility(View.GONE);
			} else {
				tvLuckyNum.setVisibility(View.VISIBLE);
				if (isLuckyNum) {
					// 获取查看幸运码数据
					GetLuckyNum(tvLuckyNum);
					isLuckyNum = false;
				}

			}
		} else {
			if (type == 3) {
				tvGoodsLogin.setVisibility(View.GONE);
			} else {
				tvGoodsLogin.setVisibility(View.VISIBLE);
			}
			tvLuckyNum.setVisibility(View.GONE);
			tvGoodsLogin.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(act, LoginActivity.class);
					act.startActivity(intent);
				}
			});
		}

		if (type == 3) {
			// 一元拍
			switch (oneStatus) {
			case 0:// 进行中
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				tvMarketPrice.setText("¥" + luckyByIdResult.getMarketPrice());
				if (luckyByIdResult.getPercentage().equals("100")) {
					pbarOneYuan.setProgress(100);
				} else {
					pbarOneYuan
							.setProgress((int) (Double
									.parseDouble(luckyByIdResult
											.getPercentage()) * 100));
				}

				tvCurrentCount.setText("已参与"
						+ luckyByIdResult.getCurrentCount());
				tvLimitCount.setText("总需" + luckyByIdResult.getLimitCount()
						+ "人次");
				tvResidue
						.setText("剩余"
								+ (Integer.parseInt(luckyByIdResult
										.getLimitCount()) - Integer
										.parseInt(luckyByIdResult
												.getCurrentCount())) + "组");
				break;
			case 1:// 即将开始

				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				Date onePurchaseTimeTemp = ToolDateTime
						.parseTime(getOnePurchaseTime());
				// 距开始时间 倒计时
				CountDown oneCountDown = new CountDown(
						onePurchaseTimeTemp.getTime() - timeNow, tvTime, 3);
				oneCountDown.start();
				tvMarketPrice.setText("¥" + luckyByIdResult.getMarketPrice());
				pbarOneYuan.setProgress((int) (Double
						.parseDouble(luckyByIdResult.getPercentage()) * 100));
				tvCurrentCount.setText("已参与"
						+ luckyByIdResult.getCurrentCount());
				tvLimitCount.setText("总需" + luckyByIdResult.getLimitCount()
						+ "人次");
				tvResidue
						.setText("剩余"
								+ (Integer.parseInt(luckyByIdResult
										.getLimitCount()) - Integer
										.parseInt(luckyByIdResult
												.getCurrentCount())) + "组");
				tvOvew.setBackgroundDrawable(act.getResources().getDrawable(
						R.drawable.shape_small_green));
				break;
			case 2:// 竞拍结束
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				tvWinnerName.setText(luckyByIdResult.getWinnerName());
				tvWinnerNum.setText(luckyByIdResult.getWinnerNum());
				// 进度条
				pbarOneYuan.setProgress(Integer.parseInt(luckyByIdResult
						.getPercentage()));
				tvCurrentCount.setText("已参与"
						+ luckyByIdResult.getCurrentCount());
				tvLimitCount.setText("总需" + luckyByIdResult.getLimitCount()
						+ "人次");
				tvResidue
						.setText("剩余"
								+ (Integer.parseInt(luckyByIdResult
										.getLimitCount()) - Integer
										.parseInt(luckyByIdResult
												.getCurrentCount())) + "组");

				break;
			}
		} else {

			// 幸运拍 红包区
			switch (status) {

			case 0:// 进行中
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				// 距结束时间 倒计时
				CountDown endCountDown = new CountDown(endData - timeNow,
						tvTime, 2);
				endCountDown.start();
				tvSalePrice.setText("¥" + luckyByIdResult.getSalePrice() + "");
				tvMarketPrice.setText(luckyByIdResult.getMarketPrice() + "");
				tvMarketPrice.getPaint().setFlags(
						Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
				tvGroupPrice.setText(luckyByIdResult.getCurrentCount());
				// 报名费
				tvFees.setText(df.format(Double.parseDouble(luckyByIdResult
						.getFees())) + "元");
				// 限购组
				if (luckyByIdResult.getLimitCount().equals("0")) {
					tvLimitCount.setText("不限");
					tvGroup.setText("");
				} else {
					tvLimitCount.setText(luckyByIdResult.getLimitCount());
					tvGroup.setText("组");
				}
				// 限购人
				if (luckyByIdResult.getMaxNumPercentage().equals("0")) {
					tvMaxNumPercentage.setText("不限");
					tvPeople.setText("");
				} else {
					tvMaxNumPercentage.setText(luckyByIdResult
							.getMaxNumPercentage());
					tvPeople.setText("人");
				}
				break;
			case 1:// 即将开始
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				// 距开始时间 倒计时
				CountDown startCountDown = new CountDown(startData - timeNow,
						tvTime, 3);
				startCountDown.start();
				tvSalePrice.setText("¥" + luckyByIdResult.getSalePrice() + "");
				tvMarketPrice.setText(luckyByIdResult.getMarketPrice() + "");
				tvMarketPrice.getPaint().setFlags(
						Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
				tvGroupPrice.setText(luckyByIdResult.getCurrentCount());
				// 报名费
				tvFees.setText(df.format(Double.parseDouble(luckyByIdResult
						.getFees())) + "元");
				// 限购组
				if (luckyByIdResult.getLimitCount().equals("0")) {
					tvLimitCount.setText("不限");
					tvGroup.setText("");
				} else {
					tvLimitCount.setText(luckyByIdResult.getLimitCount());
					tvGroup.setText("组");
				}
				// 限购人
				if (luckyByIdResult.getMaxNumPercentage().equals("0")) {
					tvMaxNumPercentage.setText("不限");
					tvPeople.setText("");
				} else {
					tvMaxNumPercentage.setText(luckyByIdResult
							.getMaxNumPercentage());
					tvPeople.setText("人");
				}
				tvOvew.setBackgroundDrawable(act.getResources().getDrawable(
						R.drawable.shape_small_green));
				break;
			case 2:// 竞拍结束
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				tvWinnerName.setText(luckyByIdResult.getWinnerName());
				tvWinnerNum.setText(luckyByIdResult.getWinnerNum());
				tvEndDate.setText(luckyByIdResult.getEndDate());
				break;
			case 3:// 流拍
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				tvTime.setText("流拍");
				tvTime.setTextColor(act.getResources().getColor(
						R.color.btn_green));
				tvOvew.setText("竞拍结束");
				break;
			case 4:// 等待揭晓
				tvProductName.setText("第" + luckyByIdResult.getPeriodIndex()
						+ "期-" + luckyByIdResult.getProductName());
				tvTime.setText("等待揭晓");
				tvTime.setTextColor(act.getResources().getColor(
						R.color.btn_green));
				tvSalePrice.setText("¥" + luckyByIdResult.getSalePrice() + "");
				tvMarketPrice.setText(luckyByIdResult.getMarketPrice() + "");
				tvMarketPrice.getPaint().setFlags(
						Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
				tvGroupPrice.setText(luckyByIdResult.getCurrentCount());
				// 报名费
				tvFees.setText(df.format(Double.parseDouble(luckyByIdResult
						.getFees())) + "元");

				// 限购组
				if (luckyByIdResult.getLimitCount().equals("0")) {
					tvLimitCount.setText("不限");
					tvGroup.setText("");
				} else {
					tvLimitCount.setText(luckyByIdResult.getLimitCount());
					tvGroup.setText("组");
				}
				// 限购人
				if (luckyByIdResult.getMaxNumPercentage().equals("0")) {
					tvMaxNumPercentage.setText("不限");
					tvPeople.setText("");
				} else {
					tvMaxNumPercentage.setText(luckyByIdResult
							.getMaxNumPercentage());
					tvPeople.setText("人");
				}
				tvOvew.setText("等待揭晓");
				tvOvew.setBackgroundDrawable(act.getResources().getDrawable(
						R.drawable.shape_small_green));
				break;
			}
		}

		return convertView;
	}

	Handler handler = new Handler();
	private TextRunnable runnable;

	/**
	 * 幸运码
	 * 
	 * @author: songbing.zhou
	 * @version Revision: 0.0.1
	 * @Date: 2015-8-18
	 */
	private class TextRunnable implements Runnable {
		private AutoTextView tvLuckyNum = null;

		public TextRunnable(AutoTextView tvLuckyNum) {
			this.tvLuckyNum = tvLuckyNum;
		}

		@Override
		public void run() {
			// handler自带方法实现定时器
			try {
				handler.postDelayed(this, 4000);
				tvLuckyNum.next();
				afficheIndex++;
				tvLuckyNum.setText("幸运码："
						+ afficheData.get(afficheIndex % 5).getNumbers());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception...");
			}
		}
	}

	/**
	 * 幸运码
	 * 
	 * @author: songbing.zhou
	 */
	private void GetLuckyNum(final AutoTextView tvLuckyNum) {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetLuckyNum");// 方法
		httpClient.url.append("/" + type);// 类型
		httpClient.url.append("/" + luckyByIdResult.getId());// ID号
		httpClient.url.append("/" + 5);// 显示多少条

		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				GetLuckyNumEntity response = JsonUtil.parseObject(act,
						responseInfo.result, GetLuckyNumEntity.class, "");
				if (response != null) {
					afficheData = response.getResult();

					if (afficheData.size() != 1) {
						if (runnable == null) {
							runnable = new TextRunnable(tvLuckyNum);
							handler.postDelayed(runnable, 4000);
						}
					}
					tvLuckyNum.setText("幸运码："
							+ response.getResult().get(0).getNumbers());
				} else {
					tvLuckyNum.setText("您还未购买或幸运码未生成");
				}
			}
		});
	}

}
