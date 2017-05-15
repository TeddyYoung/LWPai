package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 首页幸运拍Result
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeLuckResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4350037993797713621L;
	/**
	 * EndDate : 2015-08-04 21:50:00 Count : 289 AuctionProductsId : 2 StartDate
	 * : 2015-06-06 17:00:00 SalePrice : 242000 ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage/master/product/thumbs440/
	 * 440_a762e4f7ca91426c8e3622c4f4bef2eb.jpg ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage/master/product/thumbs100/
	 * 100_a762e4f7ca91426c8e3622c4f4bef2eb.jpg MaxNumPercentage : 36666
	 * ProductName : 0元拍宝马
	 */
	private String EndDate;
	private int Count;
	private int AuctionProductsId;
	private String StartDate;
	private int SalePrice;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String MaxNumPercentage;
	private String ProductName;
	private int OnePurchaseNum;
	private int CurrentPurchaseNum;
	private int LastPurchaseNum;
	private String Percentage;
	private int PeriodIndex;
	private int OnePurchasePTId;
	private int Status;

	public String getPercentage() {
		return Percentage;
	}

	public void setPercentage(String percentage) {
		Percentage = percentage;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setCount(int Count) {
		this.Count = Count;
	}

	public void setAuctionProductsId(int AuctionProductsId) {
		this.AuctionProductsId = AuctionProductsId;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
	}

	public void setMaxNumPercentage(String MaxNumPercentage) {
		this.MaxNumPercentage = MaxNumPercentage;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public String getEndDate() {
		return EndDate;
	}

	public int getCount() {
		return Count;
	}

	public int getAuctionProductsId() {
		return AuctionProductsId;
	}

	public String getStartDate() {
		return StartDate;
	}

	public int getSalePrice() {
		return SalePrice;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
	}

	public String getMaxNumPercentage() {
		return MaxNumPercentage;
	}

	public String getProductName() {
		return ProductName;
	}

	public int getOnePurchaseNum() {
		return OnePurchaseNum;
	}

	public void setOnePurchaseNum(int onePurchaseNum) {
		OnePurchaseNum = onePurchaseNum;
	}

	public int getCurrentPurchaseNum() {
		return CurrentPurchaseNum;
	}

	public void setCurrentPurchaseNum(int currentPurchaseNum) {
		CurrentPurchaseNum = currentPurchaseNum;
	}

	public int getLastPurchaseNum() {
		return LastPurchaseNum;
	}

	public void setLastPurchaseNum(int lastPurchaseNum) {
		LastPurchaseNum = lastPurchaseNum;
	}

	public int getPeriodIndex() {
		return PeriodIndex;
	}

	public void setPeriodIndex(int periodIndex) {
		PeriodIndex = periodIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getOnePurchasePTId() {
		return OnePurchasePTId;
	}

	public void setOnePurchasePTId(int onePurchasePTId) {
		OnePurchasePTId = onePurchasePTId;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
