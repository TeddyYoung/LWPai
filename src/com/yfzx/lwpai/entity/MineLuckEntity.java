package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class MineLuckEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4597126972198608805L;
	/**
	 * SumCount : 1 EndDate : 2015/7/23 21:50:00 StartDate : LotteryResults :
	 * WinnerName : PeriodIndex : 3 ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage
	 * /master/product/thumbs440/440_c050d5b29614495c9dcc327af6e7da40.jpg
	 * ThumbnailUrl100 : http://img.lwpai.com/Storage/master/product/thumbs100/
	 * 100_c050d5b29614495c9dcc327af6e7da40.jpg WinnerNum : ProductName : 进口食品
	 * 曲奇饼米果组合 Id : 6705 LuckyId : 275
	 */
	private String SumCount;
	private String EndDate;
	private String StartDate;
	private String LotteryResults;
	private String WinnerName;
	private String PeriodIndex;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String WinnerNum;
	private String ProductName;
	private String Id;
	private String Status;
	private String LuckyId;
	private String OnePurchasePId;
	private String OnePurchasePTId;
	private String MaxNumPercentage;
	private String CurrentCount;
	private String Percentage;
	private String IsStop;

	public String getIsStop() {
		return IsStop;
	}

	public void setIsStop(String isStop) {
		IsStop = isStop;
	}

	public String getPercentage() {
		return Percentage;
	}

	public void setPercentage(String percentage) {
		Percentage = percentage;
	}

	public String getMaxNumPercentage() {
		return MaxNumPercentage;
	}

	public void setMaxNumPercentage(String maxNumPercentage) {
		MaxNumPercentage = maxNumPercentage;
	}

	public String getCurrentCount() {
		return CurrentCount;
	}

	public void setCurrentCount(String currentCount) {
		CurrentCount = currentCount;
	}

	public String getOnePurchasePId() {
		return OnePurchasePId;
	}

	public void setOnePurchasePId(String onePurchasePId) {
		OnePurchasePId = onePurchasePId;
	}

	public String getOnePurchasePTId() {
		return OnePurchasePTId;
	}

	public void setOnePurchasePTId(String onePurchasePTId) {
		OnePurchasePTId = onePurchasePTId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setSumCount(String SumCount) {
		this.SumCount = SumCount;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setLotteryResults(String LotteryResults) {
		this.LotteryResults = LotteryResults;
	}

	public void setWinnerName(String WinnerName) {
		this.WinnerName = WinnerName;
	}

	public void setPeriodIndex(String PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
	}

	public void setWinnerNum(String WinnerNum) {
		this.WinnerNum = WinnerNum;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public void setLuckyId(String LuckyId) {
		this.LuckyId = LuckyId;
	}

	public String getSumCount() {
		return SumCount;
	}

	public String getEndDate() {
		return EndDate;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getLotteryResults() {
		return LotteryResults;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public String getPeriodIndex() {
		return PeriodIndex;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
	}

	public String getWinnerNum() {
		return WinnerNum;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getId() {
		return Id;
	}

	public String getLuckyId() {
		return LuckyId;
	}
}
