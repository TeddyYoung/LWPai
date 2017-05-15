package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 商品一元拍
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
public class GoodsOneYuanResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5112993767005105794L;
	/**
	 * Percentage : 49.28 SalePrice : 556 OnePurchasePTId : 0 OnePurchasePId :
	 * 53 CurrentPurchaseNum : 274 PeriodIndex : 1 ThumbnailUrl440 :
	 * http://img.lwpai.com/Storage/master/product/thumbs440/
	 * 440_e8e92c9bba134d6e911520fd1629c475.jpg ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage/master/product/thumbs100/
	 * 100_e8e92c9bba134d6e911520fd1629c475.jpg ProductName : 虎牌儿童型真空杯
	 * OnePurchaseNum : 556 ProductId : 480 LastPurchaseNum : 282
	 */
	private double Percentage;
	private int SalePrice;
	private int OnePurchasePTId;
	private int OnePurchasePId;
	private int CurrentPurchaseNum;
	private int PeriodIndex;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String ProductName;
	private int OnePurchaseNum;
	private int ProductId;
	private int LastPurchaseNum;
	private String WinnerName;
	private String WinnerNum;
	private String OnePurchaseTime;

	public String getOnePurchaseTime() {
		return OnePurchaseTime;
	}

	public void setOnePurchaseTime(String onePurchaseTime) {
		OnePurchaseTime = onePurchaseTime;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public void setWinnerName(String winnerName) {
		WinnerName = winnerName;
	}

	public String getWinnerNum() {
		return WinnerNum;
	}

	public void setWinnerNum(String winnerNum) {
		WinnerNum = winnerNum;
	}

	public void setPercentage(double Percentage) {
		this.Percentage = Percentage;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setOnePurchasePTId(int OnePurchasePTId) {
		this.OnePurchasePTId = OnePurchasePTId;
	}

	public void setOnePurchasePId(int OnePurchasePId) {
		this.OnePurchasePId = OnePurchasePId;
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

	public int getSalePrice() {
		return SalePrice;
	}

	public int getOnePurchasePTId() {
		return OnePurchasePTId;
	}

	public int getOnePurchasePId() {
		return OnePurchasePId;
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
