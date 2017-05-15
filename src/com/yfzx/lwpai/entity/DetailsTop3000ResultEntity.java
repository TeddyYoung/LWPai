package com.yfzx.lwpai.entity;

import java.io.Serializable;

/**
 * top3000
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class DetailsTop3000ResultEntity implements Serializable {

	private static final long serialVersionUID = 6294468307150732963L;
	private String LuckyIp;
	private String LuckyCity;
	private String LotteryNumbers;
	private String UserName;
	private String OfferTime;
	private String WinnerRanking;
	private String FaceImage;
	private String LuckyProvince;
	private String RemarkName;
	private String OfferNum;
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

	public void setLuckyIp(String LuckyIp) {
		this.LuckyIp = LuckyIp;
	}

	public void setLuckyCity(String LuckyCity) {
		this.LuckyCity = LuckyCity;
	}

	public void setLotteryNumbers(String LotteryNumbers) {
		this.LotteryNumbers = LotteryNumbers;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public void setOfferTime(String OfferTime) {
		this.OfferTime = OfferTime;
	}

	public void setWinnerRanking(String WinnerRanking) {
		this.WinnerRanking = WinnerRanking;
	}

	public void setFaceImage(String FaceImage) {
		this.FaceImage = FaceImage;
	}

	public void setLuckyProvince(String LuckyProvince) {
		this.LuckyProvince = LuckyProvince;
	}

	public void setRemarkName(String RemarkName) {
		this.RemarkName = RemarkName;
	}

	public String getLuckyIp() {
		return LuckyIp;
	}

	public String getLuckyCity() {
		return LuckyCity;
	}

	public String getLotteryNumbers() {
		return LotteryNumbers;
	}

	public String getUserName() {
		return UserName;
	}

	public String getOfferTime() {
		return OfferTime;
	}

	public String getWinnerRanking() {
		return WinnerRanking;
	}

	public String getFaceImage() {
		return FaceImage;
	}

	public String getLuckyProvince() {
		return LuckyProvince;
	}

	public String getRemarkName() {
		return RemarkName;
	}
}
