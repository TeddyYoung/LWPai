package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 商品幸运拍实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-18
 */
public class GoodsLuckEntity {
	/**
	 * message : 成功获取幸运拍商品 result :
	 * [{"MarketPrice":242000,"ProductShortTitle":"0元拍宝马"
	 * ,"IsRecom":"1","LotteryResults"
	 * :"","CurrentCount":"289","UnitPrice":"1.0000"
	 * ,"UrlTip":"参与竞拍","ProductId":
	 * "299","Fees":"3.0000","LimitCount":"242000","EndDate"
	 * :"2015/8/4 21:50:00",
	 * "SalePrice":0,"StartDate":"2015/6/6 17:00:00","VistiCounts"
	 * :"74193","RowNumber":"1","ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_a762e4f7ca91426c8e3622c4f4bef2eb.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_a762e4f7ca91426c8e3622c4f4bef2eb.jpg"
	 * ,"PeriodIndex":"1","Id":"2","ProductName":"\u201c0元拍\u201d宝马 您买车我出钱",
	 * "MaxNumPercentage":"36666","IsFinish":"0"}] success : True Total : 43
	 */
	private String message;
	private List<GoodsLuckResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<GoodsLuckResult> result) {
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

	public List<GoodsLuckResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}

}
