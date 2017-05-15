package com.yfzx.lwpai.entity;

import java.util.List;

public class LuckyProductByIdEntity {
	/**
	 * message : 成功获取该商品信息 result :
	 * [{"MarketPrice":242000,"LotteryResults":"","WinnerName"
	 * :"","CurrentCount":
	 * "357","UnitPrice":"1.0000","ProductId":299,"Fees":"3.0000"
	 * ,"EndDate":"2015/8/4 21:50:00"
	 * ,"LimitCount":"242000","SalePrice":0,"StartDate"
	 * :"2015/6/6 17:00:00","PeriodIndex"
	 * :"1","WinnerNum":"","Id":"2","MaxNumPercentage"
	 * :"36666","ProductName":"\u201c0元拍\u201d宝马 您买车我出钱"
	 * ,"OrderShareId":"0","IsFinish":"0"}] PImgSrc : [{"ImgSrc":
	 * "http://img.lwpai.com/Storage/master/product/images/a762e4f7ca91426c8e3622c4f4bef2eb.jpg"
	 * },{"ImgSrc":
	 * "http://img.lwpai.com/Storage/master/product/images/b5a9d4495fbf4c18835d346c5d7dc710.jpg"
	 * },{"ImgSrc":
	 * "http://img.lwpai.com/Storage/master/product/images/b3a8593f20dc4ff289062a8f9190d424.jpg"
	 * }] success : True
	 */
	private String message;
	private List<LuckyProductByIdResult> result;
	private List<AdInfoEntity> PImgSrc;
	private String success;
	private String Status;
	private String OnePurchaseTime;

	public String getOnePurchaseTime() {
		return OnePurchaseTime;
	}

	public void setOnePurchaseTime(String onePurchaseTime) {
		OnePurchaseTime = onePurchaseTime;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<LuckyProductByIdResult> result) {
		this.result = result;
	}

	public void setPImgSrc(List<AdInfoEntity> PImgSrc) {
		this.PImgSrc = PImgSrc;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public List<LuckyProductByIdResult> getResult() {
		return result;
	}

	public List<AdInfoEntity> getPImgSrc() {
		return PImgSrc;
	}

	public String getSuccess() {
		return success;
	}

}
