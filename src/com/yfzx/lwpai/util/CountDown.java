package com.yfzx.lwpai.util;

import java.text.DecimalFormat;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * 倒计时
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-24
 */
public class CountDown extends CountDownTimer {

	private TextView tvHour;
	private TextView tvMinute;
	private TextView tvSecond;
	private int flag;
	private DecimalFormat formater = new DecimalFormat("00");

	public CountDown(long millisInFuture, TextView tvHour, TextView tvMinute,
			TextView tvSecond) {
		super(millisInFuture, 1000);
		this.tvHour = tvHour;
		this.tvMinute = tvMinute;
		this.tvSecond = tvSecond;
		flag = 0;

	}

	public CountDown(long millisInFuture, TextView tvHour, int flag) {
		super(millisInFuture, 1000);
		this.tvHour = tvHour;
		this.flag = flag;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		long myDay = millisUntilFinished / (1000 * 60 * 60 * 24);
		long allHour = millisUntilFinished / (1000 * 3600);
		long myHour = allHour - (myDay * 24);
		long myMinute = millisUntilFinished / (1000 * 60) - (myDay * 24 * 60)
				- (myHour * 60);
		long mySecond = millisUntilFinished / (1000) - (myDay * 24 * 60 * 60)
				- (myHour * 60 * 60) - (myMinute * 60);
		switch (flag) {
		case 0:// 首页倒计时
			tvHour.setText(allHour + "");
			tvMinute.setText(Helper.Convert(myMinute));
			tvSecond.setText(Helper.Convert(mySecond));
			break;
		case 1:// 红包区倒计时
			tvHour.setText("距离本次结束:" + allHour + ":"
					+ formater.format(myMinute) + ":"
					+ formater.format(mySecond));
			break;
		case 2:// 详细页面距结束倒计时
			tvHour.setText("距结束  : " + myDay + "天" + formater.format(myHour)
					+ "小时" + formater.format(myMinute) + "分"
					+ formater.format(mySecond) + "秒");
			break;
		case 3:// 详细页面倒计时
			tvHour.setText("距开始  : " + myDay + "天" + formater.format(myHour)
					+ "小时" + formater.format(myMinute) + "分"
					+ formater.format(mySecond) + "秒");
			break;
		}

	}

	@Override
	public void onFinish() {

		switch (flag) {
		case 0:// 首页倒计时
			tvHour.setText("00");
			tvMinute.setText("00");
			tvSecond.setText("00");
			break;

		case 1:// 红包区倒计时
			tvHour.setText("距离本次结束:00:00:00");
			break;

		case 2:// 详细页面倒计时
			tvHour.setText("距结束  : 0天0小时0分0秒 ");
			break;
		case 3:// 详细页面倒计时
			tvHour.setText("距开始  : 0天0小时0分0秒 ");
			break;
		}

	}

}
