package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class LuckyProductByIdResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4043041772034276564L;
	/**
	 * MarketPrice : 242000 LotteryResults : WinnerName : CurrentCount : 357
	 * UnitPrice : 1.0000 ProductId : 299 Fees : 3.0000 EndDate : 2015/8/4
	 * 21:50:00 LimitCount : 242000 SalePrice : 0 StartDate : 2015/6/6 17:00:00
	 * PeriodIndex : 1 WinnerNum : Id : 2 MaxNumPercentage : 36666 ProductName :
	 * “0元拍”宝马 您买车我出钱 OrderShareId : 0 IsFinish : 0
	 */
	private int MarketPrice;
	private String LotteryResults;
	private String WinnerName;
	private String CurrentCount;
	private String UnitPrice;
	private int ProductId;
	private String Fees;
	private String EndDate;
	private String LimitCount;
	private int SalePrice;
	private String StartDate;
	private String PeriodIndex;
	private String WinnerNum;
	private String Id;
	private String MaxNumPercentage;
	private String ProductName;
	private String OrderShareId;
	private String IsFinish;
	private String OnePurchasePTId;
	private String Percentage;


	public String getPercentage() {
		return Percentage;
	}

	public void setPercentage(String percentage) {
		Percentage = percentage;
	}

	public String getOnePurchasePTId() {
		return OnePurchasePTId;
	}

	public void setOnePurchasePTId(String onePurchasePTId) {
		OnePurchasePTId = onePurchasePTId;
	}

	public void setMarketPrice(int MarketPrice) {
		this.MarketPrice = MarketPrice;
	}

	public void setLotteryResults(String LotteryResults) {
		this.LotteryResults = LotteryResults;
	}

	public void setWinnerName(String WinnerName) {
		this.WinnerName = WinnerName;
	}

	public void setCurrentCount(String CurrentCount) {
		this.CurrentCount = CurrentCount;
	}

	public void setUnitPrice(String UnitPrice) {
		this.UnitPrice = UnitPrice;
	}

	public void setProductId(int ProductId) {
		this.ProductId = ProductId;
	}

	public void setFees(String Fees) {
		this.Fees = Fees;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setLimitCount(String LimitCount) {
		this.LimitCount = LimitCount;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setPeriodIndex(String PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setWinnerNum(String WinnerNum) {
		this.WinnerNum = WinnerNum;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public void setMaxNumPercentage(String MaxNumPercentage) {
		this.MaxNumPercentage = MaxNumPercentage;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setOrderShareId(String OrderShareId) {
		this.OrderShareId = OrderShareId;
	}

	public void setIsFinish(String IsFinish) {
		this.IsFinish = IsFinish;
	}

	public int getMarketPrice() {
		return MarketPrice;
	}

	public String getLotteryResults() {
		return LotteryResults;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public String getCurrentCount() {
		return CurrentCount;
	}

	public String getUnitPrice() {
		return UnitPrice;
	}

	public int getProductId() {
		return ProductId;
	}

	public String getFees() {
		return Fees;
	}

	public String getEndDate() {
		return EndDate;
	}

	public String getLimitCount() {
		return LimitCount;
	}

	public int getSalePrice() {
		return SalePrice;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getPeriodIndex() {
		return PeriodIndex;
	}

	public String getWinnerNum() {
		return WinnerNum;
	}

	public String getId() {
		return Id;
	}

	public String getMaxNumPercentage() {
		return MaxNumPercentage;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getOrderShareId() {
		return OrderShareId;
	}

	public String getIsFinish() {
		return IsFinish;
	}

}
