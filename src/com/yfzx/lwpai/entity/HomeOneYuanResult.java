package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 首页一元拍Result
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeOneYuanResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9019364127708564086L;
	/**
	 * Percentage : 46.7 AuctionProductsId : 8 SalePrice : 10688 OnePurchasePTId
	 * : 14 CurrentPurchaseNum : 4991 PeriodIndex : 1 ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage/master/product/thumbs440/440_60449
	 * a174ac94c74ab9a7caa366f87d4.jpg ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage
	 * /master/product/thumbs100/100_60449a174ac94c74ab9a7caa366f87d4.jpg
	 * ProductName : 苹果（Apple）MacBook OnePurchaseNum : 10688 ProductId : 328
	 * LastPurchaseNum : 5697
	 */
	private double Percentage;
	private String AuctionProductsId;
	private int SalePrice;
	private String OnePurchasePTId;
	private int CurrentPurchaseNum;
	private int PeriodIndex;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String ProductName;
	private int OnePurchaseNum;
	private int ProductId;
	private int LastPurchaseNum;

	public void setPercentage(double Percentage) {
		this.Percentage = Percentage;
	}

	public void setAuctionProductsId(String AuctionProductsId) {
		this.AuctionProductsId = AuctionProductsId;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setOnePurchasePTId(String OnePurchasePTId) {
		this.OnePurchasePTId = OnePurchasePTId;
	}

	public void setCurrentPurchaseNum(int CurrentPurchaseNum) {
		this.CurrentPurchaseNum = CurrentPurchaseNum;
	}

	public void setPeriodIndex(int PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setThumbnailUrl440(String ThumbnailUrl440) {
		this.ThumbnailUrl440 = ThumbnailUrl440;
	}

	public void setThumbnailUrl100(String ThumbnailUrl100) {
		this.ThumbnailUrl100 = ThumbnailUrl100;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setOnePurchaseNum(int OnePurchaseNum) {
		this.OnePurchaseNum = OnePurchaseNum;
	}

	public void setProductId(int ProductId) {
		this.ProductId = ProductId;
	}

	public void setLastPurchaseNum(int LastPurchaseNum) {
		this.LastPurchaseNum = LastPurchaseNum;
	}

	public double getPercentage() {
		return Percentage;
	}

	public String getAuctionProductsId() {
		return AuctionProductsId;
	}

	public int getSalePrice() {
		return SalePrice;
	}

	public String getOnePurchasePTId() {
		return OnePurchasePTId;
	}

	public int getCurrentPurchaseNum() {
		return CurrentPurchaseNum;
	}

	public int getPeriodIndex() {
		return PeriodIndex;
	}

	public String getThumbnailUrl440() {
		return ThumbnailUrl440;
	}

	public String getThumbnailUrl100() {
		return ThumbnailUrl100;
	}

	public String getProductName() {
		return ProductName;
	}

	public int getOnePurchaseNum() {
		return OnePurchaseNum;
	}

	public int getProductId() {
		return ProductId;
	}

	public int getLastPurchaseNum() {
		return LastPurchaseNum;
	}
}
