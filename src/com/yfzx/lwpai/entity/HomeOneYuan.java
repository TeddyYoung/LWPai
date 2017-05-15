package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 首页一元拍实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeOneYuan {
	/**
	 * message : 成功获取商品信息 result :
	 * [{"Percentage":46.7,"AuctionProductsId":"8","SalePrice"
	 * :10688,"OnePurchasePTId"
	 * :"14","CurrentPurchaseNum":4991,"PeriodIndex":1,"ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_60449a174ac94c74ab9a7caa366f87d4.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_60449a174ac94c74ab9a7caa366f87d4.jpg"
	 * ,"ProductName":"苹果（Apple）MacBook","OnePurchaseNum":10688,"ProductId":328,
	 * "LastPurchaseNum":5697}] success : True Total : 1
	 */
	private String message;
	private List<HomeOneYuanResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<HomeOneYuanResult> result) {
		this.result = result;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public void setTotal(int Total) {
		this.Total = Total;
	}

	public String getMessage() {
		return message;
	}

	public List<HomeOneYuanResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
