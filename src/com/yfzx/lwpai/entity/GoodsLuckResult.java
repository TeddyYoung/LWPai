package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * 商品幸运拍
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
public class GoodsLuckResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6287784427275861381L;
	/**
	 * MarketPrice : 242000 ProductShortTitle : 0元拍宝马 IsRecom : 1 LotteryResults
	 * : CurrentCount : 289 UnitPrice : 1.0000 UrlTip : 参与竞拍 ProductId : 299
	 * Fees : 3.0000 LimitCount : 242000 EndDate : 2015/8/4 21:50:00 SalePrice :
	 * 0 StartDate : 2015/6/6 17:00:00 VistiCounts : 74193 RowNumber : 1
	 * ThumbnailUrl440 : http://img.lwpai.com/Storage/master/product/thumbs440/
	 * 440_a762e4f7ca91426c8e3622c4f4bef2eb.jpg ThumbnailUrl100 :
	 * http://img.lwpai.com/Storage/master/product/thumbs100/
	 * 100_a762e4f7ca91426c8e3622c4f4bef2eb.jpg PeriodIndex : 1 Id : 2
	 * ProductName : “0元拍”宝马 您买车我出钱 MaxNumPercentage : 36666 IsFinish : 0
	 */
	private int MarketPrice;
	private String ProductShortTitle;
	private String IsRecom;
	private String LotteryResults;
	private String CurrentCount;
	private String UnitPrice;
	private String UrlTip;
	private String ProductId;
	private String Fees;
	private String LimitCount;
	private String EndDate;
	private int SalePrice;
	private String StartDate;
	private String VistiCounts;
	private String RowNumber;
	private String ThumbnailUrl440;
	private String ThumbnailUrl100;
	private String PeriodIndex;
	private String Id;
	private String ProductName;
	private String MaxNumPercentage;
	private String IsFinish;
	private String WinnerName;
	private String WinnerNum;

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

	public void setMarketPrice(int MarketPrice) {
		this.MarketPrice = MarketPrice;
	}

	public void setProductShortTitle(String ProductShortTitle) {
		this.ProductShortTitle = ProductShortTitle;
	}

	public void setIsRecom(String IsRecom) {
		this.IsRecom = IsRecom;
	}

	public void setLotteryResults(String LotteryResults) {
		this.LotteryResults = LotteryResults;
	}

	public void setCurrentCount(String CurrentCount) {
		this.CurrentCount = CurrentCount;
	}

	public void setUnitPrice(String UnitPrice) {
		this.UnitPrice = UnitPrice;
	}

	public void setUrlTip(String UrlTip) {
		this.UrlTip = UrlTip;
	}

	public void setProductId(String ProductId) {
		this.ProductId = ProductId;
	}

	public void setFees(String Fees) {
		this.Fees = Fees;
	}

	public void setLimitCount(String LimitCount) {
		this.LimitCount = LimitCount;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setSalePrice(int SalePrice) {
		this.SalePrice = SalePrice;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public void setVistiCounts(String VistiCounts) {
		this.VistiCounts = VistiCounts;
	}

	public void setRowNumber(String RowNumber) {
		this.RowNumber = RowNumber;
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

	public void setId(String Id) {
		this.Id = Id;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setMaxNumPercentage(String MaxNumPercentage) {
		this.MaxNumPercentage = MaxNumPercentage;
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

	public String getIsRecom() {
		return IsRecom;
	}

	public String getLotteryResults() {
		return LotteryResults;
	}

	public String getCurrentCount() {
		return CurrentCount;
	}

	public String getUnitPrice() {
		return UnitPrice;
	}

	public String getUrlTip() {
		return UrlTip;
	}

	public String getProductId() {
		return ProductId;
	}

	public String getFees() {
		return Fees;
	}

	public String getLimitCount() {
		return LimitCount;
	}

	public String getEndDate() {
		return EndDate;
	}

	public int getSalePrice() {
		return SalePrice;
	}

	public String getStartDate() {
		return StartDate;
	}

	public String getVistiCounts() {
		return VistiCounts;
	}

	public String getRowNumber() {
		return RowNumber;
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

	public String getId() {
		return Id;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getMaxNumPercentage() {
		return MaxNumPercentage;
	}

	public String getIsFinish() {
		return IsFinish;
	}

}
