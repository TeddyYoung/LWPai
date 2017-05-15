package com.yfzx.lwpai.entity;

import java.io.Serializable;

public class DetailsPriceResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7483340854848189295L;
	/**
	 * LuckyIp : 125.79.55.75 SumCount : 16965 winLotteryNum : 2,3,33,12,28,14,7
	 * EndDate : 2015/4/19 21:50:00 LuckyCity : 漳州市 PeriodIndex : 1 ProductName
	 * : 0元拍iPhone6 UserName : cl**lj FaceImage :
	 * http://www.lwpai.com/utility/pics/tx.gif LuckyProvince : 福建省
	 */
	private String LuckyIp;
	private String SumCount;
	private String winLotteryNum;
	private String EndDate;
	private String LuckyCity;
	private String PeriodIndex;
	private String ProductName;
	private String UserName;
	private String FaceImage;
	private String LuckyProvince;
	private String OfferNum;
	private String OfferTime;
	private String OnePurchaseCount;
	private String OnePurchaseTime;
	private String OnePurchaseRNum;
	
	
	public String getOnePurchaseCount() {
		return OnePurchaseCount;
	}

	public void setOnePurchaseCount(String onePurchaseCount) {
		OnePurchaseCount = onePurchaseCount;
	}

	public String getOnePurchaseTime() {
		return OnePurchaseTime;
	}

	public void setOnePurchaseTime(String onePurchaseTime) {
		OnePurchaseTime = onePurchaseTime;
	}

	public String getOnePurchaseRNum() {
		return OnePurchaseRNum;
	}

	public void setOnePurchaseRNum(String onePurchaseRNum) {
		OnePurchaseRNum = onePurchaseRNum;
	}

	public String getOfferNum() {
		return OfferNum;
	}

	public void setOfferNum(String offerNum) {
		OfferNum = offerNum;
	}

	public String getOfferTime() {
		return OfferTime;
	}

	public void setOfferTime(String offerTime) {
		OfferTime = offerTime;
	}

	public void setLuckyIp(String LuckyIp) {
		this.LuckyIp = LuckyIp;
	}

	public void setSumCount(String SumCount) {
		this.SumCount = SumCount;
	}

	public void setWinLotteryNum(String winLotteryNum) {
		this.winLotteryNum = winLotteryNum;
	}

	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}

	public void setLuckyCity(String LuckyCity) {
		this.LuckyCity = LuckyCity;
	}

	public void setPeriodIndex(String PeriodIndex) {
		this.PeriodIndex = PeriodIndex;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public void setFaceImage(String FaceImage) {
		this.FaceImage = FaceImage;
	}

	public void setLuckyProvince(String LuckyProvince) {
		this.LuckyProvince = LuckyProvince;
	}

	public String getLuckyIp() {
		return LuckyIp;
	}

	public String getSumCount() {
		return SumCount;
	}

	public String getWinLotteryNum() {
		return winLotteryNum;
	}

	public String getEndDate() {
		return EndDate;
	}

	public String getLuckyCity() {
		return LuckyCity;
	}

	public String getPeriodIndex() {
		return PeriodIndex;
	}

	public String getProductName() {
		return ProductName;
	}

	public String getUserName() {
		return UserName;
	}

	public String getFaceImage() {
		return FaceImage;
	}

	public String getLuckyProvince() {
		return LuckyProvince;
	}

}
