package com.yfzx.lwpai.entity;

import java.util.List;

/**
 * 首页幸运拍实体
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-7-14
 */
public class HomeLuck {
	/**
	 * message : 成功获取商品信息 result :
	 * [{"EndDate":"2015-08-04 21:50:00","Count":289,
	 * "AuctionProductsId":2,"StartDate"
	 * :"2015-06-06 17:00:00","SalePrice":242000,"ThumbnailUrl440":
	 * "http://img.lwpai.com/Storage/master/product/thumbs440/440_a762e4f7ca91426c8e3622c4f4bef2eb.jpg"
	 * ,"ThumbnailUrl100":
	 * "http://img.lwpai.com/Storage/master/product/thumbs100/100_a762e4f7ca91426c8e3622c4f4bef2eb.jpg"
	 * ,"MaxNumPercentage":"36666","ProductName":"0元拍宝马"}] success : True Total
	 * : 1
	 */
	private String message;
	private List<HomeLuckResult> result;
	private String success;
	private int Total;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(List<HomeLuckResult> result) {
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

	public List<HomeLuckResult> getResult() {
		return result;
	}

	public String getSuccess() {
		return success;
	}

	public int getTotal() {
		return Total;
	}
}
