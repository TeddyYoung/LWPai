package com.yfzx.lwpai.entity;

import java.io.Serializable;

import org.apache.http.entity.SerializableEntity;

public class NewestResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4627632915417916264L;
	/**
	 * MarketPrice : 0 ProductShortTitle : 懒人手机支架 WinnerName : a5**76
	 * CurrentCount : 24 WinUserId : 5449 UnitPrice : 1.0000 ProductId : 583
	 * Fees : 1.0000 EndDate : 2015/6/30 21:50:00 LimitCount : 50 StartDate :
	 * 2015/6/23 22:00:00 ThumbnailUrl440 : ThumbnailUrl100 : PeriodIndex : 1
	 * WinnerNum : 06-33-30-31-18-12-12 Id : 152 MaxNumPercentage : 0
	 * ProductName : Homee 随意扭扭乐懒人手机支架（发货颜色随机） IsFinish : 2
	 */
	private int MarketPrice;
	private String ProductShortTitle;
	private String WinnerName;
	private String CurrentCount;
	private String WinUserId;
	private String UnitPrice;
	private int ProductId;
	private String Fees;
	private String EndDate;
	private String LimitCount;
	private String StartDate;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String PeriodIndex;
	private String WinnerNum;
	private String Id;
	private String MaxNumPercentage;
	private String ProductName;
	private String IsFinish;
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

	public String getCurrentPurchaseNum() {
		return CurrentPurchaseNum;
	}

	public void setCurrentPurchaseNum(String currentPurchaseNum) {
		CurrentPurchaseNum = currentPurchaseNum;
	}

	private String OnePurchasePId;
	private String OnePurchasePTId;
	private String CurrentPurchaseNum;

	public void setMarketPrice(int MarketPrice) {
		this.MarketPrice = MarketPrice;
	}

	public void setProductShortTitle(String ProductShortTitle) {
		this.ProductShortTitle = ProductShortTitle;
	}

	public void setWinnerName(String WinnerName) {
		this.WinnerName = WinnerName;
	}

	public void setCurrentCount(String CurrentCount) {
		this.CurrentCount = CurrentCount;
	}

	public void setWinUserId(String WinUserId) {
		this.WinUserId = WinUserId;
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

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
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

	public void setIsFinish(String IsFinish) {
		this.IsFinish = IsFinish;
	}

	public int getMarketPrice() {
		return MarketPrice;
	}

	public String getProductShortTitle() {
		return ProductShortTitle;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public String getCurrentCount() {
		return CurrentCount;
	}

	public String getWinUserId() {
		return WinUserId;
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

	public String getStartDate() {
		return StartDate;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
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

	public String getIsFinish() {
		return IsFinish;
	}

}
